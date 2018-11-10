package sth.core;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.io.Serializable;

public class Course implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _name;

    private List<Discipline> _disciplines   = new ArrayList<Discipline>();
    private List<Student> _students         = new ArrayList<Student>();
    private List<Student> _representatives  = new ArrayList<Student>();

    Course(String name) {
        _name = name;
    }

    /*
    List<Discipline> getDisciplines() {
		return _disciplines.stream()
				.sorted((d1, d2) -> d1.getName().compareTo(d2.getName()))
				.collect(Collectors.toList());
    }
    */
    
    void addDiscipline(Discipline discipline) {
        _disciplines.add(discipline);
    }

    void addStudent(Student student) {
        _students.add(student);
    }

    void addRepresentative(Student student) {
        _representatives.add(student);
    }

    void removeRepresentative(Student student) {
        _representatives.remove(student);
    }

    public String getName() {
        return _name;
    }

    Discipline parseDiscipline(String header) {
        for (Discipline d : _disciplines)
        	if (d.getName() == header)
        		return d;
        Discipline d = new Discipline(header, this);
        _disciplines.add(d);
        return d;
    }

}