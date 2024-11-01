package com.university.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Evaluation implements Comparable {
    private String type = "";
    private String name = "";
    private String subject = "";
    private String student = "";
    // keys of grades represent the exercise
    // LinkedHashMap porque mantiene el orden de insercion
    private Map<String, Float> results = new LinkedHashMap<>();
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

    public float getAverage() {
        float average = 0;

        for (float result : results.values())
            average += result;

        return average / results.size();
    }

    public float getAverageByType()
    {
        // types = [FINAL_PRACTICAL_WORK, PRACTICAL_WORK, WRITTEN_EXAM, ORAL_EXAM]
        return switch (this.type)
        {
            case "FINAL_PRACTICAL_WORK":
                yield getFinalExamAverage();
            case "PRACTICAL_WORK":
                yield getPracticalWorkAverage();
            case "WRITTEN_EXAM":
                yield getWrittenExamAverage();
            case "ORAL_EXAM":
                yield getOralExamAverage();
            default:
                yield 0;
        };
    }

    private float getFinalExamAverage()
    {
        float result = 0;

        for (float grade : results.values())
            result += grade;

        return result;
    }

    private float getWrittenExamAverage()
    {
        float result = 0;

        for (float grade : this.results.values())
            result += grade;

        return result / this.results.size();
    }

    private float getPracticalWorkAverage() {
        // puse sorted, porque en algunos alumnos me aparece su mayor nota, no la ultima
        List<Float> result = this.results.values().stream().sorted().toList();
        return result.getLast();
    }

    private float getOralExamAverage()
    {
        return this.results.values().stream().toList().getFirst();
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

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Evaluation)) return -1;

        Evaluation e = (Evaluation) o;

        int compare = name.compareTo(e.name);
        if (compare == 0) return student.compareTo(e.student);
        return compare;
    }
}
