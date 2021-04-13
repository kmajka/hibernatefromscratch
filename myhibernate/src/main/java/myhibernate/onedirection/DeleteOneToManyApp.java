package myhibernate.onedirection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import myhibernate.onedirection.entity.Car;
import myhibernate.onedirection.entity.Owner;

public class DeleteOneToManyApp {

	public static void main(String[] args) {
		
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Owner.class);
		conf.addAnnotatedClass(Car.class);
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		String sql = "select distinct o from Owner o left join fetch o.cars ";	
		Query<Owner> query = session.createQuery(sql);
		for(Owner owner : query.list()) {
			System.out.println("Owner istnieje w baze:" + owner);
			
			session.delete(owner);
			System.out.println("Owner skasowany.");
		}
		
		session.getTransaction().commit();
		factory.close();
	}
}
