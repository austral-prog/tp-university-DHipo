package com.university.Universidad;

import com.university.Course.*;
import com.university.Student.Student;
import com.university.Teacher.Teacher;

import java.io.*;
import java.util.*;
import java.util.List;

// Classroom, Subject, Student_Name, Student_Email, Subject_Teacher
enum IndexData
{
    CLASSROOM,
    SUBJECT,
    STUDENT_NAME,
    STUDENT_EMAIL,
    SUBJECT_TEACHER
}

public class University {
    private final String m_universityName = "Austral University";
    private Map<String, Course> m_courses = new HashMap<String, Course>();
    private Map<String, Student> m_students = new HashMap<String, Student>();
    private Map<String, Teacher> m_teachers = new HashMap<>();

    public University()
    {
        System.out.println("new University was created!\n\t-> Name: " + m_universityName);
    };

    /* ----- GETTERS ----- */

    public Map<String, Course> getCourses() { return m_courses; }
    public Map<String, Student> getStudents() { return m_students; }
    public Map<String, Teacher> getTeachers() { return m_teachers; }
    public String getUniversityName() { return m_universityName; }

    /* ----- Private : METHODS ----- */

    private boolean checkStudentInUniversity(final String _studentName) { return m_students.containsKey(_studentName); }

    private boolean checkSubjectInCourses(final String _subject)
    {
        return m_courses.containsKey(_subject);
    }

    private boolean checkTeacherInUniversity(final String _teacherName)
    {
        return m_teachers.containsKey(_teacherName);
    }

    /* ----- Public : METHODS ----- */

    public void updateData(String[] _data)
    {

        if (_data[IndexData.CLASSROOM.ordinal()].equals("Classroom")) return;
        if (_data[IndexData.SUBJECT.ordinal()].equals("Subject")) return;

        final String subject        = _data[IndexData.SUBJECT.ordinal()];
        final String classroom      = _data[IndexData.CLASSROOM.ordinal()];
        final String studentName    = _data[IndexData.STUDENT_NAME.ordinal()];
        final String studentEmail   = _data[IndexData.STUDENT_EMAIL.ordinal()];
        final String teacher        = _data[IndexData.SUBJECT_TEACHER.ordinal()];

        if (!checkStudentInUniversity(studentName))
        {
            Student newStudent = new Student(studentName, studentEmail);
            newStudent.addCourse(subject);
            m_students.put(studentName, newStudent);
        }

        if (!checkTeacherInUniversity(teacher))
        {
            Teacher newTeacher = new Teacher(teacher);
            newTeacher.addCourse(classroom);
            newTeacher.addSubject(subject);
            newTeacher.addStudent(studentName);
            m_teachers.put(teacher, newTeacher);
        }

        if(!checkSubjectInCourses(subject))
        {
            Course newCourse = new Course(subject);
            newCourse.addStudent(m_students.get(studentName));
            newCourse.addClassroom(classroom);
            newCourse.addTeacherName(teacher);
            m_courses.put(newCourse.getName(), newCourse);
            return;
        }

        /* ----- UPDATE DATA ----- */
        Course actualCourse = m_courses.get(subject);
        Student actualStudent = m_students.get(studentName);
        Teacher actualTeacher = m_teachers.get(teacher);

        /* --- Course --- */
        actualCourse.addClassroom(classroom);
        actualCourse.addTeacherName(teacher);
        actualCourse.addStudent(actualStudent);

        /* --- Student --- */
        actualStudent.addCourse(subject);

        /* --- Teacher --- */
        actualTeacher.addSubject(subject);
        actualTeacher.addStudent(studentName);
        actualTeacher.addCourse(classroom);
    }

    public boolean importDataFromCSV(String _fileName)
    {
        boolean success = false;

        try(BufferedReader reader = new BufferedReader(new FileReader(_fileName));) {
            while (reader.ready())
                updateData(reader.readLine().split(","));
            success = true;
        }catch (IOException exception) {
            System.out.printf("\n\t\t--- Error ---\n%s", exception.getMessage());
        }

        return success;
    }

    public boolean extractStudentsWithCourses(String _fileName)
    {
        boolean success = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(_fileName)))
        {
            List<String> sorted = m_students.keySet().stream().sorted().toList();
            writer.write("Student_Name,Course_Count\n");
            for (String e : sorted)
                writer.write(String.format("%s,%s\n", e, m_students.get(e).getCountCourses()));
            success = true;
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return success;
    }

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
