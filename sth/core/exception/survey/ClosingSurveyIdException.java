package sth.core.exception.survey;

public class ClosingSurveyIdException extends SurveyIdException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1054210451340284480L;

	public ClosingSurveyIdException(String discipline, String project) {
		super(discipline, project);
	}
}
