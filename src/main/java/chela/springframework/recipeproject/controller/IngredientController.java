package chela.springframework.recipeproject.controller;

import chela.springframework.recipeproject.command.IngredientCommand;
import chela.springframework.recipeproject.command.RecipeCommand;
import chela.springframework.recipeproject.command.UnitOfMeasureCommand;
import chela.springframework.recipeproject.services.IngredientService;
import chela.springframework.recipeproject.services.RecipeService;
import chela.springframework.recipeproject.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {
	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	private final UnitOfMeasureService unitOfMeasureService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
	}

	@GetMapping
	@RequestMapping({"/recipe/{id}/ingredients/", "/recipe/{id}/ingredients"})
	public String listIngredients(@PathVariable String id, Model model){
		model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));
		return "recipe/ingredient/list";
	}

	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredient/{id}/show")
	public String showIngredient(@PathVariable String recipeId, @PathVariable String id,Model model){
		model.addAttribute("ingredient", ingredientService.findIngredientByRecipeIdAndId(Long.valueOf(recipeId), Long.valueOf(id)));
		return "recipe/ingredient/show";
	}

	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredient/{id}/update")   //fetches existing IngredientCommand info to ingredientform for update
	public String updateIngredient(@PathVariable String recipeId, @PathVariable String id, Model model){
		model.addAttribute("ingredient", ingredientService.findIngredientByRecipeIdAndId(Long.valueOf(recipeId), Long.valueOf(id)));
		model.addAttribute("uomList", unitOfMeasureService.findAllUoms());
		return "recipe/ingredient/ingredientform";
	}

	@PostMapping
	@RequestMapping("/recipe/{recipeId}/ingredient")
	public String saveOrUpdateIngredient(@ModelAttribute IngredientCommand ingredientCommand){
		IngredientCommand savedIngredientCommand = ingredientService.saveIngredient(ingredientCommand);

		log.debug("RecipeID: "+ savedIngredientCommand.getRecipeId());
		log.debug("IngredientID: "+ savedIngredientCommand.getId());

		return "redirect:/recipe/"+savedIngredientCommand.getRecipeId()+"/ingredient/"+savedIngredientCommand.getId()+"/show";
	}

	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/new")  //fetches an empty IngredientCommand info to ingredientform for create
	public String newIngredient(@PathVariable String recipeId, Model model){
		RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(recipeId));

		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeId(recipeCommand.getId());
		model.addAttribute("ingredient", ingredientCommand);

		ingredientCommand.setUnitOfMeasureCommand(new UnitOfMeasureCommand());

		model.addAttribute("uomList", unitOfMeasureService.findAllUoms());
		return "recipe/ingredient/ingredientform";
	}

	@RequestMapping("/recipe/{recipeId}/ingredient/{id}/delete")
	public String deleteIngredient(@PathVariable String recipeId, @PathVariable String id){
		ingredientService.deleteIngredientByRecipeIdAndId(Long.valueOf(recipeId), Long.valueOf(id));
		return "redirect:/recipe/"+recipeId+"/ingredients";
	}
}
