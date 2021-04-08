package myhibernate.twodirection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import myhibernate.twodirection.entity.Company;
import myhibernate.twodirection.entity.CompanyDetail;

public class DeleteOneToOneApp {

	public static void main(String[] args) {
		
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		String sql = "from CompanyDetail c join fetch c.company where c.companyDetailId = :companyId ";		
		Query<CompanyDetail> query = session.createQuery(sql);
		query.setParameter("companyId", 1);		
		CompanyDetail companyDetail = query.getSingleResult();
		
		System.out.println("obiekt istnieje w baze:");
		System.out.println(companyDetail);
		
		session.delete(companyDetail); 
		System.out.println("obiekt skasowany.");
		
		session.getTransaction().commit();
		factory.close();

	}

}
