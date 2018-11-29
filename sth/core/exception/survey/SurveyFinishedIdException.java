package sth.core.exception.survey;

public class SurveyFinishedIdException extends SurveyIdException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -62836429755968000L;

	public SurveyFinishedIdException(String discipline, String project) {
		super(discipline, project);
	}
}
