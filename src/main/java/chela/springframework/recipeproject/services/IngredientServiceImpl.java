package chela.springframework.recipeproject.services;

import chela.springframework.recipeproject.command.IngredientCommand;
import chela.springframework.recipeproject.convertors.IngredientToIngredientCommand;
import chela.springframework.recipeproject.domain.Recipe;
import chela.springframework.recipeproject.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

	private final RecipeRepository recipeRepository;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;

	public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
		this.recipeRepository = recipeRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
	}

	@Override
	public IngredientCommand findIngredientByRecipeIdAndId(Long recipeId, Long id) {
		Optional<Recipe> savedRecipeOptional = recipeRepository.findById(recipeId);
		if(!savedRecipeOptional.isPresent()){
			log.error("No such recipe exists");
		}
		Optional<IngredientCommand> ingredientCommand= savedRecipeOptional.get().getIngredients().stream()
			.filter(ingredient -> ingredient.getId().equals(id))
			.map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
			.findFirst();

		if(ingredientCommand.isPresent()){
			log.error("No such ingredient exists ");
		}

		return ingredientCommand.get();
	}
}
