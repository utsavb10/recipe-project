package chela.springframework.recipeproject.services;

import chela.springframework.recipeproject.command.IngredientCommand;

public interface IngredientService {
	IngredientCommand findIngredientByRecipeIdAndId(Long recipeId, Long id);
	IngredientCommand saveIngredient(IngredientCommand ingredientCommand);
	void deleteIngredientByRecipeIdAndId(Long recipeId, Long id);
}
