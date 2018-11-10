package sth.core;

import java.io.Serializable;

public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5667914297060917398L;

	private String _name;
	// private String _description;
	private boolean _closed = false;
	
	Project(String name) {
		_name = name;
	}
	
	public String getName() {
		return _name;
	}
	
	void close() {
		_closed = true;
	}
}
