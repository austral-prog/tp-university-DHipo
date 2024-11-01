package com.university.solution;

import com.university.Course;
import com.university.University;
import com.university.csv.CSVManager;
import com.university.person.Student;
import com.university.person.Teacher;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.university.solution.ExerciseOne.IndexData.*;

public class ExerciseOne extends Solution {
    /* --- exercise one : Variables --- */
    private static final String solutionFile = resourcesPath.concat("solution.csv");
    private static final String inputFile = resourcesPath.concat("input.csv");

    public ExerciseOne(University university) { this.university = university; }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University _university) {
        university = _university;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> _data) {
        data = _data;
    }

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
    @Override
    public void processData()
    {
        data = CSVManager.getDataFromFileAsList(inputFile);

        if (data == null) return;

        for (String line : data.subList(1, data.size())) updateData(university, line.split(","));
    }

    /** Main solution */
    @Override
    public void solution () {
        System.out.println("Doing exercise one...\n");
        processData();

        if (data == null) {
            System.out.println("Impossible to process the data");
            return;
        }

        exportAsCSV();
        System.out.println("Exercise one completed!");
    }

    @Override
    public void exportAsCSV()
    {
        System.out.print("Exporting the solution into solution.csv file --> ");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(solutionFile));
            Map<String, Student> students = university.getStudents();
            List<String> sorted = students.keySet().stream().sorted().toList();

            writer.write("Student_Name,Course_Count\n");

            for (String e : sorted)
                writer.write(String.format("%s,%s\n", e, students.get(e).getCountCourses()));

            writer.close();
            System.out.println("Success!");
        }catch (IOException e) {
            System.out.println("Error!");
            System.out.println(e.getMessage());
        }
    }
}
