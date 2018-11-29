package sth.app.person;

import java.util.Collection;

import pt.tecnico.po.ui.Command;
import sth.core.Person;
import sth.core.SchoolManager;

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

			Collection<Person> users = _receiver.getAllUsers();
			
			for(Person user : users)
				_display.addLine(user.toString());

			_display.display();
		}

}
