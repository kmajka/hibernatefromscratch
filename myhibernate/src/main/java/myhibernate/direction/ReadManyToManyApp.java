package myhibernate.direction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import myhibernate.direction.entity.Ingredient;
import myhibernate.direction.entity.Recipe;

public class ReadManyToManyApp {

	public static void main(String[] args) {
		
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Recipe.class);
		conf.addAnnotatedClass(Ingredient.class);
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();

		String sql = "select distinct r from Recipe r join fetch r.ingredients ";		
		Query<Recipe> query = session.createQuery(sql);
		for(Recipe recipe : query.list()) {
			System.out.println("Recipe istnieje w baze:" + recipe);
			
			for(Ingredient ingredient : recipe.getIngredients()) {
				System.out.println("Ingredient: " + ingredient);
			}
		}
		
		session.getTransaction().commit();
		factory.close();

	}

}
