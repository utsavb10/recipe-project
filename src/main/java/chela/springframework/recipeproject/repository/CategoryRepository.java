package chela.springframework.recipeproject.repository;

import chela.springframework.recipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
