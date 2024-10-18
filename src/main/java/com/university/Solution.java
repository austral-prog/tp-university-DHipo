package com.university;

import com.university.Student.Student;
import com.university.Universidad.University;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.lang.Float.parseFloat;

public class Solution {

    private static final String resourcesPath = "src/main/resources/";

    /* --- exercise one : Variables --- */
    private static final String solutionFile = resourcesPath.concat("solution.csv");
    private static final String inputFile = resourcesPath.concat("input.csv");

    /* --- exercise two : Variables --- */
    private static final String solutionFile_2 = resourcesPath.concat("solution_2.csv");
    private static final String inputFile_2 = resourcesPath.concat("input_2.csv");

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
    private static void addDataToEvaluation(final String _key, final String _value, Evaluation evaluation) {

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

    /* --- public : Methods - Exercise Two --- */
    public static boolean exerciseTwo(University _university) {
        Map<String, List<String>> data = CSVManager.getDataFromFileAsMap(inputFile_2);
        if (data == null || data.isEmpty()) return false;

        String[] keys = data.keySet().toArray(new String[0]);
        // data.get(keys[0]).size() -> cantidad de lineas del archivo
        for (int i = 0; i < data.get(keys[0]).size(); i++) {
            Evaluation evaluation = new Evaluation();
            // keys.length -> cantidad de columnas
            for (String key : keys)
                addDataToEvaluation(key, data.get(key).get(i), evaluation);

            System.out.println(evaluation);
        }
        return true;
    }

}
