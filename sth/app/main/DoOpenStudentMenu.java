package sth.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Menu;
import pt.tecnico.po.ui.ValidityPredicate;
import sth.app.student.StudentMenu;
import sth.core.SchoolManager;

/**
 * 4.1.2. Open student menu.
 */
public class DoOpenStudentMenu extends Command<SchoolManager> {

	/**
	 * @param receiver
	 */
	public DoOpenStudentMenu(SchoolManager receiver) {
		super(Label.OPEN_STUDENT_MENU, receiver, new ValidityPredicate<SchoolManager>(receiver) {
			@Override
			public boolean isValid() {
				return _receiver.isLoggedUserStudent();
			}
		});
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		Menu menu = new StudentMenu(_receiver);
		menu.open();
	}

}
