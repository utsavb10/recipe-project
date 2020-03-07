package chela.springframework.recipeproject.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String categoryName;

	@ManyToMany(mappedBy = "categories")
	private Set<Recipe> recipeSet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<Recipe> getRecipeSet() {
		return recipeSet;
	}

	public void setRecipeSet(Set<Recipe> recipeSet) {
		this.recipeSet = recipeSet;
	}
}
