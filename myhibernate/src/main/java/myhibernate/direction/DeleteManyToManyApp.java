package myhibernate.direction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import myhibernate.direction.entity.Ingredient;
import myhibernate.direction.entity.Recipe;

public class DeleteManyToManyApp {

	public static void main(String[] args) {
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Recipe.class);
		conf.addAnnotatedClass(Ingredient.class);
		SessionFactory factory = conf.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();

		String sql = "select distinct r from Recipe r join fetch r.ingredients where r.recipeName= :recipeName ";		
		Query<Recipe> query = session.createQuery(sql);
		query.setParameter("recipeName", "sernik");	

		Recipe recipe = query.getSingleResult();
		System.out.println("Recipe istnieje w baze:" + recipe);
				
		for(Ingredient ingredient : recipe.getIngredients()) {
			System.out.println("Ingredient: " + ingredient);
			
			//kasujemy tylko sk³adniki nale¿¹ce do przepisu sernik
			if (ingredient.getRecipes().size() == 1) {
				System.out.println("kasuje Ingredient: " + ingredient);
				session.delete(ingredient);
			}
		}
		
		session.delete(recipe);
		
		session.getTransaction().commit();
		factory.close();

	}

}
