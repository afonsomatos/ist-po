package sth.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import sth.core.exception.BadEntryException;

public abstract class Person implements Observer<Notification>, Serializable, Comparable<Person> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer _id;
	private String _name;
	private int _phoneNumber;
	
	private List<Notification> _inbox = new ArrayList<Notification>();
	
	Person(Integer id, int phoneNumber, String name) {
		_name = name;
		_id = id;
		_phoneNumber = phoneNumber;
	}

	Collection<String> readInbox() {
		Collection<Notification> all = new ArrayList<>(_inbox);
		_inbox.clear();
		return all.stream()
				.map(Notification::getMessage)
				.collect(Collectors.toList());
	}
	
	@Override
	public void notify(Notification notification) {
		_inbox.add(notification);
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

	protected abstract String getDetails();
	
	@Override
	public int compareTo(Person p) {
		return getId() - p.getId();
	}

	public final String toString() {
		return String.format("%s|%d|%d|%s", getLabel(), _id, _phoneNumber, _name) + getDetails();
	}
	
	void parseContext(String context, School school) throws BadEntryException {
		throw new BadEntryException("Should not have extra context: " + context);
	}

}