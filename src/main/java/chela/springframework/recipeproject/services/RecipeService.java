package chela.springframework.recipeproject.services;

import chela.springframework.recipeproject.command.RecipeCommand;
import chela.springframework.recipeproject.domain.Recipe;
import java.util.Optional;
import java.util.Set;

public interface RecipeService {
	Set<Recipe> getAllRecipes();
	Recipe findById(Long id);
	RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
	RecipeCommand findRecipeCommandById(Long id);
	void deleteRecipeById(Long id);
}
