package chela.springframework.recipeproject.services;

import chela.springframework.recipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {
	Set<Recipe> getAllRecipes();
}
