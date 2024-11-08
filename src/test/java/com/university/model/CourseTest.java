package com.university.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    private Course course;
    private Student student;

    @BeforeEach
    void setUp() {
        course = new Course("Math 101");
        student = new Student("Alice");
    }

    @Test
    void testConstructorAndID() {
        // Verifica que el ID sea asignado correctamente y sea único
        Course course2 = new Course("Physics 101");
        assertNotEquals(course.getId(), course2.getId(), "Los IDs deberían ser diferentes.");
    }

    @Test
    void testAddTeacherName() {
        assertEquals(1, course.addTeacherName("Dr. Smith"), "El nombre del profesor debería ser agregado.");
        assertEquals(-1, course.addTeacherName("Dr. Smith"), "El nombre del profesor no debería ser agregado dos veces.");
    }

    @Test
    void testAddClassroom() {
        assertEquals(1, course.addClassroom("Room 101"), "El aula debería ser agregada.");
        assertEquals(-1, course.addClassroom("Room 101"), "El aula no debería ser agregada dos veces.");
    }

    @Test
    void testAddStudent() {
        assertEquals(1, course.addStudent(student), "El estudiante debería ser agregado.");
        assertEquals(-1, course.addStudent(student), "El estudiante no debería ser agregado dos veces.");
    }

    @Test
    void testToString() {
        course.addTeacherName("Dr. Smith");
        course.addClassroom("Room 101");
        course.addStudent(student);
        String expectedString = String.format(
                "{id:%d\n\tName: Math 101\n\tClassrooms: [Room 101]\n\tTeachers: [Dr. Smith]\n\tStudents (count): 1\n\tCriteria: %s\n}\n",
                course.getId(), course.getCriteriaType()
        );
        assertEquals(expectedString, course.toString(), "El formato de la cadena no es el esperado.");
    }

    @Test
    void testGetAproveSubjects() {
        assertTrue(course.getAproveSubjects().isEmpty(), "La lista de materias aprobadas debería estar vacía.");
    }

    @Test
    void testGetStateOfSubject() {
        assertFalse(course.getStateOfSubject("Math 101"), "El estado del curso debería ser falso por defecto.");
    }
}
