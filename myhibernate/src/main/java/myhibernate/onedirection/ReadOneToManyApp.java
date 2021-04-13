package myhibernate.onedirection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import myhibernate.onedirection.entity.Car;
import myhibernate.onedirection.entity.Owner;

public class ReadOneToManyApp {

	public static void main(String[] args) {
		
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Owner.class);
		conf.addAnnotatedClass(Car.class);
		
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		String sql = "select distinct o from Owner o join fetch o.cars ";		
		Query<Owner> query = session.createQuery(sql);
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
