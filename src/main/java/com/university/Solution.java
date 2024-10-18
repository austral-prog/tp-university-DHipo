package com.university;

import com.university.Student.Student;
import com.university.Universidad.University;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public static boolean exerciseTwo(University _university) {
        Map<String, List<String>> data = CSVManager.getDataFromFileAsMap(inputFile_2);
        System.out.println(data);
        if (data == null || data.isEmpty()) return false;

        String[] keys = data.keySet().toArray(new String[0]);

        // data.get(keys[0]).size() -> cantidad de lineas del archivo
        for (int i = 0; i < data.get(keys[0]).size(); i++) {
            // keys.length -> cantidad de columnas
            for (int j = 0; j < keys.length; j++) {
                System.out.println(data.get(keys[j]).get(i));
            }
            System.out.println();
        }
        return true;
    }

}
