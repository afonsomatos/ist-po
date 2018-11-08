package sth.core;

import sth.core.Person;
import sth.core.Course;
import sth.core.Discipline;

import sth.core.exception.BadEntryException;

public class Teacher extends Person {

    Teacher(int id, int phoneNumber, String name) {
        super(id, phoneNumber, name);
    }

    @Override
    void parseContext(String lineContext, School school) throws BadEntryException {
    String components[] =  lineContext.split("\\|");

    if (components.length != 2)
        throw new BadEntryException("Invalid line context " + lineContext);

    Course course = school.parseCourse(components[0]);
    Discipline discipline = course.parseDiscipline(components[1]);

    discipline.addTeacher(this);
  }

}