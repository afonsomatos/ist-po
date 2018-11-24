package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;
import sth.core.NoSuchDisciplineIdException;
import sth.app.exception.NoSuchDisciplineException;

/**
 * 4.6.6. Show discipline surveys.
 */
public class DoShowDisciplineSurveys extends Command<SchoolManager> {

	private Input<String> _discipline;

	/**
	 * @param receiver
	 */
	public DoShowDisciplineSurveys(SchoolManager receiver) {
		super(Label.SHOW_DISCIPLINE_SURVEYS, receiver);
		_discipline = _form.addStringInput(Message.requestDisciplineName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		try{
			_form.parse();
			_display.addLine(_receiver.getDisciplineSurveyResults(_discipline.value()));
			_display.display();
		} catch(NoSuchDisciplineIdException nsde) {
			throw new NoSuchDisciplineException(_discipline.value());
		}
	}

}
