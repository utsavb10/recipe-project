package chela.springframework.recipeproject.services;

import chela.springframework.recipeproject.domain.Recipe;
import chela.springframework.recipeproject.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getAllRecipes() {
		Set<Recipe> hashSet = new HashSet<>();
		recipeRepository.findAll().forEach(recipe -> {
			hashSet.add(recipe);
		});
//		recipeRepository.findAll().iterator().forEachRemaining(getAllRecipes()::add);
		return hashSet;
	}
}
