package myhibernate.direction.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ingredient")
public class Ingredient {

	@Id
	@Column(name = "ingredient_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ingredientId;

	@Column(name = "ingredient_name")
	private String ingredientName;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "recipe_ingredient", joinColumns = @JoinColumn(name = "ingredient_id"), inverseJoinColumns = @JoinColumn(name = "recipe_id"))
	List<Recipe> recipes;
	
	public Ingredient() {
	}
	
	public Ingredient(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	public void addRecipe(Recipe recipe) {
		if (recipes == null) {
			recipes = new ArrayList<Recipe>();
		}
		
		recipes.add(recipe);
	}

	@Override
	public String toString() {
		return "Ingredient [ingredientId=" + ingredientId + ", ingredientName=" + ingredientName + "]";
	}

}
