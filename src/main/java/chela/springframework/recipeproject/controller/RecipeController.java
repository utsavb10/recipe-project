package chela.springframework.recipeproject.controller;

import chela.springframework.recipeproject.command.RecipeCommand;
import chela.springframework.recipeproject.exception.NotFoundException;
import chela.springframework.recipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
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

	@RequestMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model){
		model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));

		return "recipe/recipeform";
	}

	@RequestMapping("recipe/{id}/delete")
	public String deleteRecipe(@PathVariable String id){
		recipeService.deleteRecipeById(Long.valueOf(id));

		return "redirect:/";
	}

	//writing a view fro 404errors
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(){
		log.error("Handling a not found exception");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("404error");
		return mav;
	}
}
