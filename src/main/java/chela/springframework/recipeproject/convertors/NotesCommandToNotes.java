package chela.springframework.recipeproject.convertors;

import chela.springframework.recipeproject.command.NotesCommand;
import chela.springframework.recipeproject.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
	@Synchronized
	@Nullable
	@Override
	public Notes convert(NotesCommand notesCommand) {
		if(notesCommand == null){
			return null;
		}
		final Notes notes = new Notes();
		notes.setId(notesCommand.getId());
		notes.setNotes(notesCommand.getNotes());
		return notes;
	}
}
