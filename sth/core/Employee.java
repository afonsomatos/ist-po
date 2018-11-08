package sth.core;

public class Employee extends Person {

    Employee(int id, int phoneNumber, String name) {
        super(id, phoneNumber, name);
    }

    @Override
    protected abstract String getLabel() {
    	return "FUNCIONÁRIO";
    }
    
}