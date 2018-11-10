package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;
import sth.core.Person;
import sth.core.exception.NoSuchDisciplineIdException;

import sth.app.exception.NoSuchDisciplineException;

import java.util.List;

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
    
    try{
      
    List<Person> students;
    
    _form.parse();
    students = _receiver.showDisciplineStudents(_discipline.value());
    
    for(Person student : students)
      _display.addLine(student.toString());

    _display.display();

    } catch(NoSuchDisciplineIdException nsd) {
      throw new NoSuchDisciplineException(_discipline.value());
    }

  }

}
