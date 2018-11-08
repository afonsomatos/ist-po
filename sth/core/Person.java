package sth.core;

import sth.core.exception.BadEntryException;

public abstract class Person {

    private Integer _id;
    private String _name;
    private int _phoneNumber;

    Person(Integer id, int phoneNumber, String name) {
        _name = name;
        _id = id;
        _phoneNumber = phoneNumber;
    }  

    public String getName() {
        return _name;
    }

    public Integer getId() {
        return _id;
    }

    public String toString() {
        // TODO
        return "Teste";
    }

	void parseContext(String context, School school) throws BadEntryException {
    	throw new BadEntryException("Should not have extra context: " + context);
	}

}