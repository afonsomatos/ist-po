package sth.core;

import java.util.Set;
import java.util.TreeSet;

import sth.app.exception.DuplicateProjectException;
import sth.app.exception.NoSuchDisciplineException;
import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchDisciplineIdException;

public class Teacher extends Person {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Set<Discipline> _disciplines = new TreeSet<Discipline>();

	Teacher(int id, int phoneNumber, String name) {
        super(id, phoneNumber, name);
    }

	void createProject(String discipline, String projName) throws NoSuchDisciplineIdException, DuplicateProjectException {
		for (Discipline d : _disciplines)
			if (d.getName() == discipline) {
				d.createProject(projName);
			}
		throw new NoSuchDisciplineIdException(discipline);
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
	  
	  void createProject() {
		  
	  }
  
}