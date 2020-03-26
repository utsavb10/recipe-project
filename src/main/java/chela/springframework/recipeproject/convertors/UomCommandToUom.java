package chela.springframework.recipeproject.convertors;

import chela.springframework.recipeproject.command.UnitOfMeasureCommand;
import chela.springframework.recipeproject.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UomCommandToUom implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
		if(unitOfMeasureCommand == null){
			return null;
		}

		final UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(unitOfMeasureCommand.getId());
		uom.setDescription(unitOfMeasureCommand.getDescription());
		return uom;
	}
}
