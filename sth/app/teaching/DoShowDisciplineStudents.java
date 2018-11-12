package sth.app.teaching;

import java.util.Collection;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.Person;
import sth.core.SchoolManager;
import sth.core.Student;
import sth.core.exception.NoSuchDisciplineIdException;

import sth.app.exception.NoSuchDisciplineException;

/**
 * 4.4.4. Show course students.
 */
public class DoShowDisciplineStudents extends Command<SchoolManager> {

	Input<String> _discipline;

	/**
	 * @param receiver
	 */
	public DoShowDisciplineStudents(SchoolManager receiver) {
		super(Label.SHOW_COURSE_STUDENTS, receiver);
		_discipline = _form.addStringInput(Message.requestDisciplineName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		
		_form.parse();
		
		try {
			Collection<Student> students = _receiver.showDisciplineStudents(_discipline.value());
		
			for(Student student : students)
				_display.addLine(student.toString());

			_display.display();
			
		} catch (NoSuchDisciplineIdException nsd) {
			throw new NoSuchDisciplineException(_discipline.value());
		}
	}

}
