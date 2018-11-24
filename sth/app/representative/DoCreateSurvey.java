package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.app.exception.DuplicateSurveyException;

/**
 * 4.5.1. Create survey.
 */
public class DoCreateSurvey extends sth.app.common.ProjectCommand {

	/**
	 * @param receiver
	 */
	public DoCreateSurvey(SchoolManager receiver) {
		super(Label.CREATE_SURVEY, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */ 
	@Override
	public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
		try{	
			_receiver.createProject(_discipline.value(), _project.value());
		} catch(sth.core.exception.DuplicateSurveyException) {
			throw new DuplicateSurveyException(_discipline.value(), _project.value());
		}
	}

}
