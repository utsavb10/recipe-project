package chela.springframework.recipeproject.controller;

import chela.springframework.recipeproject.domain.Category;
import chela.springframework.recipeproject.domain.UnitOfMeasure;
import chela.springframework.recipeproject.repository.CategoryRepository;
import chela.springframework.recipeproject.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@RequestMapping({"", "/"})
	public String getIndexPage(){
		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

		System.out.println("cat id is= "+ categoryOptional.get().getId());
		System.out.println("uom id is= "+ unitOfMeasureOptional.get().getId());

		return "index";
	}
}
