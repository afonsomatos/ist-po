package sth.core.exception.survey;

public class SurveyIdException extends Exception {

	private static final long serialVersionUID = -1144209305505662380L;

	protected final String _discipline;
	
	protected final String _project;
	
	public SurveyIdException(String discipline, String project) {
		_discipline = discipline;
		_project = project;
	}
	
}
