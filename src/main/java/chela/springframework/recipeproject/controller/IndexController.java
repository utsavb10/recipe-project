package chela.springframework.recipeproject.controller;

import chela.springframework.recipeproject.domain.Category;
import chela.springframework.recipeproject.domain.UnitOfMeasure;
import chela.springframework.recipeproject.repository.CategoryRepository;
import chela.springframework.recipeproject.repository.UnitOfMeasureRepository;
import chela.springframework.recipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

//	private CategoryRepository categoryRepository;
//	private UnitOfMeasureRepository unitOfMeasureRepository;
	private final RecipeService recipeService;

	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({"", "/"})
	public String getIndexPage(Model model){
//		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
//		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
//
//		System.out.println("cat id is= "+ categoryOptional.get().getId());
//		System.out.println("uom id is= "+ unitOfMeasureOptional.get().getId());
		log.debug("calling IndexController");
		model.addAttribute("recipes", recipeService.getAllRecipes());

		return "index";
	}
}
