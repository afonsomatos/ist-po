package sth.core;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import sth.core.exception.BadEntryException;
import sth.core.exception.DuplicateProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;


/**
 * Teacher implementation.
 */
public class Teacher extends Person {
	/** Serial number for serialization */
	private static final long serialVersionUID = 1L;

	/** Teacher's disciplines */
	private Set<Discipline> _disciplines = new TreeSet<Discipline>();

	/**
	 * Teacher constructor.

	 * @param id
	 * @param phoneNumber
	 * @param name
	 */
	Teacher(int id, int phoneNumber, String name) {
		super(id, phoneNumber, name);
	}

	/**
	 * @param name
	 * @return discipline with the given name
	 * @throws NoSuchDisciplineIdException if the discipline doesn't exist
	 */
	Discipline getDiscipline(String name) throws NoSuchDisciplineIdException {
		for (Discipline d : _disciplines) {
			if (d.getName().equals(name))
				return d;
		}
		throw new NoSuchDisciplineIdException(name);
	}
	
	/**
	 * Creates a project on a teacher's discipline

	 * @param discipline
	 * @param projName
	 * @throws NoSuchDisciplineIdException if the discipline isn't valid
	 * @throws DuplicateProjectIdException if the project already exists
	 */
	void createProject(String discipline, String projName) throws NoSuchDisciplineIdException, DuplicateProjectIdException {
		getDiscipline(discipline).createProject(projName);
	}
	
	/**
	 * Closes a project on a teacher's discipline

	 * @param discipline
	 * @param projName
	 * @throws NoSuchDisciplineIdException if the discipline isn't valid
	 * @throws NoSuchProjectIdException if the project isn't valid
	 */
	void closeProject(String discipline, String projName) throws NoSuchDisciplineIdException, NoSuchProjectIdException {
		getDiscipline(discipline).getProject(projName).close();
	}
	
	/**
	 * Parses the context of a teacher.

	 * @param lineContext
	 * @param school
	 * @throws BadEntryException
	 */
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

	/**
	 * @return all the students of a teacher discipline
	 * @throws NoSuchDisciplineIdException if the discipline isn't valid
	 */
	Collection<Student> getStudentsOfDiscipline(String name) throws NoSuchDisciplineIdException {
		return getDiscipline(name).getStudents();
	}

	/**
	 * @return the label of a teacher
	 */
	@Override
	protected String getLabel() {
		return "DOCENTE";
	}
	
	@Override
	protected String getDetails() {
		String str = "";
		for (Discipline d : _disciplines)
			str += "\n" + d.show();
		return str;
	}
	
}