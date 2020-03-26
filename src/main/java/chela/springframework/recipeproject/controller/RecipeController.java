package chela.springframework.recipeproject.controller;

import chela.springframework.recipeproject.command.RecipeCommand;
import chela.springframework.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/{id}/show")
	public String getRecipeById(@PathVariable("id") String id, Model model){
		model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
		return "recipe/show";
	}

	@RequestMapping("/recipe/new")
	public String newRecipe(Model model){
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
	}

	@PostMapping
	@RequestMapping("recipe")
	public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand){
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);

		return "redirect:/recipe/" + savedRecipeCommand.getId()+"/show";
	}
}
