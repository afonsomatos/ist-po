package sth.app.student;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.app.exception.NoSuchProjectException;
import sth.core.SchoolManager;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

/**
 * 4.5.1. Deliver project.
 */
public class DoDeliverProject extends sth.app.common.ProjectCommand {

	private Input<String> _submission;

	/**
	 * @param receiver
	 */
	public DoDeliverProject(SchoolManager receiver) {
		super(Label.DELIVER_PROJECT, receiver);
		_submission = _form.addStringInput(Message.requestDeliveryMessage());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, DialogException {
		try{
			_receiver.deliverProject(_submission.value(), _discipline.value(), _project.value());
		} catch(NoSuchProjectIdException nspe){
			throw new NoSuchProjectException(_discipline.value(), _project.value());
		}
	}

}
