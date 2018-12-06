package sth.core;

import java.io.Serializable;

class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	private String _message;
	
	Notification(String message) {
		_message = message;
	}
	
	String getMessage() {
		return _message;
	}
	
}
