package sth.core;

import java.io.Serializable;

import sth.core.exception.BadEntryException;

public abstract class Person implements Serializable, Comparable<Person> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer _id;
	private String _name;
	private int _phoneNumber;

	Person(Integer id, int phoneNumber, String name) {
		_name = name;
		_id = id;
		_phoneNumber = phoneNumber;
	}

	void setPhoneNumber(int phoneNumber) {
		_phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return _name;
	}

	public Integer getId() {
		return _id;
	}

	protected abstract String getLabel();

	@Override
	public int compareTo(Person p) {
		return getId() - p.getId();
	}
	
	public String toString() {
		return String.format("%s|%d|%d|%s", getLabel(), _id, _phoneNumber, _name);
	}

	void parseContext(String context, School school) throws BadEntryException {
		throw new BadEntryException("Should not have extra context: " + context);
	}

}