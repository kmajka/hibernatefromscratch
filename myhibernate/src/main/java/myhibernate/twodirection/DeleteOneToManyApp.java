package myhibernate.twodirection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import myhibernate.twodirection.entity.Car;
import myhibernate.twodirection.entity.Owner;

public class DeleteOneToManyApp {

	public static void main(String[] args) {
		
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Owner.class);
		conf.addAnnotatedClass(Car.class);
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
				
		//kasowanie pustych Car oraz Car i po³¹czonych z nim Owner
		String sqlCars = "select distinct c from Car c left join fetch c.ownerId ";	
		Query<Car> queryCars = session.createQuery(sqlCars);
		for(Car car : queryCars.list()) {
			System.out.println("Car istnieje w baze:" + car);
			
			session.delete(car);
			if (car.getOwnerId() != null) {
				session.delete(car.getOwnerId());
			}
			
			System.out.println("Car skasowany.");
		}
		
		//kasowanie pustych Owner oraz Owner i po³¹czonych z nim Car
		String sqlOwner = "select distinct o from Owner o left join fetch o.cars ";	
		Query<Owner> queryOwner = session.createQuery(sqlOwner);
		for(Owner owner : queryOwner.list()) {
			System.out.println("Owner istnieje w baze:" + owner);
			
			session.delete(owner);
			System.out.println("Owner skasowany.");
		}
		
		session.getTransaction().commit();
		factory.close();
	}
}
