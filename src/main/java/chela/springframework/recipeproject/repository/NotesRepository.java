package chela.springframework.recipeproject.repository;

import chela.springframework.recipeproject.domain.Notes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface NotesRepository extends CrudRepository<Notes, Long> {
}
