package com.university;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluation{
    private String type = "";
    private String name = "";
    private String subject = "";
    private String student = "";
    // keys of grades represent the exercise
    private Map<String, Float> results = new HashMap<>();
    private String exerciseToAdd = "";

    /* ----- CONSTRUCTOR ----- */
    public Evaluation(String _type, String _name, String _subject)
    {
        type = _type;
        name = _name;
        subject = _subject;
    }

    public Evaluation(){};

    /* ----- GETTERS ----- */
    public String getType() { return type; }
    public String getName() { return name; }
    public String getSubject() { return subject; }
    public String getStudent() { return student; }
    public Map<String, Float> getResults() { return results; }
    public float getAverage()
    {
        float total = 0;
        for (float value : results.values())
            total += value;
        return total/results.size();
    }

    /* ----- SETTERS ----- */
    public void setType(String _type) { type = _type; }
    public void setName(String _name) { name = _name; }
    public void setSubject(String _subject) { subject = _subject; }
    public void setStudent(String _student) { student = _student; }
    public void setResults(Map<String, Float> _grade) { results = _grade; }

    // No me gusta pero no se me ocurre otra cosa
    public void addExercise(final String _exercise) {
        exerciseToAdd = _exercise;
    }

    public void addGrade(final float _grade)
    {
        if (results.containsKey(exerciseToAdd)) return;
        results.put(exerciseToAdd, _grade);
    }

    /* ----- UTILS ----- */
    @Override
    public String toString() {
        return String.format(
                "Type: %s, Name: %s, Subject: %s, Author: %s, Grade: %s",
                type, name, subject, student, results
        );
    }
}
