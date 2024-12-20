package com.university.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course implements Entity {
    private static int counter = 0;
    private int id;
    private String m_name;
    private List<String> m_classrooms = new ArrayList<>();
    private List<String> m_teachersName = new ArrayList<>();
    private List<Student> m_students = new ArrayList<>();
    /// KEY = Exercise Value = Criteria
    private Map<String, Criteria> m_criteriaType = new HashMap<>();

    /* ----- CONSTRUCTOR ----- */
    public Course(String _name) {
        this.m_name = _name;
        this.id = counter;
        counter++;
    }

    /* ----- GETTERS ----- */

    public String getName() {
        return m_name;
    }

    public List<String> getClassrooms() {
        return m_classrooms;
    }

    public List<String> getTeachersName() {
        return m_teachersName;
    }

    public List<Student> getStudents() {
        return m_students;
    }

    public Map<String, Criteria> getCriteriaType() {return m_criteriaType;}

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

    /* ----- OVERRIDE METHODS ----- */
    @Override
    public String toString() {
        return String.format(
                "{" +
                        "id:%d" +
                    "\n\tName: %s" +
                    "\n\tClassrooms: %s" +
                    "\n\tTeachers: %s" +
                    "\n\tStudents (count): %d" +
                    "\n\tCriteria: %s" +
                "\n}\n",this.id,
                m_name, m_classrooms, m_teachersName, m_students.size(), m_criteriaType
        );
    }

    public List<String> getAproveSubjects() {
        return List.of();
    }

    public boolean getStateOfSubject(String subject) {
     return false;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
