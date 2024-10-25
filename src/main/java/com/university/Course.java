package com.university;

import com.university.person.Student;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String m_name;
    private List<String> m_classrooms = new ArrayList<>();
    private List<String> m_teachersName = new ArrayList<>();
    private List<Student> m_students = new ArrayList<>();

    /* ----- CONSTRUCTOR ----- */
    public Course(String _name) {
        this.m_name = _name;
    }

    /* ----- GETTERS ----- */

    public String getName() {return m_name;}
    public List<String> getClassrooms() {return m_classrooms;}
    public List<String> getTeachersName() {return m_teachersName;}
    public List<Student> getStudents() {return m_students;}

    /* ----- SETTERS ----- */

    public int addTeacherName(String _TeacherName) {
        if (this.m_teachersName.contains(_TeacherName)) return -1;

        this.m_teachersName.add(_TeacherName);
        return 1;
    }

    public int addClassroom(String _classroom) {
        if (this.m_classrooms.contains(_classroom)) return -1;
        this.m_classrooms.add(_classroom);
        return 1;
    }

    public int addStudent(Student _student) {
        if (this.m_students.contains(_student)) return -1;
        this.m_students.add(_student);
        return 1;
    }

    /* ----- PRINT METHODS ----- */
    @Override
    public String toString() {
        return String.format(
                "{" +
                        "\n\tName: %s" +
                        "\n\tClassrooms: %s" +
                        "\n\tTeachers: %s" +
                        "\n\tStudents (count): %d" +
                        "\n}\n", m_name, m_classrooms, m_teachersName, m_students.size()
        );
    }
}
