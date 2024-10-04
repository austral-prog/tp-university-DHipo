package com.university.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private List<String> m_courses = new ArrayList<String>();
    private List<String> m_subjects = new ArrayList<String>();
    private List<String> m_students = new ArrayList<String>();
    private String m_name;

    /* ----- CONSTRUCTOR ----- */
    public Teacher (final String _name)
    {
        m_name = _name;
    }

    /* ----- GETTERS ----- */
    public List<String> getCourses() { return m_courses; }
    public List<String> getSubjects() { return m_subjects; }
    public List<String> getStudents() { return m_students; }
    public String getName() { return m_name; }

    /* ----- SETTERS ----- */
    public void addCourse(final String _course) { if (!m_courses.contains(_course)) m_courses.add(_course); }
    public void addSubject(final String _subject) { if (!m_subjects.contains(_subject)) m_subjects.add(_subject); }
    public void addStudent(final String _student) { if (!m_students.contains(_student)) m_students.add(_student); }

    @Override
    public String toString() {
        return String.format(
                """
                {
                \tName: %s
                \tSubjects: %s
                \tCourses: %s
                \tStudents: %s
                }
                """,
                m_name, m_subjects.stream().sorted().toList(), m_courses.stream().sorted().toList(), m_students.stream().sorted().toList()
        );
    }
}
