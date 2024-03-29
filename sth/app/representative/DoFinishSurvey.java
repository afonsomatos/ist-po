package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import sth.app.exception.FinishingSurveyException;
import sth.app.exception.NoSurveyException;
import sth.core.SchoolManager;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.survey.NoSurveyIdException;
import sth.core.exception.survey.SurveyFinishedIdException;

/**
 * 4.6.5. Finish survey.
 */
public class DoFinishSurvey extends sth.app.common.ProjectCommand {

	/**
	 * @param receiver
	 */
	public DoFinishSurvey(SchoolManager receiver) {
		super(Label.FINISH_SURVEY, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */ 
	@Override
	public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
		try{
			_receiver.finishSurvey(_discipline.value(), _project.value());
		} catch(NoSurveyIdException nse) {
			throw new NoSurveyException(_discipline.value(), _project.value());
		} catch(SurveyFinishedIdException fse) {
			throw new FinishingSurveyException(_discipline.value(), _project.value());
		}
	}

}
