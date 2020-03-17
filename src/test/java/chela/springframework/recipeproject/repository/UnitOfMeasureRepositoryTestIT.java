package chela.springframework.recipeproject.repository;

import chela.springframework.recipeproject.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTestIT {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Before
	public void setUp() throws Exception {

	}

	@Test
//	@DirtiesContext         Context gets lost after this test, takes more time again in next test to load Context
	public void findByDescription() {
		Optional<UnitOfMeasure> uoms = unitOfMeasureRepository.findByDescription("Teaspoon");

		assertEquals("Teaspoon", uoms.get().getDescription());
	}

	@Test
	public void findByDescriptionCup() {
		Optional<UnitOfMeasure> uoms = unitOfMeasureRepository.findByDescription("Cup");

		assertEquals("Cup", uoms.get().getDescription());
	}
}