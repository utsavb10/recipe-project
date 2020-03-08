package chela.springframework.recipeproject.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	private String notes;

	@OneToOne
	private Recipe recipe;

}
