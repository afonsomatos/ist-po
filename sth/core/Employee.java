package sth.core;


/**
 * Employee implementation.
 */
public class Employee extends Person {

	/** Serial number for serialization */
	private static final long serialVersionUID = -8002337478616583319L;


	/**
	 * Employee constructor.

	 * @param id
	 * @param phoneNumber
	 * @param name
	 */
	Employee(int id, int phoneNumber, String name) {
		super(id, phoneNumber, name);
	}


	/**
	 * @return the label of the Employee
	 */
	@Override
	protected String getLabel() {
		return "FUNCIONÁRIO";
	}


	@Override
	protected String getDetails() {
		return "";
	}
	
}