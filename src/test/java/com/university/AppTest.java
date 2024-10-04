package com.university;

import com.university.Student.Student;
import com.university.Universidad.University;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    public String m_dbFilePath = "src/main/resources/input.csv";
    public String m_solutionFilePath = "src/main/resources/solution.csv";
    public String m_expectedFilePath = "src/main/resources/expected.csv";

    @Test
    public void testSolutionCSVMatchesExpected() {
        try {
            App.main(new String[]{});  // Running the App's main method
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute App.main()");
        }

        String solutionFilePath = "src/main/resources/solution.csv";
        String expectedFilePath = "src/main/resources/expected.csv";

        try (BufferedReader solutionReader = new BufferedReader(new FileReader(solutionFilePath));
             BufferedReader expectedReader = new BufferedReader(new FileReader(expectedFilePath))) {

            String solutionLine;
            String expectedLine;

            while ((solutionLine = solutionReader.readLine()) != null &&
                   (expectedLine = expectedReader.readLine()) != null) {
                assertEquals(expectedLine, solutionLine, "Mismatch found in the CSV file content.");
            }

            // Ensure both files have the same number of lines
            assertEquals(solutionReader.readLine(), expectedReader.readLine(), "Files have different number of lines.");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void execMainFunction() {
        try {
            App.main(new String[]{});  // Running the App's main method
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute App.main()");
        }
    }

    @Test
    public void testStudentsCount() {
        University universidad = new University();
        universidad.importDataFromCSV(m_dbFilePath);
        assertEquals(400, universidad.getStudents().size());
    }

    @Test
    public void testCoursesCount() {
        University universidad = new University();
        universidad.importDataFromCSV(m_dbFilePath);
        assertEquals(20, universidad.getCourses().size());
    }
}
