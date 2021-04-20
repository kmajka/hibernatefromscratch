package myhibernate.twodirection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import myhibernate.twodirection.entity.Car;
import myhibernate.twodirection.entity.Owner;

public class ReadOneToManyCriteriaApp {

	public static void main(String[] args) {
		
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Owner.class);
		conf.addAnnotatedClass(Car.class);
		
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Owner> cr = cb.createQuery(Owner.class);
		Root<Owner> root = cr.from(Owner.class);
		cr.select(root).where(cb.like(root.get("fullName"),"Jan Kowalski"));
				
		Query<Owner> query = session.createQuery(cr);
		for(Owner owner : query.list()) {
			System.out.println("Owner istnieje w baze:" + owner);
			
			for(Car car : owner.getCars()) {
				System.out.println("Car: " + car);
			}
		}
		
		session.getTransaction().commit();
		factory.close();
	}

}
