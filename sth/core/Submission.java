package sth.core;

import java.io.Serializable;

public class Submission implements Serializable, Comparable<Submission> {
	
	private static final long serialVersionUID = 5658898363223725299L;

	private final String _message;
	private final Student _student;
	
	Submission(Student student, String message) {
		_student = student;
		_message = message;
	}
	
	@Override
	public int compareTo(Submission o2) {
		return _student.getId() - o2.getStudent().getId();
	}
	
	Student getStudent() {
		return _student;
	}
	
	@Override
	public String toString() {
		return _student.getId() + " - " + _message;
	}
	
}
