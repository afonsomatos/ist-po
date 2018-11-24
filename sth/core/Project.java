package sth.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import sth.core.exception.NoSurveyException;


/**
 * Project implementation.	 
 */
public class Project implements Serializable {
	
	/** Serial number for serialization */
	private static final long serialVersionUID = -5667914297060917398L;

	/** Project's name */
	private String _name;

	/** Boolean that represents the state of the project */
	private boolean _closed = false;
	
	private Discipline _discipline;
	
	private Set<Submission> _submissions = new TreeSet<Submission>(new Comparator<Submission>() {
		@Override
		public int compare(Submission o1, Submission o2) {
			return o1.getStudent().getId() - o2.getStudent().getId();
		}
	});
	
	private Survey _survey = null;
	
	/**
	 * Project constructor.

	 * @param name
	 */
	Project(String name, Discipline discipline) {
		_name = name;
		_discipline = discipline;
	}
	
	Discipline getDiscipline() {
		return _discipline;
	}
	
	/**
	 * @return project's name
	 */
	public String getName() {
		return _name;
	}
	
	Survey getSurvey() throws NoSurveyException {
		if (_survey == null)
			throw new NoSurveyException();
		return _survey;
	}
	
	void addSubmission(Student student, String message) {
		_submissions.removeIf(s -> s.getStudent() == student);
		_submissions.add(new Submission(student, message));
	}
	
	Collection<Submission> getSubmissions() {
		return _submissions;
	}
	
	/**
	 * Closes the project.
	 */
	void close() {
		_closed = true;
	}
}
