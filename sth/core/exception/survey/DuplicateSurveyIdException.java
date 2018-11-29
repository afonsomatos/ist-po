package sth.core.exception.survey;

public class DuplicateSurveyIdException extends SurveyIdException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4675965385691917090L;

	public DuplicateSurveyIdException(String discipline, String project) {
		super(discipline, project);
	}
}
