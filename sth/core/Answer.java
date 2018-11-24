package sth.core;

import java.io.Serializable;

public class Answer implements Serializable {

	private static final long serialVersionUID = -5496071551526664326L;

	private String _message;
	private int _hours;
	
	public String getMessage() {
		return _message;
	}
	
	public int getHours() {
		return _hours;
	}
	
	Answer(String message, int hours) {
		_message = message;
		_hours = hours;
	}
	
}
