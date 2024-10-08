package com.university;

import com.university.Universidad.University;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args)
    {
        University university = new University();
        Map<String, List<String>> data = CSVManager.getDataFromFileAsMap("src/main/resources/input.csv");

        assert data != null;
        data.forEach((k, v) -> System.out.printf("%s: %d\n", k, v.size()));

        if (university.importDataFromCSV("src/main/resources/input.csv"))
            System.out.print("Data from CSV imported correctly!\n");

        if (university.extractStudentsWithCourses("src/main/resources/solution.csv"))
            System.out.print("Data from CSV extracted correctly!\n\t-> Name of the file \"solution.csv\"\n");
    }
}