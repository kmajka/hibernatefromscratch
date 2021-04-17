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
@Table(name = "recipe")
public class Recipe {

	@Id
	@Column(name = "recipe_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long recipeId;

	@Column(name = "recipe_name")
	private String recipeName;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "recipe_ingredient", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	List<Ingredient> ingeredients;

	public Recipe() {	
	}
	
	public Recipe(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public List<Ingredient> getIngeredients() {
		return ingeredients;
	}

	public void addIngeredient(Ingredient ingredient) {
		if (ingeredients == null) {
			ingeredients = new ArrayList<Ingredient>();
		}
		ingeredients.add(ingredient);
	}

	public void setIngeredients(List<Ingredient> ingeredients) {
		this.ingeredients = ingeredients;
	}

	@Override
	public String toString() {
		return "Recipe [recipeId=" + recipeId + ", recipeName=" + recipeName + "]";
	}

}
