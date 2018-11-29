package sth.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.survey.DuplicateSurveyIdException;
import sth.core.exception.survey.NoSurveyIdException;
import sth.core.exception.survey.OpeningSurveyIdException;


/**
 * Project implementation.	 
 */
public class Project implements Serializable, Comparable<Project> {
	
	/** Serial number for serialization */
	private static final long serialVersionUID = -5667914297060917398L;

	/** Project's name */
	private String _name;

	/** Boolean that represents the state of the project */
	private boolean _closed = false;
	
	private Discipline _discipline;
	
	private Set<Submission> _submissions = new TreeSet<Submission>();
	
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
	
	boolean studentSubmited(Student student) {
		for (Submission s : _submissions)
			if (s.getStudent() == student)
				return true;
		return false;
	}
	
	void createSurvey() throws DuplicateSurveyIdException {
		if (_survey != null)
			throw new DuplicateSurveyIdException(
					_discipline.getName(),
					_name);
		
		_survey = new Survey(this);
	}
	
	void setSurvey(Survey survey) {
		_survey = survey;
	}
	
	Survey getSurvey() throws NoSurveyIdException {
		if (_survey == null)
			throw new NoSurveyIdException(
					_discipline.getName(),
					_name);
		return _survey;
	}
	
	void addSubmission(Student student, String message) throws NoSuchProjectIdException{
		if(_closed == true)
			throw new NoSuchProjectIdException(_name);
		_submissions.removeIf(s -> s.getStudent() == student);
		_submissions.add(new Submission(student, message));
	}
	
	Collection<Submission> getSubmissions() {
		return _submissions;
	}
	
	boolean hasSurvey() {
		return _survey != null;
	}
	
	/**
	 * Closes the project.
	 */
	void close() {
		if (_survey != null)
			try {
				_survey.open();
			} catch (OpeningSurveyIdException e) {
				// Ignore
			}
		
		_closed = true;
	}

	@Override
	public int compareTo(Project o) {
		return _name.compareTo(o._name);
	}
}
