package sth.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import sth.app.exception.DuplicateProjectException;
import sth.app.exception.NoSuchPersonException;
import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchPersonIdException;


//FIXME import other classes if needed

/**
 * The façade class.
 */
public class SchoolManager {

  //FIXME add object attributes if needed

  private School _school = new School();
  private Person _loggedUser;
  private String _saveFile;
  
  //FIXME implement constructors if needed
  
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
    //FIXME implement method
	  _loggedUser = _school.getPerson(id);
  }

  public Person getLoggedUser(){
    return _loggedUser;
  }

  /**
   * @return true when the currently logged in person is an administrative
   */
  public boolean isLoggedUserAdministrative() {
    //FIXME implement predicate
    return false;
  }

  /**
   * @return true when the currently logged in person is a professor
   */
  public boolean isLoggedUserProfessor() {
    //FIXME implement predicate
    return _loggedUser instanceof Teacher;
  }

  /**
   * @return true when the currently logged in person is a student
   */
  public boolean isLoggedUserStudent() {
    //FIXME implement predicate
	  return _loggedUser instanceof Student;
  }

  /**
   * @return true when the currently logged in person is a representative
   */
  public boolean isLoggedUserRepresentative() {
    //FIXME implement predicate
    return isLoggedUserStudent() && ((Student) _loggedUser).isRepresentative();
  }

  // FIXME implement other methods (in general, one for each command in sth-app)
  
  public void showDisciplineStudents(String name) {
	  
  }
  
  public void closeProject(String name) {
	  
  }
  
  public void createProject(String discipline, String name) throws DuplicateProjectException, NoSuchDisciplineIdException {
	  Teacher teacher = (Teacher) _loggedUser;
	  teacher.createProject(discipline, name);
  }
  
  public void save() throws IOException {
	 FileOutputStream fout = new FileOutputStream(_saveFile);
	 ObjectOutputStream obOut = new ObjectOutputStream(fout);
	 obOut.writeObject(_school);
	 obOut.flush();
	 obOut.close();
  }
  
  public void open() throws ClassNotFoundException, FileNotFoundException, IOException, NoSuchPersonException {
	  FileInputStream fin = new FileInputStream(_saveFile);
	  ObjectInputStream obIn = new ObjectInputStream(fin);
	  School school = (School) obIn.readObject();
	  obIn.close();

	  try {
		  _loggedUser = school.getPerson(_loggedUser.getId());
		  _school = school;
	  } catch (NoSuchPersonIdException e) {
		  throw new NoSuchPersonException(e.getId());
	  }
  }
  
  public boolean hasSaveFile() {
	  return _saveFile != null;
  }
  
  public void setSaveFile(String filename) {
	  _saveFile = filename;
  }

  public List<Person> getAllUsers() {
    return _school.getAllUsers();
  }

  public void setPhone(int phone) {
	_loggedUser.setPhoneNumber(phone);
  }
  
  public List<Person> searchPerson(String name) {
	  return _school.searchPerson(name);
  }
  
}
