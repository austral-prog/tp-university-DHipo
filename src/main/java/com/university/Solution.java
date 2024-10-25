package com.university;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.lang.Float.parseFloat;
import static javax.swing.UIManager.put;

public class Solution {

    private static final String resourcesPath = "src/main/resources/";

    /* --- exercise one : Variables --- */
    private static final String solutionFile = resourcesPath.concat("solution.csv");
    private static final String inputFile = resourcesPath.concat("input.csv");

    // Classroom, Subject, Student_Name, Student_Email, Subject_Teacher
    public enum IndexData
    {
        CLASSROOM,
        SUBJECT,
        STUDENT_NAME,
        STUDENT_EMAIL,
        SUBJECT_TEACHER
    }


    /* --- exercise two : Variables --- */
    private static final String solutionFile_2 = resourcesPath.concat("solution_2.csv");
    private static final String inputFile_2 = resourcesPath.concat("input_2.csv");

    // Student,Subject,Evaluation_Type,Evaluation_Name,Exercise_Name,Grade
    private enum IndexData_2 {
        STUDENT_NAME,
        SUBJECT_NAME,
        EVALUATION_TYPE,
        EVALUATION_NAME,
        EXERCISE_NAME,
        GRADE
    };

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
    //todo tengo que implementar una nueva forma de actulizar los datos de la universidad (updateData)
    public static boolean uploadDataToUniversity(final University _university, List<String> _data)
    {
        if (_data == null || _data.isEmpty()) return false;

        for (String line : _data)
            _university.updateData(line.split(","));

        return true;
    }

    public static boolean exerciseOne(final University _university) {
        boolean success = false;

        boolean result = uploadDataToUniversity(_university, CSVManager.getDataFromFileAsList(inputFile));

        if (!result) return success;

        System.out.print("Data from CSV imported correctly!\n");
        System.out.println("Writing the solution file...");

        if (!extractStudentsWithCourses(_university)) return success;

        System.out.println("Writing file was done correctly!");
        success = true;
        return success;
    }

    /* --- private : Methods - Exercise Two --- */
    private static void checkEvalInStudent() {}

    private static void addDataToEvaluation(final String _key, final String _value, Evaluation evaluation)
    {

        // Estos son los nombres de las keys
        // Student,Subject,Evaluation_Type,Evaluation_Name,Exercise_Name,Grade
        switch (_key) {
            case "Student": evaluation.setStudent(_value); break;
            case "Subject": evaluation.setSubject(_value); break;
            case "Evaluation_Type": evaluation.setType(_value); break;
            case "Evaluation_Name": evaluation.setName(_value); break;
            case "Exercise_Name": evaluation.addExercise(_value); break;
            case "Grade": evaluation.addGrade(parseFloat(_value)); break;
        }
    }

    private static void addEvaluationToStudent(Student _student, String[] _line)
    {
        Evaluation eval = new Evaluation();

        eval.setName(_line[IndexData_2.EVALUATION_NAME.ordinal()]);
        eval.setType(_line[IndexData_2.EVALUATION_TYPE.ordinal()]);
        eval.setSubject(_line[IndexData_2.EVALUATION_NAME.ordinal()]);
        eval.setStudent(_line[IndexData_2.STUDENT_NAME.ordinal()]);

        Map<String, Float> results = new HashMap<>();
        results.put(_line[IndexData_2.EXERCISE_NAME.ordinal()], parseFloat(_line[IndexData_2.GRADE.ordinal()]));
        eval.setResults(results);

        _student.addEvaluation(eval);
    }

    /* --- public : Methods - Exercise Two --- */
    public static boolean exerciseTwo(University _university) {
        List<String> rawData = CSVManager.getDataFromFileAsList(inputFile_2);
        if (rawData == null || rawData.isEmpty()) return false;

        Map<String, Student> students = _university.getStudents();

        for (int i = 1; i < rawData.size(); i++) {
            String[] line = rawData.get(i).split(",");

            Student student = students.get(line[IndexData_2.STUDENT_NAME.ordinal()]);
            if (student == null) {
                /// Genero el estudiante, agrego la evaluacion y lo agrego a la universidad
                student = new Student(line[IndexData_2.STUDENT_NAME.ordinal()]);
                addEvaluationToStudent(student, line);
                students.put(student.getName(), student);
                continue;
            }

            addEvaluationToStudent(student, line);
        }
        students.get("Eve Black").showEvaluations();
        return true;
    }

}
