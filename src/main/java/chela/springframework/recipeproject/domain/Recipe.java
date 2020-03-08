package chela.springframework.recipeproject.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)    //Identity will support an automatic generation to Sequence
	private Long id;
	private String description;
	private int prepTime;
	private int cookTime;
	private int servings;
	private String source;
	private String url;

	@Lob
	private String directions;

	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;

	@Lob
	private Byte[] image;

	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "recipe_category",
		joinColumns = @JoinColumn(name = "recipe_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

//	-------------------------------------------------------------------------------

	public Recipe addIngredient(Ingredient ingredient){
		ingredient.setRecipe(this);
		this.ingredients.add(ingredient);
		return this;
	}

	public void setNotes(Notes notes) {
		this.notes = notes;
		notes.setRecipe(this);
	}
	protected boolean canEqual(final Object other) {
		return other instanceof Recipe;
	}

}
