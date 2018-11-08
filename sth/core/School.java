package sth.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//FIXME import other classes if needed

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;

/**
 * School implementation.
 */
public class School implements java.io.Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  //FIXME define object fields (attributes and, possibly, associations)

  //FIXME implement constructors if needed
  
  private Parser _parser;

 //private static int _nextPersonId = 100000;

  School() {
    _parser = new Parser(this);
  }

  private List<Course> _courses = new ArrayList<Course>();
  private Map<Integer, Person> _persons = new HashMap<Integer, Person>();

  /**
   * @param filename
   * @throws BadEntryException
   * @throws IOException
   */
  void importFile(String filename) throws IOException, BadEntryException {
    _parser.parseFile(filename);
  }
  
  //FIXME implement other methods

  List<Person> getAllUsers() {
    return new ArrayList<>(_persons.values());
  }

  Person getPerson(int id) throws NoSuchPersonIdException {
	  if (!_persons.containsKey(id))
		  throw new NoSuchPersonIdException(id);
	  return _persons.get(id);
  }

  void addPerson(Person person) {
    _persons.put(person.getId(), person);
  }
  
  	Course getCourse(String name) {
  		for (Course c : _courses)
  			if (c.getName() == name)
  				return c;
  		return null;
  	}
  
  	void addCourse(Course course) {
  		_courses.add(course);
  	}

  	Course parseCourse(String header) {
  		Course course = getCourse(header);
  		if (course == null) {
  			course = new Course(header);
  			addCourse(course);
  		}
  		return course;
	}

}
