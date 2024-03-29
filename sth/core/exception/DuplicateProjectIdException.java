package sth.core.exception;

/** Exception thrown when the requested project does not exist. */
public class DuplicateProjectIdException extends Exception {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201809021324L;

	/** Project id. */
	private String _projectName;

	/**
	 * @param id
	 */
	public DuplicateProjectIdException(String id) {
		_projectName = id;
	}

	/** @return id */
	public String getId() {
		return _projectName;
	}

}
