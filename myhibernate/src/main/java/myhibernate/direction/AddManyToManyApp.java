package myhibernate.direction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import myhibernate.direction.entity.Recipe;
import myhibernate.direction.entity.Ingredient;

public class AddManyToManyApp {

	public static void main(String[] args) {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Recipe.class);
		conf.addAnnotatedClass(Ingredient.class);
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();

		//create ingredients
		Ingredient ingredient1 = new Ingredient("mleko");
		Ingredient ingredient2 = new Ingredient("proszek do pieczenia");
		Ingredient ingredient3 = new Ingredient("bia³y ser");
		Ingredient ingredient4 = new Ingredient("czekolada");
		
		//create recipes
		Recipe recipe1 = new Recipe("sernik");
		recipe1.addIngeredient(ingredient1);
		recipe1.addIngeredient(ingredient2);
		recipe1.addIngeredient(ingredient3);
		
		Recipe recipe2 = new Recipe("tort czekoladowy");
		recipe2.addIngeredient(ingredient1);
		recipe2.addIngeredient(ingredient2);
		recipe2.addIngeredient(ingredient4);

		//utrwalenie obiektu w bazie
		session.persist(recipe1);
		session.persist(recipe2);
		
		session.getTransaction().commit();
		factory.close();
	}

}
