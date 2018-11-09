package sth.core;

public class Employee extends Person {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8002337478616583319L;

	Employee(int id, int phoneNumber, String name) {
        super(id, phoneNumber, name);
    }

    @Override
    protected String getLabel() {
    	return "FUNCIONÁRIO";
    }
    
}