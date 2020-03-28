package chela.springframework.recipeproject.convertors;

import chela.springframework.recipeproject.command.IngredientCommand;
import chela.springframework.recipeproject.domain.Ingredient;
import chela.springframework.recipeproject.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

	private final UomCommandToUom uomCommandToUom;

	public IngredientCommandToIngredient(UomCommandToUom uomCommandToUom) {
		this.uomCommandToUom = uomCommandToUom;
	}

	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand ingredientCommand) {
		if(ingredientCommand == null){
			return null;
		}
		final Ingredient ingredient = new Ingredient();
		ingredient.setId(ingredientCommand.getId());
		ingredient.setAmount(ingredientCommand.getAmount());
		ingredient.setDescription(ingredientCommand.getDescription());
		ingredient.setUom(uomCommandToUom.convert(ingredientCommand.getUnitOfMeasureCommand()));

		//flawed code -_-
		if(ingredientCommand.getRecipeId() !=null){
			Recipe recipe = new Recipe();
			recipe.setId(ingredientCommand.getRecipeId());
			ingredient.setRecipe(recipe);
			recipe.addIngredient(ingredient);
		}

		return ingredient;
	}
}
