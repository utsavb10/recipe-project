package chela.springframework.recipeproject.services;

import chela.springframework.recipeproject.domain.Recipe;
import chela.springframework.recipeproject.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getAllRecipes() {
		log.debug("RecipeService called and working");
		Set<Recipe> hashSet = new HashSet<>();
		recipeRepository.findAll().forEach(recipe -> {
			hashSet.add(recipe);
		});
//		recipeRepository.findAll().iterator().forEachRemaining(getAllRecipes()::add);
		return hashSet;
	}

	@Override
	public Recipe findById(Long id) {
		Optional<Recipe> getRecipe = recipeRepository.findById(id);
		if(!getRecipe.isPresent()){
			throw new RuntimeException("Recipe not found");
		}
		return getRecipe.get();
	}
}
