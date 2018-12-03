package sth.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;

import sth.app.exception.DuplicateProjectException;
import sth.core.exception.BadEntryException;
import sth.core.exception.DuplicateProjectIdException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.survey.ClosingSurveyIdException;
import sth.core.exception.survey.DuplicateSurveyIdException;
import sth.core.exception.survey.NoSurveyIdException;
import sth.core.exception.survey.NonEmptySurveyIdException;
import sth.core.exception.survey.OpeningSurveyIdException;
import sth.core.exception.survey.SurveyFinishedIdException;


/**
 * The façade class.
 */
public class SchoolManager {
	/** School managed by SchoolManager*/
	private School _school = new School();

	/** Current logged user */
	private Person _loggedUser;
	
	/** Current save file to store the program information */
	private String _saveFile;
	
	
	/**
	 * @param datafile
	 * @throws ImportFileException
	 * @throws InvalidCourseSelectionException
	 */
	public void importFile(String datafile) throws ImportFileException {
		try {
			_school.importFile(datafile);
		} catch (IOException | BadEntryException e) {
			throw new ImportFileException(e);
		}
	}

	/**
	 * Do the login of the user with the given identifier.

	 * @param id identifier of the user to login
	 * @throws NoSuchPersonIdException if there is no users with the given identifier
	 */
	public void login(int id) throws NoSuchPersonIdException {
		_loggedUser = _school.getPerson(id);
	}

	/**
	 * @return the logged user
	 */
	public Person getLoggedUser(){
		return _loggedUser;
	}

	/**
	 * @return true when the currently logged in person is an administrative
	 */
	public boolean isLoggedUserAdministrative() {
		return _loggedUser instanceof Employee;
	}

	/**
	 * @return true when the currently logged in person is a professor
	 */
	public boolean isLoggedUserProfessor() {
		return _loggedUser instanceof Teacher;
	}

	/**
	 * @return true when the currently logged in person is a student
	 */
	public boolean isLoggedUserStudent() {
		return _loggedUser instanceof Student;
	}

	/**
	 * @return true when the currently logged in person is a representative
	 */
	public boolean isLoggedUserRepresentative() {
		return isLoggedUserStudent() && ((Student) _loggedUser).isRepresentative();
	}
	

	/**
	 * @param name
	 * @return Collection of students of the given discipline
	 * @throws NoSuchDisciplineIdException if the given discipline isn't valid
	 */	
	public Collection<Student> showDisciplineStudents(String name) throws NoSuchDisciplineIdException {
		Teacher teacher  = (Teacher) _loggedUser;
		return teacher.getStudentsOfDiscipline(name);
	}
	
	/**
	 * Closes the given project.
	 * @param discipline
	 * @param name project name
	 * @throws NoSuchProjectIdException if the given project isn't valid
	 * @throws NoSuchDisciplineIdException if the given discipline isn't valid
	 */
	public void closeProject(String discipline, String name) throws NoSuchProjectIdException, NoSuchDisciplineIdException {
		Teacher teacher = (Teacher) _loggedUser;
		teacher.getDiscipline(discipline).getProject(name).close();
	}
	
	/**
	 * Creates a project with given name.

	 * @param discipline
	 * @param name
	 * @throws DuplicateProjectException if the project already exists
	 * @throws NoSuchDisciplineIdException if the given discipline isn't valid
	 */
	public void createProject(String discipline, String name) throws DuplicateProjectIdException, NoSuchDisciplineIdException {
		Teacher teacher = (Teacher) _loggedUser;
		teacher.createProject(discipline, name);
	}
	
	/**
	 * Saves the information on the file stored by the SchoolManager.
	 */
	public void save() throws IOException {
		FileOutputStream fout = new FileOutputStream(_saveFile);
		ObjectOutputStream obOut = new ObjectOutputStream(fout);
		obOut.writeObject(_school);
		obOut.flush();
		obOut.close();
	}
	
	/**
	 * Reads the information on the file stored by the SchoolManager.
	 * @throws NoSuchPersonIdException 
	 */
	public void open() throws ClassNotFoundException, FileNotFoundException, IOException, NoSuchPersonIdException {
		FileInputStream fin = new FileInputStream(_saveFile);
		ObjectInputStream obIn = new ObjectInputStream(fin);
		School school = (School) obIn.readObject();
		obIn.close();

		_loggedUser = school.getPerson(_loggedUser.getId());
		_school = school;
	}

	/**
	 * @return true if a save file has been specified, false in contrary
	 */
	public boolean hasSaveFile() {
		return _saveFile != null;
	}
	
	/**
	 * Sets the save file to the given file

	 * @param filename
	 */
	public void setSaveFile(String filename) {
		_saveFile = filename;
	}

	/**
	 * @return all the people of the school
	 */
	public List<Person> getAllUsers() {
		return _school.getAllUsers();
	}


	/**
	 * Sets the logged user phone number

	 * @param phone
	 */
	public void setPhone(int phone) {
		_loggedUser.setPhoneNumber(phone);
	}
	
	/**
	 * @param name
	 * @return all the people of the school whose name contains the given name
	 */
	public Collection<Person> searchPerson(String name) {
		return _school.searchPerson(name);
	}
	
	public void answerSurvey(int hours, String message, String discipline, String proj) throws NoSurveyIdException, NoSuchProjectIdException, NoSuchDisciplineIdException {
		Student student = (Student) _loggedUser;
		Survey survey = student.getDiscipline(discipline).getProject(proj).getSurvey();
		survey.addAnswer(student, message, hours);
	}
	
	public void finishSurvey(String discipline, String project) throws NoSurveyIdException, NoSuchProjectIdException, NoSuchDisciplineIdException, SurveyFinishedIdException {
		((Student) _loggedUser)
			.getCourse()
			.getDiscipline(discipline)
			.getProject(project)
			.getSurvey()
			.finish();
	}
	
	public void openSurvey(String discipline, String project) throws NoSurveyIdException, NoSuchProjectIdException, NoSuchDisciplineIdException, OpeningSurveyIdException {
		((Student) _loggedUser)
			.getCourse()
			.getDiscipline(discipline)
			.getProject(project)
			.getSurvey()
			.open();
	}
	
	public void closeSurvey(String discipline, String project) throws NoSurveyIdException, NoSuchProjectIdException, NoSuchDisciplineIdException, ClosingSurveyIdException {
		((Student) _loggedUser)
			.getCourse()
			.getDiscipline(discipline)
			.getProject(project)
			.getSurvey()
			.close();
	}
	
	public void cancelSurvey(String discipline, String project) throws NoSurveyIdException, NoSuchProjectIdException, NoSuchDisciplineIdException, SurveyFinishedIdException, NonEmptySurveyIdException {
		((Student) _loggedUser)
			.getCourse()
			.getDiscipline(discipline)
			.getProject(project)
			.getSurvey()
			.cancel();
	}
	
	public void createSurvey(String discipline, String proj) throws DuplicateSurveyIdException, NoSuchProjectIdException, NoSuchDisciplineIdException {
		Project project =
				((Student) _loggedUser)
					.getCourse()
					.getDiscipline(discipline)
					.getProject(proj);
		
		project.createSurvey();
	}
	
	public void deliverProject(String content, String discipline, String proj) throws NoSuchProjectIdException, NoSuchDisciplineIdException {
		Student student = (Student) _loggedUser;
		student.getDiscipline(discipline).getProject(proj).addSubmission(student, content);
	}

	public String getDisciplineSurveyResults(String discipline) throws NoSuchDisciplineIdException {
		return ((Student) _loggedUser).getCourse().getDiscipline(discipline).getSurveyResults();
	}
	
	public String getSurveyResults(String discipline, String proj) throws NoSuchProjectIdException, NoSuchDisciplineIdException, NoSurveyIdException {
		if (isLoggedUserProfessor())
			return ((Teacher) _loggedUser)
					.getDiscipline(discipline)
					.getProject(proj)
					.getSurvey()
					.getResultsForTeacher();
			
		else
			return ((Student) _loggedUser)
					.getDiscipline(discipline)
					.getProject(proj)
					.getSurvey()
					.getResultsForStudent();
	}
	
	public String showProjectSubmissions(String discipline, String proj) throws NoSuchProjectIdException, NoSuchDisciplineIdException {
		Teacher teacher = (Teacher) _loggedUser;
		Collection<Submission> projectSubmissions = teacher.getDiscipline(discipline).getProject(proj).getSubmissions();
		String res = discipline + " - " + proj;
		for(Submission s : projectSubmissions)
			res += "\n" + "* " + s;
		return res;
	}
	
	public String readInbox() {
		return String.join("\n", _loggedUser.readInbox());
	}
	
}
