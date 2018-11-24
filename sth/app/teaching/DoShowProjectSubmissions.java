package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import sth.core.SchoolManager;
import sth.core.Submission;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

import java.util.Collection;

/**
 * 4.4.3. Show project submissions.
 */
public class DoShowProjectSubmissions extends sth.app.common.ProjectCommand {
	/**
	 * @param receiver
	 */
	public DoShowProjectSubmissions(SchoolManager receiver) {
	super(Label.SHOW_PROJECT_SUBMISSIONS, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	@Override
	public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
		
		Collection<Submission> projectSubmissions;
		projectSubmissions = _receiver.getProjectSubmissions(_discipline.value(),
															 _project.value());
		
		_display.addLine(_discipline.value() + " - " + _project.value());
		for(Submission proj : projectSubmissions)
			_display.addLine("* " + proj.toString());

		_display.display();
	}

}
