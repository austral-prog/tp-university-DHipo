package com.university.person;

import com.university.Evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends Person{
    private String m_name;
    private String m_email;
    private List<String> m_Courses;
    private Map<String, Map<String, Evaluation>> m_evaluations;

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
    public Map<String, Map<String, Evaluation>> getGrades() {return this.m_evaluations;}
    public List<String> getCourses() {return this.m_Courses;}
    public int getCountCourses() {return this.m_Courses.size();}
    public Map<String, Map<String, Float>> getAllPromedio() {
        Map<String, Map<String, Float>> result = new HashMap<>();
        for (String key : this.m_evaluations.keySet().stream().toList()) {
            Map<String, Float> promedio = new HashMap<>();
            for (String name : m_evaluations.get(key).keySet().stream().toList())
                promedio.put(name, this.m_evaluations.get(key).get(name).getAverage());
            result.put(key, promedio);
        }
        return result;
    }

    /* ----- SETTERS ----- */
    public void addCourse(final String _course) {
        if (this.m_Courses.contains(_course)) return;
        this.m_Courses.add(_course);
    }

    public int removeCourse(final String _course) {
        if (!this.m_Courses.contains(_course)) return -1;
        this.m_Courses.remove(_course);
        return 1;
    }

    public void addEvaluation(final Evaluation _evaluation) {
        // De no existir una key para esa materia la creo
        if (this.m_evaluations.get(_evaluation.getSubject()) == null)
            m_evaluations.put(_evaluation.getSubject(), new HashMap<>());

        Map<String, Evaluation> evalsOfSubject = this.m_evaluations.get(_evaluation.getSubject());
        Evaluation evaluation = evalsOfSubject.get(_evaluation.getName());

        if (evaluation == null) {
            evalsOfSubject.put(_evaluation.getName(), _evaluation);
            return;
        }

        _evaluation.getResults().forEach((e, g) -> evaluation.getResults().put(e, g));
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
            for (Map.Entry<String, Evaluation> e : v.entrySet()) System.out.println(e);
        });
    }

    @Override
    public List<String> toCSV() {
        List<String> result = new ArrayList<>();
        return result;
    }
}
