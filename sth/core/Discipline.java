package sth.core;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Discipline implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _name;
    
    //not used?
    //private int _capacity;

    private List<Teacher> teachers = new ArrayList<Teacher>();
    private List<Student> students = new ArrayList<Student>();

    Discipline(String name) {
        _name = name;
        //_capacity = capacity;
    }

    String getName() {
        return _name;
    }

    Course getCourse() {
        // TODO
        return null;
    }

    void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    void enrollStudent(Student student) {
        students.add(student);
    }

}