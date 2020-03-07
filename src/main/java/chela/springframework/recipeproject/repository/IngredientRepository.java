package chela.springframework.recipeproject.repository;

import chela.springframework.recipeproject.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
