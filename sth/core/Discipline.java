package sth.core;

import java.util.List;
import java.util.ArrayList;

public class Discipline {

    private String _name;
    private int _capacidade;

    private List<Teacher> teachers = new ArrayList<Teacher>();
    private List<Student> students = new ArrayList<Student>();

    Discipline(String name, int capacidade) {
        _name = name;
        _capacidade = capacidade;
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