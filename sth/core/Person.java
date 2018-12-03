package sth.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sth.core.exception.BadEntryException;


/**
 * Person implementation.
 */
public abstract class Person implements Serializable, Comparable<Person> {
	
	/** Serial number for serialization */
	private static final long serialVersionUID = 1L;
	
	/** Person's id */
	private Integer _id;
	
	/** Person's name */
	private String _name;

	/** Person's phone number */
	private int _phoneNumber;
	
	/** Inbox messages */
	private List<String> _inbox = new ArrayList<String>();
	
	/** Don't receive messages from these disciplines */
	private Set<Discipline> _inboxBlocks = new HashSet<>();

	/**
	 * Person constructor.

	 * @param id
	 * @param phoneNumber
	 * @param name
	 */
	Person(Integer id, int phoneNumber, String name) {
		_name = name;
		_id = id;
		_phoneNumber = phoneNumber;
	}

	Collection<String> readInbox() {
		Collection<String> all = new ArrayList<>(_inbox);
		_inbox.clear();
		return all;
	}
	
	void unblock(Discipline discipline) {
		_inboxBlocks.remove(discipline);
	}
	
	void block(Discipline discipline) {
		_inboxBlocks.add(discipline);
	}
	
	void notify(Discipline discipline, String message) {
		if (!_inboxBlocks.contains(discipline))
			_inbox.add(message);
	}
	
	/**
	 * Sets person phone number to given one.

	 * @param phoneNumber
	 */
	void setPhoneNumber(int phoneNumber) {
		_phoneNumber = phoneNumber;
	}
	
	/**
	 * @return person's name
	 */
	public String getName() {
		return _name;
	}


	/**
	 * @return person's id
	 */
	public Integer getId() {
		return _id;
	}

	/**
	 * @return the label of the person
	 */
	protected abstract String getLabel();

	/**
	 * @return the details of each person
	 */
	protected abstract String getDetails();
	
	/**
	 * @param p
	 * @return an int that represents the sorting precedence
	 */
	@Override
	public int compareTo(Person p) {
		return getId() - p.getId();
	}
	
	/**
	 * @return string that represents the person
	 */
	public final String toString() {
		return String.format("%s|%d|%d|%s", getLabel(), _id, _phoneNumber, _name) + getDetails();
	}
	
	/**
	 * Parses the context of a person

	 * @param context
	 * @param school
	 * @throws BadEntryException if the parsed element represents a Person
	 */
	void parseContext(String context, School school) throws BadEntryException {
		throw new BadEntryException("Should not have extra context: " + context);
	}

}