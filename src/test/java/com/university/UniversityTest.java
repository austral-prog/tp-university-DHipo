package com.university;

import com.university.person.Student;
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
}
