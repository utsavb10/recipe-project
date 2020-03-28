package chela.springframework.recipeproject.services;

import chela.springframework.recipeproject.command.IngredientCommand;

public interface IngredientService {
	IngredientCommand findIngredientByRecipeIdAndId(Long recipeId, Long id);
}
