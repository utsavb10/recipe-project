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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
			Recipe recipe = optionalRecipe.get();

			Optional<Ingredient> ingredientOptional= recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
				.findFirst();

			if(ingredientOptional.isPresent()){
				Ingredient savedIngredient = ingredientOptional.get();
				savedIngredient.setId(ingredientCommand.getId());
				savedIngredient.setDescription(ingredientCommand.getDescription());
				savedIngredient.setAmount(ingredientCommand.getAmount());
				savedIngredient.setRecipe(optionalRecipe.get());
				savedIngredient.setUom(
					unitOfMeasureRepository.findById(ingredientCommand.getUnitOfMeasureCommand().getId())
					.orElseThrow(() -> new RuntimeException("UOM not found")));
			} else{
				Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
				recipe.addIngredient(ingredient);
				ingredient.setRecipe(recipe);
			}
			Recipe savedRecipe = recipeRepository.save(recipe);

			Optional<Ingredient> optionalIngredient = savedRecipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
				.findFirst();

			if(!optionalIngredient.isPresent()){
				optionalIngredient = savedRecipe.getIngredients().stream()
					.filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientCommand.getDescription()))
					.filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientCommand.getAmount()))
					.filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(ingredientCommand.getUnitOfMeasureCommand().getId()))
					.findFirst();
			}

			return ingredientToIngredientCommand.convert(optionalIngredient.get());
		}
	}

	@Override
	@Transactional
	public void deleteIngredientByRecipeIdAndId(Long recipeId, Long id) {
		Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
		if(!optionalRecipe.isPresent()){
			log.error("Recipe doen't exist");
		}
		Recipe savedRecipe = optionalRecipe.get();
		Optional<Ingredient> optionalIngredient = savedRecipe.getIngredients().stream()
			.filter(ingredient -> ingredient.getId().equals(id))
			.findFirst();

		if(optionalIngredient.isPresent()){
			Ingredient deleteIngredient = optionalIngredient.get();
			deleteIngredient.setRecipe(null);
			savedRecipe.getIngredients().remove(deleteIngredient);
			recipeRepository.save(savedRecipe);
		}
		else{
			log.debug("No ingredient to delete");
		}
	}
}
