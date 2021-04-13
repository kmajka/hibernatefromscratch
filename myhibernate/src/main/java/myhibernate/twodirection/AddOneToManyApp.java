package myhibernate.twodirection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import myhibernate.twodirection.entity.Car;
import myhibernate.twodirection.entity.Owner;

public class AddOneToManyApp {

	public static void main(String[] args) {
		
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Owner.class);
		conf.addAnnotatedClass(Car.class);
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		//create owner
		Owner owner = new Owner();
		owner.setFullName("Jan Kowalski12");
		
		//create car
		Car car1 = new Car("BMW12");
		Car car2 = new Car("OPEL12");
		
		//create relation for owner
		owner.AddCar(car1);
		owner.AddCar(car2);
		
		session.save(owner);
		
		session.getTransaction().commit();
		factory.close();
	}
}
