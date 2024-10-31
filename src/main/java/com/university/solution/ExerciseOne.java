package com.university.solution;

import com.university.Course;
import com.university.University;
import com.university.csv.CSVManager;
import com.university.person.Student;
import com.university.person.Teacher;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.university.solution.ExerciseOne.IndexData.*;

public class ExerciseOne implements Solution {
    /* --- exercise one : Variables --- */
    private static final String solutionFile = resourcesPath.concat("solution.csv");
    private static final String inputFile = resourcesPath.concat("input.csv");

    // Classroom, Subject, Student_Name, Student_Email, Subject_Teacher
    public enum IndexData
    {
        CLASSROOM(0),
        SUBJECT(1),
        STUDENT_NAME(2),
        STUDENT_EMAIL(3),
        SUBJECT_TEACHER(4);

        public final int value;
        IndexData(int value) {this.value = value;}
    }

    /* --- private : Methods - Exercise One --- */
    private static boolean extractStudentsWithCourses(University _university)
    {
        boolean success = false;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(solutionFile));
            Map<String, Student> students = _university.getStudents();
            List<String> sorted = students.keySet().stream().sorted().toList();

            writer.write("Student_Name,Course_Count\n");

            for (String e : sorted)
                writer.write(String.format("%s,%s\n", e, students.get(e).getCountCourses()));

            writer.close();
            success = true;

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return success;
    }

    /* --- public : Methods - Exercise One --- */
    public static void updateData(University university, String[] data)
    {
        Map<String, Student> uniStudents = university.getStudents();
        Map<String, Teacher> uniTeachers = university.getTeachers();
        Map<String, Course> uniCourses = university.getCourses();

        final String subject        = data[SUBJECT.value];
        final String classroom      = data[CLASSROOM.value];
        final String studentName    = data[STUDENT_NAME.value];
        final String studentEmail   = data[STUDENT_EMAIL.value];
        final String teacher        = data[SUBJECT_TEACHER.value];

        if (!university.checkStudentInUniversity(studentName))
        {
            Student newStudent = new Student(studentName, studentEmail);
            newStudent.addCourse(subject);
            uniStudents.put(studentName, newStudent);
        }

        if (!university.checkTeacherInUniversity(teacher))
        {
            Teacher newTeacher = new Teacher(teacher);
            newTeacher.addCourse(classroom);
            newTeacher.addSubject(subject);
            newTeacher.addStudent(studentName);
            uniTeachers.put(teacher, newTeacher);
        }

        if(!university.checkSubjectInCourses(subject))
        {
            Course newCourse = new Course(subject);
            newCourse.addStudent(uniStudents.get(studentName));
            newCourse.addClassroom(classroom);
            newCourse.addTeacherName(teacher);
            uniCourses.put(newCourse.getName(), newCourse);
            return;
        }

        /* ----- UPDATE DATA ----- */
        Course actualCourse = uniCourses.get(subject);
        Student actualStudent = uniStudents.get(studentName);
        Teacher actualTeacher = uniTeachers.get(teacher);

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

    //todo tengo que implementar una nueva forma de actulizar los datos de la universidad (updateData)
    public static boolean uploadDataToUniversity(final University _university, List<String> _data)
    {
        if (_data == null || _data.isEmpty()) return false;

        for (String line : _data.subList(1, _data.size()))   updateData(_university, line.split(","));

        return true;
    }

    /** Main solution */
    @Override
    public void solution (University university) {
        boolean result = uploadDataToUniversity(university, CSVManager.getDataFromFileAsList(inputFile));

        if (!result) return;

        System.out.print("Data from CSV imported correctly!\n");
        System.out.println("Writing the solution file...");

        if (!extractStudentsWithCourses(university)) return;

        System.out.println("Writing file was done correctly!");
    }

    @Override
    public void exportAsCSV(University university) {

    }
}
