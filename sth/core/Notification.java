package sth.core;

import java.io.Serializable;

class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	private Discipline _discipline;
	private String _message;
	
	Notification(Discipline discipline, String message) {
		_discipline = discipline;
		_message = message;
	}
	
	Discipline getDiscipline() {
		return _discipline;
	}
	
	String getMessage() {
		return _message;
	}
	
}
