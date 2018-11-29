package sth.core.exception.survey;

public class NonEmptySurveyIdException extends SurveyIdException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2489495935964489412L;

	
	public NonEmptySurveyIdException(String discipline, String project) {
		super(discipline, project);
	}
	
}
