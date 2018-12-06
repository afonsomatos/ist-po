package sth.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sth.core.exception.NoSuchDisciplineIdException;


/**
 * Course implementation.
 */
public class Course implements Serializable {

	/** Serial number for serialization */
	private static final long serialVersionUID = 1L;

	/** Name of the course */
	private String _name;

	/** List of disciplines of the course */
	private List<Discipline> _disciplines   = new ArrayList<Discipline>();
	
	/** List of students of the course */
	private List<Student> _students         = new ArrayList<Student>();
	
	/** List of representative students of the course */
	private List<Student> _representatives  = new ArrayList<Student>();

	/**
	 * Constructor of the Course.

	 * @param name
	 */		
	Course(String name) {
		_name = name;
	}

	Discipline getDiscipline(String name) throws NoSuchDisciplineIdException {
		for (Discipline d : _disciplines) {
			if (d.getName().equals(name))
				return d;
		}
		throw new NoSuchDisciplineIdException(name);
	}
	
	
	/**
	 * Adds a student to the course.

	 * @param student
	 */
	void addStudent(Student student) {
		_students.add(student);
	}

	/**
	 * Adds a representative student to the course

	 * @param student
	 */
	void addRepresentative(Student student) {
		if(_representatives.size() < 7){
			if(_representatives.contains(student))
				return;
			_representatives.add(student);
			addStudent(student);
			for (Discipline d : _disciplines)
				d.attach(student);
		}
	}

	/**
	 * Removes a representative student to the course

	 * @param student
	 */
	void removeRepresentative(Student student) {
		_representatives.remove(student);
	}

	/**
	 * @return the name of the course
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @return students of the course
	 */
	public List<Student> getStudents(){	
		return _students;	
	}

	/**
	 * Checks if the given discipline exists if it doesn't exist creates it
	 * and adds it to the Course.

	 * @param header
	 * @return the discipline with the given header
	 */
	Discipline parseDiscipline(String header) {
		for (Discipline d : _disciplines) {
			if (d.getName().equals(header))
				return d;
		}
		Discipline x = new Discipline(header, this);
		_disciplines.add(x);
		for (Student s : _representatives)
			x.attach(s);
		return x;
	}
	
	Collection<Student> getRepresentatives() {
		return _representatives;
	}

}