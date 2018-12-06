package sth.core;

import java.util.Set;
import java.util.TreeSet;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchDisciplineIdException;


/**
 * Student implementation.
 */
public class Student extends Person {

	/** Serial number for serialization */
	private static final long serialVersionUID = 1L;
	
	/** Set of disciplines of the student */
	private Set<Discipline> _disciplines = new TreeSet<Discipline>();
	
	/** Boolean that represents if the student is representative */
	private boolean _isRepresentative = false;

	/** Course where the student is registered */
	private Course _course;

	
	/**
	 * Student constructor.

	 * @param id
	 * @param phoneNumber
	 * @param name
	 * @param isRepresentative 
	 */
	Student(int id, int phoneNumber, String name, boolean isRepresentative) {
		super(id, phoneNumber, name);
		_isRepresentative = isRepresentative;
	}

	Discipline getDiscipline(String name) throws NoSuchDisciplineIdException {
		for (Discipline d : _disciplines) {
			if (d.getName().equals(name))
				return d;
		}
		throw new NoSuchDisciplineIdException(name);
	}
	
	/**
	 * Sets the student to be representative.

	 * @param isRepresentative
	 */
	void setRepresentative(boolean isRepresentative) {
		_isRepresentative = isRepresentative;
	}

	/**
	 * @return true if the student is representative, false if he isn't
	 */
	boolean isRepresentative() {
		return _isRepresentative;
	}

	/**
	 * Parses the information of the student about the course and disciplines he is in.
	 */
	@Override
	void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] =  lineContext.split("\\|");

		if (components.length != 2)
			throw new BadEntryException("Invalid line context " + lineContext);

		if (_course == null)
			_course = school.parseCourse(components[0]);
		
		if (_isRepresentative) 
			_course.addRepresentative(this);
		else 
			_course.addStudent(this);
			
		
		Discipline dis = _course.parseDiscipline(components[1]);
		
		if(_disciplines.size() < 6){
			dis.enrollStudent(this);
			_disciplines.add(dis);
		}
	}

	/**
	 * @return the label of the student, DELEGADO if he is representative or ALUNO if he isn't
	 */
	@Override
	protected String getLabel() {
		return _isRepresentative ? "DELEGADO" : "ALUNO";
	}

	Course getCourse() {
		return _course;
	}

	/**
	 * @return the string that represents the particular information of the student
	 */
	@Override
	protected String getDetails() {
		String str = "";
		for (Discipline d : _disciplines)
			str += "\n" + d.show();
		return str;
	}
	
}