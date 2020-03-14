package chela.springframework.recipeproject.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

	Category category;

	@BeforeEach
	public void setUp(){
		category = new Category();
	}

	@Test
	void getId() {
		Long id = 10L;
		category.setId(id);

		assertEquals(id, category.getId());
	}

	@Test
	void getDescription() {
	}

	@Test
	void getRecipeSet() {
	}
}