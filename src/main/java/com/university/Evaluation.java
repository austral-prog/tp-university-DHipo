package com.university;

import java.util.Map;

public class Evaluation {
    private String type;
    private String name;
    private String subject;

    private String author;
    // keys of grades represent the exercise
    private Map<Integer, Integer> grade;

    /* ----- CONSTRUCTOR ----- */
    public Evaluation(String _type, String _name, String _subject)
    {
        type = _type;
        name = _name;
        subject = _subject;
    }

    /* ----- GETTERS ----- */
    public String getType() { return type; }
    public String getName() { return name; }
    public String getSubject() { return subject; }
    public String getAuthor() { return author; }
    public Map<Integer, Integer> getGrade() { return grade; }

    /* ----- SETTERS ----- */
    public void setType(String _type) { type = _type; }
    public void setName(String _name) { name = _name; }
    public void setSubject(String _subject) { subject = _subject; }
    public void setAuthor(String _author) { author = _author; }
    public void setGrade(Map<Integer, Integer> _grade) { grade = _grade; }

    public void addGrade(final int _exercise, final int _grade)
    {
        if (grade.containsKey(_exercise)) return;
        grade.put(_exercise, _grade);
    }
}
