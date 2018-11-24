package sth.core;

public class Submission {
	
	private final String _message;
	private final Student _student;
	
	Submission(Student student, String message) {
		_student = student;
		_message = message;
	}
	
	Student getStudent() {
		return _student;
	}
	
	@Override
	public String toString() {
		return _student.getId() + " - " + _message;
	}
	
}
