package chela.springframework.recipeproject.services;

import chela.springframework.recipeproject.command.IngredientCommand;
import chela.springframework.recipeproject.convertors.IngredientCommandToIngredient;
import chela.springframework.recipeproject.convertors.IngredientToIngredientCommand;
import chela.springframework.recipeproject.domain.Ingredient;
import chela.springframework.recipeproject.domain.Recipe;
import chela.springframework.recipeproject.repository.RecipeRepository;
import chela.springframework.recipeproject.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient) {
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
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

		if(!ingredientCommand.isPresent()){
			log.error("No such ingredient exists ");
		}

		return ingredientCommand.get();
	}

	@Transactional
	@Override
	public IngredientCommand saveIngredient(IngredientCommand ingredientCommand) {
		Optional<Recipe> optionalRecipe = recipeRepository.findById(ingredientCommand.getRecipeId());
		if(!optionalRecipe.isPresent()){
			log.error("Invalid Operation: Recipe does not exist");
			return new IngredientCommand();
		} else{
			Optional<Ingredient> ingredientOptional= optionalRecipe.get().getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
				.findFirst();

			if(ingredientOptional.isPresent()){
				Ingredient savedIngredient = ingredientOptional.get();
				savedIngredient.setId(ingredientCommand.getId());
				savedIngredient.setDescription(ingredientCommand.getDescription());
				savedIngredient.setAmount(ingredientCommand.getAmount());
				savedIngredient.setRecipe(optionalRecipe.get());
				savedIngredient.setUom(unitOfMeasureRepository.findById(ingredientCommand.getUnitOfMeasureCommand().getId())
					.orElseThrow(() -> new RuntimeException("UOM not found")));
			} else{
				optionalRecipe.get().addIngredient(Objects.requireNonNull(ingredientCommandToIngredient.convert(ingredientCommand)));
			}
			Recipe savedRecipe = recipeRepository.save(optionalRecipe.get());

			return savedRecipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
				.findFirst().get();
		}
	}
}
