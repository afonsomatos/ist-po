package sth.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import sth.core.exception.DuplicateProjectIdException;
import sth.core.exception.NoSuchProjectIdException;

public class Discipline implements Serializable, Comparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _name;
	
	//not used?
	//private int _capacity;

	private Course _course;
	private Set<Student> _students = new TreeSet<Student>();
	private List<Teacher> _teachers = new ArrayList<Teacher>();
	private List<Project> _projects = new ArrayList<>();

	Discipline(String name, Course course) {
		_name = name;
		_course = course;
		//_capacity = capacity;
	}

	Collection<Student> getStudents() {
		return _students;
	}
	
	String getName() {
		return _name;
	}

	Course getCourse() {
		return _course;
	}

	String show() {
		String txt = String.format("* %s - %s", _course.getName(), _name);
		return txt;
	}
	
	void addTeacher(Teacher teacher) {
		_teachers.add(teacher);
	}

	void enrollStudent(Student student) {
		_students.add(student);
	}
	
	Project getProject(String name) throws NoSuchProjectIdException {
		for (Project p : _projects)
			if (p.getName().equals(name))
				return p;
		throw new NoSuchProjectIdException(name);
	}

	void createProject(String name) throws DuplicateProjectIdException {
		// check if project exists
		for (Project p : _projects)
			if (p.getName().equals(name))
				throw new DuplicateProjectIdException(name);
		_projects.add(new Project(name));
	}
	
	@Override
	public int compareTo(Object o) {
		Discipline d = (Discipline) o;
		
		// try to order by course name
		int compareCourse = _course.getName().compareTo(d.getCourse().getName());
		if (compareCourse != 0)
			return compareCourse;
		
		// order by discipline name
		return getName().compareTo(d.getName());
	}

}