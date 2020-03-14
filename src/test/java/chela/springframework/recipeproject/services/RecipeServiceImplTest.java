package chela.springframework.recipeproject.services;

import chela.springframework.recipeproject.domain.Recipe;
import chela.springframework.recipeproject.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks( this); //Initialize Mocks from this class

		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	void getAllRecipes() {
		Recipe recipe = new Recipe();
		HashSet<Recipe> recipeData = new HashSet<>();
		recipeData.add(recipe);

		when(recipeService.getAllRecipes()).thenReturn(recipeData);
		Set<Recipe> recipes = recipeService.getAllRecipes();

		assertEquals(recipes, recipeData);
		assertEquals(recipes.size(), 1);

		//check that recipeRepository was called only once
		verify(recipeRepository, times(1)).findAll();
	}
}