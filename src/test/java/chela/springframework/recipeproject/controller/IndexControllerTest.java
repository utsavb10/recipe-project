package chela.springframework.recipeproject.controller;

import chela.springframework.recipeproject.domain.Recipe;
import chela.springframework.recipeproject.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndexControllerTest {

	IndexController indexController;

	@Mock
	RecipeService recipeService;

	@Mock
	Model model;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		indexController = new IndexController(recipeService);
	}

	@Test
	void getIndexPage() {
		//given
		Recipe recipe = new Recipe();
		HashSet<Recipe> recipeData = new HashSet<>();
		recipeData.add(recipe);

		when(recipeService.getAllRecipes()).thenReturn(recipeData);
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

		//when
		String test = indexController.getIndexPage(model);

		//then
		assertEquals("index", test);
		verify(recipeService, times(1)).getAllRecipes();
//		verify(model, times(1)).addAttribute(eq("recipes"), anySet());
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		Set<Recipe> setRecipe = argumentCaptor.getValue();
		assertEquals(1, setRecipe.size());
	}
}