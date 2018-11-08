package sth.core;

//FIXME import other classes if needed

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

/**
 * School implementation.
 */
public class School implements java.io.Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  //FIXME define object fields (attributes and, possibly, associations)

  //FIXME implement constructors if needed
  
 // private static int _nextPersonId = 100000;

  private Map<Integer, Person> persons = new HashMap<Integer, Person>();

  /**
   * @param filename
   * @throws BadEntryException
   * @throws IOException
   */
  void importFile(String filename) throws IOException, BadEntryException {
    //FIXME implement text file reader
  }
  
  //FIXME implement other methods

  void addPerson(Person person) {
    persons.put(person.getId(), person);
  }

	Course parseCourse(String header) {
		// TODO
		return null;
	}

}
