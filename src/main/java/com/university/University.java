package com.university;

import java.util.*;
import com.university.person.Student;
import com.university.person.Teacher;

public class University {
    private final String m_universityName = "Austral University";
    private final Map<String, Course> m_courses = new HashMap<String, Course>();
    private final Map<String, Student> m_students = new HashMap<String, Student>();
    private final Map<String, Teacher> m_teachers = new HashMap<>();
    private final Map<String, List<Evaluation>> m_evaluations = new HashMap<>();

    /* ----- CONSTRUCTOR ----- */
    public University()
    {
        System.out.println("new University was created!\n\t-> Name: " + m_universityName);
    };

    /* ----- GETTERS ----- */
    public Map<String, Course> getCourses() { return m_courses; }
    public Map<String, Student> getStudents() { return m_students; }
    public Map<String, Teacher> getTeachers() { return m_teachers; }
    public Map<String, List<Evaluation>> getEvaluations() { return m_evaluations; }
    public String getUniversityName() { return m_universityName; }

    public boolean checkStudentInUniversity(final String _studentName) { return m_students.containsKey(_studentName); }
    public boolean checkSubjectInCourses(final String _subject)
    {
        return m_courses.containsKey(_subject);
    }
    public boolean checkTeacherInUniversity(final String _teacherName)
    {
        return m_teachers.containsKey(_teacherName);
    }


    public <T> boolean inUniversity (final T _element) {
        String className = _element.getClass().getSimpleName();
        return switch (className) {
            case "Student" -> {
                Student s = (Student) _element;
                yield m_students.containsKey(s.getName());
            }
            case "Teacher" -> {
                Teacher t = (Teacher) _element;
                yield m_teachers.containsKey(t.getName());
            }
            case "Course" -> {
                Course c = (Course) _element;
                yield m_courses.containsKey(c.getName());
            }
            case "Evaluation" -> {
                Evaluation e = (Evaluation) _element;
                yield m_evaluations.containsKey(e.getName());
            }
            default -> false;
        };
    }

    /* ----- SETTERS ----- */
    public boolean addStudent(Student student) {
        boolean inUniversity = !m_students.containsKey(student.getName());
        if (inUniversity) m_students.put(student.getName(), student);
        return inUniversity;
    }

    public boolean addTeacher(Teacher teacher) {
        boolean inUniversity = !m_teachers.containsKey(teacher.getName());
        if (inUniversity) m_teachers.put(teacher.getName(), teacher);
        return inUniversity;
    }

    public boolean addCourse(Course course) {
        boolean inUniversity = !m_courses.containsKey(course.getName());
        if (inUniversity) m_courses.put(course.getName(), course);
        return inUniversity;
    }

    public void addEvaluation(Evaluation evaluation) {
        if (!m_evaluations.containsKey(evaluation.getSubject())) {
            m_evaluations.put(evaluation.getSubject(), new ArrayList<>() {{
                add(evaluation);
            }});
        }
        // de estar le evaluacion
        for (Evaluation eval : m_evaluations.get(evaluation.getSubject())) {
            if (!eval.getName().equals(evaluation.getName())
                    || !eval.getStudent().equals(evaluation.getStudent()))
                continue;

            eval.getResults().putAll(evaluation.getResults());
        }
        m_evaluations.get(evaluation.getSubject()).add(evaluation);
    }

    /* ----- PRINT METHODS ----- */
    public void printCourses()
    {
        if (m_courses.isEmpty())
        {
            System.out.println("-> No Courses in University\n");
            return;
        }

        System.out.printf("-> Data of Courses (%d) in %s\n\n", m_courses.size(), m_universityName);
        for (Course course : m_courses.values())
            System.out.println(course);
    }

    public void printStudents()
    {
        if (m_students.isEmpty())
        {
            System.out.println("-> No Students in University\n");
            return;
        }

        System.out.printf("-> Data of Students (%d) in %s\n\n", m_students.size(), m_universityName);
        for (Student student : m_students.values())
            System.out.println(student);
    }

    public void printTeachers()
    {
        if (m_teachers.isEmpty())
        {
            System.out.println("-> No Teachers in University\n");
            return;
        }

        System.out.printf("-> Data of Teachers (%d) in %s\n\n", m_teachers.size(), m_universityName);
        for (Teacher teacher : m_teachers.values())
            System.out.println(teacher);
    }

    public void printData()
    {
        System.out.printf("\t\t--- Data of %s ---\n\n", m_universityName);

        printCourses();
        printStudents();
        printTeachers();

        System.out.print("\n\t\t--- End of data ---\n");
    }

    @Override
    public String toString() {
        return String.format("University: %s", m_universityName);
    }

}
