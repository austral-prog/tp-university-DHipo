package com.university.service;

import com.university.model.Course;
import com.university.model.University;
import com.university.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {

    private University university;
    private CourseService courseService;

    @BeforeEach
    public void setUp() {
        university = new University();
        courseService = new CourseService(university);
    }

    @Test
    public void testCreateCourse() {
        Course course = new Course("Math 101");
        courseService.create(course);

        assertTrue(university.getCourses().containsKey("Math 101"));
        assertTrue(courseService.getCourseIDs().contains(course.getId()));
    }

    @Test
    public void testReadCourse() {
        Course course = new Course("History 101");
        university.addCourse(course);

        Course retrievedCourse = courseService.read(course.getId());

        assertNotNull(retrievedCourse);
        assertEquals("History 101", retrievedCourse.getName());
    }

    @Test
    public void testDeleteCourse() {
        Course course = new Course("Chemistry 101");
        university.addCourse(course);
        courseService.updateIDs(); // Aseguramos que `courseIDs` esté actualizado

        courseService.delete(course.getId());

        assertFalse(university.getCourses().containsKey("Chemistry 101"));
        assertFalse(courseService.getCourseIDs().contains(course.getId()));
    }

    @Test
    public void testGetCourseIDs() {
        Course course1 = new Course("Biology 101");
        Course course2 = new Course("Computer Science 101");
        university.addCourse(course1);
        university.addCourse(course2);
        courseService.updateIDs(); // Actualiza el conjunto de IDs

        Set<Integer> courseIDs = courseService.getCourseIDs();
        assertTrue(courseIDs.contains(course1.getId()));
        assertTrue(courseIDs.contains(course2.getId()));
        assertEquals(2, courseIDs.size());
    }
}
