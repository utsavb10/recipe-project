package chela.springframework.recipeproject.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private BigDecimal amount;
	@OneToOne
	private UnitOfMeasure uom;

	@ManyToOne
	private Recipe recipe;

	public Ingredient() {
	}

	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
		this.description = description;
		this.amount = amount;
		this.uom = uom;
		this.recipe = recipe;
	}

}
