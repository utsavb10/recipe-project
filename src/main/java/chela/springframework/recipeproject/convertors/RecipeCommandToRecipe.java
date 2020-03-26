package chela.springframework.recipeproject.convertors;

import chela.springframework.recipeproject.command.RecipeCommand;
import chela.springframework.recipeproject.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private final NotesCommandToNotes notesCommandToNotes;
	private final CategoryCommandToCategory categoryCommandToCategory;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes, CategoryCommandToCategory categoryCommandToCategory, IngredientCommandToIngredient ingredientCommandToIngredient) {
		this.notesCommandToNotes = notesCommandToNotes;
		this.categoryCommandToCategory = categoryCommandToCategory;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand recipeCommand) {
		if(recipeCommand == null){
			return null;
		}
		final Recipe recipe = new Recipe();
		recipe.setId(recipeCommand.getId());
		recipe.setCookTime(recipeCommand.getCookTime());
		recipe.setDescription(recipeCommand.getDescription());
		recipe.setDifficulty(recipeCommand.getDifficulty());
		recipe.setDirections(recipeCommand.getDirections());
		recipe.setNotes(notesCommandToNotes.convert(recipeCommand.getNotesCommand()));
		recipe.setPrepTime(recipeCommand.getPrepTime());
		recipe.setServings(recipeCommand.getServings());
		recipe.setSource(recipeCommand.getSource());
		recipe.setUrl(recipeCommand.getUrl());

		if(recipeCommand.getCategoryCommandSet() !=null && recipeCommand.getCategoryCommandSet().size()>0){
			recipeCommand.getCategoryCommandSet().forEach(categoryCommand -> {
				recipe.getCategories().add(categoryCommandToCategory.convert(categoryCommand));
			});
		}
		if(recipeCommand.getIngredientCommandSet()!=null && recipeCommand.getIngredientCommandSet().size()>0){
			recipeCommand.getIngredientCommandSet().forEach(ingredientCommand -> {
				recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredientCommand));
			});
		}

		return recipe;
	}
}
