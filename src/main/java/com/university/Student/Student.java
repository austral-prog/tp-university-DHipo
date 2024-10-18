package com.university.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private String m_name;
    private String m_email;
    private List<String> m_Courses;
    private Map<String, List<Integer>> m_grades;

    /* ----- CONSTRUCTOR ----- */
    public Student(String _name) {
        this.m_name = _name;
        this.m_email = "None";
        this.m_Courses = new ArrayList<>();
        this.m_grades = new HashMap<>();
    }

    public Student(String _name, String _email) {
        this.m_name = _name;
        this.m_email = _email;
        this.m_Courses = new ArrayList<>();
        this.m_grades = new HashMap<>();
    }

    /* ----- GETTERS ----- */
    public String getName() {return this.m_name;}
    public String getEmail() {return this.m_email;}
    public Map<String, List<Integer>> getGrades() {return this.m_grades;}
    public List<String> getCourses() {return this.m_Courses;}
    public int getCountCourses() {return this.m_Courses.size();}

    /* ----- SETTERS ----- */
    public int addCourse(final String _course) {
        if (this.m_Courses.contains(_course)) return -1;
        this.m_Courses.add(_course);
        return 1;
    }

    public int removeCourse(final String _course) {
        if (!this.m_Courses.contains(_course)) return -1;
        this.m_Courses.remove(_course);
        return 1;
    }

    /* --- PRINT METHODS --- */
    @Override
    public String toString() {
        return String.format("\n\t{\n\t\tName: %s, \n\t\tEmail: %s\n\t}", m_name, m_email);
    }
}
