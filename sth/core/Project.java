package sth.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import sth.core.Survey.Condition;
import sth.core.exception.ClosingSurveyException;
import sth.core.exception.DuplicateSurveyException;
import sth.core.exception.NoSurveyException;
import sth.core.exception.NonEmptySurveyException;
import sth.core.exception.OpeningSurveyException;
import sth.core.exception.SurveyFinishedException;


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
	
	void finishSurvey() throws NoSurveyException, SurveyFinishedException {
		Survey survey = getSurvey();
		Condition condition = survey.getCondition();
		
		if (condition == Condition.CLOSED)
			survey.setCondition(Condition.FINISHED);
		else if (condition != Condition.FINISHED)
			throw new SurveyFinishedException();
	}

	void closeSurvey() throws NoSurveyException, ClosingSurveyException {
		Survey survey = getSurvey();
		Condition condition = survey.getCondition();
		
		if (condition == Condition.OPENED)
			survey.setCondition(Condition.CLOSED);
		else if (condition != Condition.CLOSED)
			throw new ClosingSurveyException();
	}
	
	void openSurvey() throws OpeningSurveyException, NoSurveyException {
		Survey survey = getSurvey();
		Condition condition = survey.getCondition();
		
		if (condition != Condition.FINISHED && condition != Condition.CREATED)
			throw new OpeningSurveyException();

		survey.setCondition(Condition.OPENED);
	}
	
	void cancelSurvey() throws NonEmptySurveyException, SurveyFinishedException, NoSurveyException {
		Survey survey = getSurvey();
		Condition condition = survey.getCondition();
		
		if (condition == Condition.OPENED && !survey.empty())
			throw new NonEmptySurveyException();
		
		if (condition == Condition.FINISHED)
			throw new SurveyFinishedException();
		
		if (condition == Condition.CLOSED)
			survey.setCondition(Condition.OPENED);
		else
			_survey = null;
	}
	
	void createSurvey() throws DuplicateSurveyException {
		if (_survey != null)
			throw new DuplicateSurveyException();
		_survey = new Survey(this);
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
	
	boolean hasSurvey() {
		return _survey != null;
	}
	
	/**
	 * Closes the project.
	 */
	void close() {
		if (_survey != null)
			_survey.setCondition(Condition.OPENED);
		_closed = true;
	}

	@Override
	public int compareTo(Project o) {
		return _name.compareTo(o._name);
	}
}
