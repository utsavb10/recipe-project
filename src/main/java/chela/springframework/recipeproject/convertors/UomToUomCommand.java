package chela.springframework.recipeproject.convertors;

import chela.springframework.recipeproject.command.UnitOfMeasureCommand;
import chela.springframework.recipeproject.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UomToUomCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
		if(unitOfMeasure == null){
			return null;
		}
		final UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(unitOfMeasure.getId());
		uomCommand.setDescription(unitOfMeasure.getDescription());
		return uomCommand;
	}
}
