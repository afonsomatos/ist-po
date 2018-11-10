package sth.core;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Discipline implements Serializable, Comparable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _name;
    
    //not used?
    //private int _capacity;

	private Course _course;
    private List<Teacher> teachers = new ArrayList<Teacher>();
    private List<Student> students = new ArrayList<Student>();

    Discipline(String name, Course course) {
        _name = name;
        _course = course;
        //_capacity = capacity;
    }

    String getName() {
        return _name;
    }

    Course getCourse() {
        return _course;
    }

    String show() {
    	String txt = String.format("* %s - %s", _course.getName(), _name);
    	return txt;
    }
    
    void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    void enrollStudent(Student student) {
        students.add(student);
    }

	@Override
	public int compareTo(Object o) {
		Discipline d = (Discipline) o;
		
		// try to order by course name
		int compareCourse = _course.getName().compareTo(d.getCourse().getName());
		if (compareCourse != 0)
			return compareCourse;
		
		// order by discipline name
		return getName().compareTo(d.getName());
	}

}