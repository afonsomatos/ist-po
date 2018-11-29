package sth.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import sth.core.exception.DuplicateProjectIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.survey.NoSurveyIdException;


/**
 * Discipline Implementation.
 */
public class Discipline implements Serializable, Comparable<Discipline> {
	
	/** Serial number for serialization */
	private static final long serialVersionUID = 1L;

	/** Discipline name */
	private String _name;

	/** Course where the discipline is registered */
	private Course _course;
	
	/** Set of students of the discipline */
	private Set<Student> _students = new TreeSet<Student>();
	
	/** List of students of the discipline */
	private List<Teacher> _teachers = new ArrayList<Teacher>();
	
	/** List of projects of the discipline */
	private Set<Project> _projects = new TreeSet<>();


	/**
	 * Discipline constructor.

	 * @param name
	 * @param course
	 */
	Discipline(String name, Course course) {
		_name = name;
		_course = course;
	}

	/**
	 * @return Collection of discipline's students
	 */
	Collection<Student> getStudents() {
		return _students;
	}
	
	/**
	 * @return discipline's name
	 */
	String getName() {
		return _name;
	}

	/**
	 * @return course where the discipline is registered
	 */
	Course getCourse() {
		return _course;
	}

	/**
	 * @return string that represents the discipline
	 */
	String show() {
		String txt = String.format("* %s - %s", _course.getName(), _name);
		return txt;
	}
	
	/**
	 * Adds a teacher to the discipline

	 * @param teacher
	 */
	void addTeacher(Teacher teacher) {
		_teachers.add(teacher);
	}

	/**
	 * Adds a student to the discipline

	 * @param student
	 */
	void enrollStudent(Student student) {
		if(_course.getStudents().contains(student))
			_students.add(student);
	}
	
	/**
	 * @param name
	 * @return project with the given name
	 * @throws NoSuchProjectIdException if the given project doesn't exist on the discipline
	 */
	Project getProject(String name) throws NoSuchProjectIdException {
		for (Project p : _projects)
			if (p.getName().equals(name))
				return p;
		throw new NoSuchProjectIdException(name);
	}

	/**
	 * Creates a project with the given name in the discipline

	 * @param name
	 * @throws DuplicateProjectIdException if the project already exists
	 */
	void createProject(String name) throws DuplicateProjectIdException {
		// check if project exists
		for (Project p : _projects)
			if (p.getName().equals(name))
				throw new DuplicateProjectIdException(name);
		_projects.add(new Project(name, this));
	}
	

	/**
	 * @param o
	 * @return an int that represents the sorting precedence of the disciplines
	 */
	@Override
	public int compareTo(Discipline d) {
		
		// try to order by course name
		int compareCourse = _course.getName().compareTo(d.getCourse().getName());
		if (compareCourse != 0)
			return compareCourse;
		
		// order by discipline name
		return getName().compareTo(d.getName());
	}
	
	String getSurveyResults() {
		String msg = "";
		for (Project p : _projects) {
			try {
				msg += p.getSurvey().getSummary(true);
			} catch (NoSurveyIdException e) {
				// don't add
			}
		}
		return msg;

	}
}