package sth.core;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Course implements Serializable {

    private String _name;

    private List<Discipline> _disciplines   = new ArrayList<Discipline>();
    private List<Student> _students         = new ArrayList<Student>();
    private List<Student> _representatives  = new ArrayList<Student>();

    Course(String name) {
        _name = name;
    }

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
        //header=name
        return new Discipline(header);
    }

}