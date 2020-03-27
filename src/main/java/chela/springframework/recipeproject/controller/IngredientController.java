package chela.springframework.recipeproject.controller;

import chela.springframework.recipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {
	private final RecipeService recipeService;

	public IngredientController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping
	@RequestMapping({"/recipe/{id}/ingredients/", "/recipe/{id}/ingredients"})
	public String listIngredients(@PathVariable String id, Model model){
		model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));
		return "recipe/ingredient/list";
	}
}
