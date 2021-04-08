package myhibernate.twodirection;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import myhibernate.twodirection.entity.Company;
import myhibernate.twodirection.entity.CompanyDetail;

public class RelationOneToOneApp {

	public static void main(String[] args) {
		
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		//create company
		Company company = new Company();
		company.setCompanyName("COMPANY 123");
		
		//create company detail
		CompanyDetail companyDetail = new CompanyDetail();
		companyDetail.setCountry("POL");
		
		Date dateCreatedCompany = new Date();
		companyDetail.setCreated(dateCreatedCompany);
		
		//add ref
		company.setCompanyDetail(companyDetail);
		session.save(company);
		
		session.getTransaction().commit();
		factory.close();
	}
}
