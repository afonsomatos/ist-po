package sth.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;

/**
 * School implementation.
 */
public class School implements java.io.Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	/** Parser responsible to parse the import file information */
	private Parser _parser;

	/** List responsible to store all courses of the school */
	private List<Course> _courses = new LinkedList<Course>();

	/** Map resonsible to store all studens of the school */
	private Map<Integer, Person> _persons = new HashMap<Integer, Person>();


	/**
	 * School constructor. Initializes the parser.
	 */
	School() {
		_parser = new Parser(this);
	}

	/**
	 * Parses the file which contains the starting information of the program.

	 * @param filename
	 * @throws BadEntryException
	 * @throws IOException
	 */
	void importFile(String filename) throws IOException, BadEntryException {
		_parser.parseFile(filename);
	}

	/**
	 * @return a list with all the people of the school sorted by id
	 */
	List<Person> getAllUsers() {
		List<Person> users = new ArrayList<>(_persons.values());
		users.sort((p1, p2) -> p1.getId()- p2.getId());
		return users;
	}


	/**
	 * @param id identifier of the person to obtain
	 * @return a person with the given identifier
	 * @throws NoSuchPersonIdException if there is no person with the given identifier
	 */
	Person getPerson(int id) throws NoSuchPersonIdException {
		if (!_persons.containsKey(id))
			throw new NoSuchPersonIdException(id);
		return _persons.get(id);
	}


	/**
	 * Adds the given person to the map of people in the school.

	 * @param person
	 */
	void addPerson(Person person) {
		_persons.put(person.getId(), person);
	}
	

	/**
	 * @param name name of the course
	 * @return the course with the given name, null if there is no course
	 */
	Course getCourse(String name) {
		for (Course c : _courses)
			if (c.getName().equals(name))
				return c;
		return null;
	}
	

	/**
	 * Adds the given course to the list of courses in the school.

	 * @param course
	 */
	void addCourse(Course course) {
		_courses.add(course);
	}


	/**
	 * @param name name to search in the school
	 * @return Collection of people whose name contains the given name 
	 */
	Collection<Person> searchPerson(String name) {
		return _persons.values()
				.stream()
				.filter(p -> p.getName()
				.contains(name))
				.sorted((a, b) -> a.getName().compareTo(b.getName()))
				.collect(Collectors.toList());
	}
	
	
	/**
	 * Checks if the given course exists if it doesn't exist creates it and adds
	 * it to the school.

	 * @param header
	 * @return the course with the given header
	 */	
	Course parseCourse(String header) {
		Course course = getCourse(header);
		if (course == null) {
			course = new Course(header);
			addCourse(course);
		}
		return course;
	}

}
