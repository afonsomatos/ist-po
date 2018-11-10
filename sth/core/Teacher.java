package sth.core;

import java.util.Set;
import java.util.TreeSet;

import sth.core.exception.BadEntryException;

public class Teacher extends Person {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Set<Discipline> _disciplines = new TreeSet<Discipline>();

	Teacher(int id, int phoneNumber, String name) {
        super(id, phoneNumber, name);
    }

    @Override
    void parseContext(String lineContext, School school) throws BadEntryException {
    	String components[] =  lineContext.split("\\|");

    	if (components.length != 2)
    		throw new BadEntryException("Invalid line context " + lineContext);

    		Course course = school.parseCourse(components[0]);
    		Discipline discipline = course.parseDiscipline(components[1]);

    		discipline.addTeacher(this);
    		_disciplines.add(discipline);
     }
 

	  @Override
	  protected String getLabel() {
	    return "DOCENTE";
	  }
	  
	  @Override
	  public String toString() {
		  String header = super.toString();
		  for (Discipline d : _disciplines)
			  header += '\n' + d.show();
		  return header;
	  }
  
}