package sth.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import sth.core.exception.DuplicateProjectIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.survey.NoSurveyIdException;

public class Discipline implements Subject<Notification>, Serializable, Comparable<Discipline> {
	
	private static final long serialVersionUID = 1L;

	private String _name;
	private Course _course;
	
	private Set<Teacher> _teachers 	= new HashSet<>();
	private Set<Student> _students 	= new TreeSet<>();
	private Set<Project> _projects	= new TreeSet<>();
	
	private Set< Observer<Notification> > _observers = new HashSet<>();

	Discipline(String name, Course course) {
		_name = name;
		_course = course;
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
		return String.format("* %s - %s", _course.getName(), _name);
	}
	
	void addTeacher(Teacher teacher) {
		_teachers.add(teacher);
		_observers.add(teacher);
	}

	void enrollStudent(Student student) {
		if(_course.getStudents().contains(student)) {
			_students.add(student);
			_observers.add(student);
		}
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
		_projects.add(new Project(name, this));
	}
	

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
				// Project has no survey, don't add to results
			}
		}
		return msg;

	}
	
	@Override
	public void attach(Observer<Notification> obs) {
		_observers.add(obs);
	}

	@Override
	public void dettach(Observer<Notification> obs) {
		_observers.remove(obs);
	}

	@Override
	public void notifyObservers(Notification obj) {
		for (Observer<Notification> obs : _observers)
			obs.notify(obj);
	}
	
}