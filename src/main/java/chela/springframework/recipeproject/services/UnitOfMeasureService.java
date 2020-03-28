package chela.springframework.recipeproject.services;

import chela.springframework.recipeproject.command.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
	Set<UnitOfMeasureCommand> findAllUoms();
}
