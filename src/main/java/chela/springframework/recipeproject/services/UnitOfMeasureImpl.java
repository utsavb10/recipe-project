package chela.springframework.recipeproject.services;

import chela.springframework.recipeproject.command.UnitOfMeasureCommand;
import chela.springframework.recipeproject.convertors.UomToUomCommand;
import chela.springframework.recipeproject.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureImpl implements UnitOfMeasureService{
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final UomToUomCommand uomToUomCommand;

	public UnitOfMeasureImpl(UnitOfMeasureRepository unitOfMeasureRepository, UomToUomCommand uomToUomCommand) {
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.uomToUomCommand = uomToUomCommand;
	}

	@Override
	public Set<UnitOfMeasureCommand> findAllUoms() {
		return StreamSupport.stream(unitOfMeasureRepository.findAll()
			.spliterator(), false)
			.map(uomToUomCommand::convert)
			.collect(Collectors.toSet());
	}
}
