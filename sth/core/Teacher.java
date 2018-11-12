package sth.core;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import sth.core.exception.BadEntryException;
import sth.core.exception.DuplicateProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

public class Teacher extends Person {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Set<Discipline> _disciplines = new TreeSet<Discipline>();

	Teacher(int id, int phoneNumber, String name) {
        super(id, phoneNumber, name);
    }
	
	Discipline getDiscipline(String name) throws NoSuchDisciplineIdException {
		for (Discipline d : _disciplines) {
			if (d.getName().equals(name))
				return d;
		}
		throw new NoSuchDisciplineIdException(name);
	}
	
	void createProject(String discipline, String projName) throws NoSuchDisciplineIdException, DuplicateProjectIdException {
		getDiscipline(discipline).createProject(projName);
	}
	
	void closeProject(String discipline, String projName) throws NoSuchDisciplineIdException, NoSuchProjectIdException {
		getDiscipline(discipline).getProject(projName).close();
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
 
	Collection<Student> getStudentsOfDiscipline(String name) throws NoSuchDisciplineIdException {
		return getDiscipline(name).getStudents();
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