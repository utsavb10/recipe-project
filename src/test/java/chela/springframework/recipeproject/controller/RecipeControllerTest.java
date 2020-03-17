package chela.springframework.recipeproject.controller;

import chela.springframework.recipeproject.domain.Recipe;
import chela.springframework.recipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {

	RecipeController recipeController;

	@Mock
	RecipeService recipeService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		recipeController = new RecipeController(recipeService);
	}

	@Test
	public void testMockMVC() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(10L);

		when(recipeService.findById(anyLong())).thenReturn(recipe);

		MockMvc recipeMockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

		recipeMockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/10"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/show"));
	}

}