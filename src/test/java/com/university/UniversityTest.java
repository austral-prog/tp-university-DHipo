package com.university;

import com.university.Course.Course;
import com.university.Student.Student;
import com.university.Universidad.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UniversityTest {
    private University university;

    @BeforeEach
    public void setUp() {
        university = new University();
    }

    @Test
    public void testAddStudent() {
        Student student = new Student("Alice", "alice@example.com");
        assertTrue(university.addStudent(student), "Student should be added successfully.");
        assertFalse(university.addStudent(student), "Duplicate student should not be added.");
        assertTrue(university.getStudents().containsKey("Alice"), "University should contain Alice.");
    }

    @Test
    public void testAddCourse() {
        Course course = new Course("Mathematics");
        assertTrue(university.addCourse(course), "Course should be added successfully.");
        assertFalse(university.addCourse(course), "Duplicate course should not be added.");
        assertTrue(university.getCourses().containsKey("Mathematics"), "University should contain Mathematics course.");
    }

    @Test
    public void testUpdateDataNewStudent() {
        String[] data = {"Classroom A", "Mathematics", "Alice", "alice@example.com", "Prof. Smith"};
        assertDoesNotThrow(() -> university.updateData(data), "Update data should not throw an exception.");

        assertTrue(university.getStudents().containsKey("Alice"), "Alice should be added to the university.");
        assertTrue(university.getTeachers().containsKey("Prof. Smith"), "Prof. Smith should be added to the university.");
        assertTrue(university.getCourses().containsKey("Mathematics"), "Mathematics course should be added to the university.");
    }
}
