package sth.app.person;

import java.util.Iterator;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;

import sth.core.SchoolManager;
import sth.core.Person;

/**
 * 4.2.3. Show all persons.
 */
public class DoShowAllPersons extends Command<SchoolManager> {

  /**
   * @param receiver
   */
    public DoShowAllPersons(SchoolManager receiver) {
      super(Label.SHOW_ALL_PERSONS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  
    @Override
    public final void execute() {

      Iterator<Person> it = _receiver.getAllUsers().iterator();
      
      while(it.hasNext()){
        Person user = it.next();
          _display.addLine(user.toString());
      }
      
      _display.display();
  }

}
