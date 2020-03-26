package chela.springframework.recipeproject.convertors;

import chela.springframework.recipeproject.command.IngredientCommand;
import chela.springframework.recipeproject.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	private final UomToUomCommand uomToUomCommand;

	public IngredientToIngredientCommand(UomToUomCommand uomToUomCommand) {
		this.uomToUomCommand = uomToUomCommand;
	}

	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient ingredient) {
		if(ingredient == null){
			return null;
		}
		final IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(ingredient.getId());
		ingredientCommand.setAmount(ingredient.getAmount());
		ingredientCommand.setDescription(ingredient.getDescription());
		ingredientCommand.setUnitOfMeasureCommand(uomToUomCommand.convert(ingredient.getUom()));
		return ingredientCommand;
	}
}
