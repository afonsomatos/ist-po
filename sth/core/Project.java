package sth.core;

import java.io.Serializable;


/**
 * Project implementation.	 
 */
public class Project implements Serializable {
	
	/** Serial number for serialization */
	private static final long serialVersionUID = -5667914297060917398L;

	/** Project's name */
	private String _name;

	/** Boolean that represents the state of the project */
	private boolean _closed = false;
	
	/**
	 * Project constructor.

	 * @param name
	 */
	Project(String name) {
		_name = name;
	}
	
	/**
	 * @return project's name
	 */
	public String getName() {
		return _name;
	}
	
	/**
	 * Closes the project.
	 */
	void close() {
		_closed = true;
	}
}
