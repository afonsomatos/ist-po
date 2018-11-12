package sth.app.person;

import java.util.Iterator;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;

import sth.core.Person;
import sth.core.SchoolManager;

/**
 * 4.2.4. Search person.
 */
public class DoSearchPerson extends Command<SchoolManager> {

  Input<String> _name;
  
  /**
   * @param receiver
   */
  public DoSearchPerson(SchoolManager receiver) {
    super(Label.SEARCH_PERSON, receiver);
    _name = _form.addStringInput(Message.requestPersonName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {

    _form.parse();
    Iterator<Person> it = _receiver.searchPerson(_name.value()).iterator();

    while(it.hasNext()){
      Person user = it.next();
      _display.addLine(user.toString());
    }
    
    _display.display();

  }

}
