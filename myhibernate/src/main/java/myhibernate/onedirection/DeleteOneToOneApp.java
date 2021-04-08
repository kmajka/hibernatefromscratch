package myhibernate.onedirection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import myhibernate.onedirection.entity.Company;
import myhibernate.onedirection.entity.CompanyDetail;

public class DeleteOneToOneApp {

	public static void main(String[] args) {
		
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		String sql = "from Company c where c.companyName = :companyName ";		
		Query<Company> query = session.createQuery(sql);
		query.setParameter("companyName", "BIG Company 1");		
		Company company = query.getSingleResult();
		
		System.out.println("obiekt istnieje w baze:");
		System.out.println(company);
		
		session.delete(company); 
		System.out.println("obiekt skasowany.");
		
		session.getTransaction().commit();
		factory.close();

	}

}
