package com.university.Student;

import com.university.Evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private String m_name;
    private String m_email;
    private List<String> m_Courses;
    private Map<String, List<Evaluation>> m_evaluations;

    /* ----- CONSTRUCTOR ----- */
    public Student(String _name) {
        this.m_name = _name;
        this.m_email = "None";
        this.m_Courses = new ArrayList<>();
        this.m_evaluations = new HashMap<>();
    }

    public Student(String _name, String _email) {
        this.m_name = _name;
        this.m_email = _email;
        this.m_Courses = new ArrayList<>();
        this.m_evaluations = new HashMap<>();
    }

    /* ----- GETTERS ----- */
    public String getName() {return this.m_name;}
    public String getEmail() {return this.m_email;}
    public Map<String, List<Evaluation>> getGrades() {return this.m_evaluations;}
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

    public int addEvaluation(final Evaluation _evaluation) {
        // De no existir una key para esa materia la creo
        if (this.m_evaluations.get(_evaluation.getSubject()) == null)
            m_evaluations.put(_evaluation.getSubject(), new ArrayList<>());
        if (this.m_evaluations.get(_evaluation.getSubject()).contains(_evaluation)) return -1;

        this.m_evaluations.get(_evaluation.getSubject()).add(_evaluation);
        return 1;
    }

    /* --- PRINT METHODS --- */
    @Override
    public String toString() {
        return String.format(
                "Name: %s, Email: %s, Courses: %s, Grades: %d",
                m_name, m_email, m_Courses, m_evaluations.size()
        );
    }

    public void showEvaluations() {
        m_evaluations.forEach((k, v) -> {
            System.out.printf("Subject - %s\n", k.toUpperCase());
            for (Evaluation e : v) System.out.println(e);
        });
    }
}
