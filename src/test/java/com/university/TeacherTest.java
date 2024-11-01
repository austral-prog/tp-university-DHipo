package com.university;

import com.university.model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeacherTest {
    private Teacher teacher;

    @BeforeEach
    public void setUp() {
        teacher = new Teacher("John Doe");
    }

    public void execMainFunction() {
        try {
            App.main(new String[]{});  // Running the App's main method
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute App.main()");
        }
    }

    @Test
    public void testConstructor() {
        assertEquals("John Doe", teacher.getName());
        assertTrue(teacher.getCourses().isEmpty());
        assertTrue(teacher.getSubjects().isEmpty());
        assertTrue(teacher.getStudents().isEmpty());
    }

    @Test
    public void testAddCourse() {
        teacher.addCourse("Mathematics");
        assertTrue(teacher.getCourses().contains("Mathematics"));

        // Ensure duplicates are not added
        teacher.addCourse("Mathematics");
        assertEquals(1, teacher.getCourses().size());
    }

    @Test
    public void testAddSubject() {
        teacher.addSubject("Physics");
        assertTrue(teacher.getSubjects().contains("Physics"));

        // Ensure duplicates are not added
        teacher.addSubject("Physics");
        assertEquals(1, teacher.getSubjects().size());
    }

    @Test
    public void testAddStudent() {
        teacher.addStudent("Alice");
        assertTrue(teacher.getStudents().contains("Alice"));

        // Ensure duplicates are not added
        teacher.addStudent("Alice");
        assertEquals(1, teacher.getStudents().size());
    }

    @Test
    public void testRemoveCourse() {
        teacher.addCourse("Mathematics");
        teacher.removeCourse("Mathematics");
        assertFalse(teacher.getCourses().contains("Mathematics"));
    }

    @Test
    public void testRemoveSubject() {
        teacher.addSubject("Physics");
        teacher.removeSubject("Physics");
        assertFalse(teacher.getSubjects().contains("Physics"));
    }

    @Test
    public void testRemoveStudent() {
        teacher.addStudent("Alice");
        teacher.removeStudent("Alice");
        assertFalse(teacher.getStudents().contains("Alice"));
    }

    @Test
    public void testGetCoursesImmutable() {
        List<String> courses = teacher.getCourses();
    }

    @Test
    public void testToString() {
        teacher.addCourse("Mathematics");
        teacher.addSubject("Physics");
        teacher.addStudent("Alice");

        String expectedString = """
                {
                \tName: John Doe
                \tSubjects: [Physics]
                \tCourses: [Mathematics]
                \tStudents: [Alice]
                }
                """;

        assertEquals(expectedString.trim(), teacher.toString().trim());
    }
}
