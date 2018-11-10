package sth.core;

import java.util.Set;
import java.util.TreeSet;

import sth.core.exception.BadEntryException;

public class Student extends Person {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<Discipline> _disciplines = new TreeSet<Discipline>();
	private boolean _isRepresentative = false;
    private Course _course;

    Student(int id, int phoneNumber, String name, boolean isRepresentative) {
        super(id, phoneNumber, name);
        _isRepresentative = isRepresentative;
    }

    void setRepresentative(boolean isRepresentative) {
        _isRepresentative = isRepresentative;
    }
    
    boolean isRepresentative() {
        return _isRepresentative;
    }

    @Override
    void parseContext(String lineContext, School school) throws BadEntryException {
        String components[] =  lineContext.split("\\|");

        if (components.length != 2)
            throw new BadEntryException("Invalid line context " + lineContext);

        if (_course == null) {
            _course = school.parseCourse(components[0]);
            _course.addStudent(this);
        }

        Discipline dis = _course.parseDiscipline(components[1]);
        dis.enrollStudent(this);
        _disciplines.add(dis);
    }

    @Override
    protected String getLabel() {
        return _isRepresentative ? "DELEGADO" : "ALUNO";
    }

    @Override
    public String toString() {
		  String header = super.toString();
		  for (Discipline d : _disciplines)
			  header += '\n' + d.show();
		  return header;
    }
    
}