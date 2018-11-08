package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Display;

import sth.core.SchoolManager;
import sth.app.person.Message;
import sth.core.Person;

import java.util.List;

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

    List<Person> users;
    
    _form.parse();
    users = _receiver.searchPerson(_name.value());
    
    for(Person user : users)
      _display.addLine(user.toString());

    _display.display();
  }

}
