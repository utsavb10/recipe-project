package chela.springframework.recipeproject.command;

import chela.springframework.recipeproject.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
	private Long id;
	private String description;
	private int prepTime;
	private int cookTime;
	private int servings;
	private String source;
	private String url;
	private String directions;
	private Difficulty difficulty;
	private NotesCommand notesCommand;
	private Set<IngredientCommand> ingredientCommandSet = new HashSet<>();
	private Set<CategoryCommand> categoryCommandSet = new HashSet<>();
}
