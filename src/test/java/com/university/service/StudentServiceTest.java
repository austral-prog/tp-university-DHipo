package com.university.service;

import com.university.model.Student;
import com.university.model.University;
import com.university.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private University university;
    private StudentService studentService;
    private Student student;

    @BeforeEach
    void setUp() {
        university = new University();
        studentService = new StudentService(university);
        student = new Student( "John Doe", "johndoe@mail.com"); // Asegúrate de que la clase Student tenga un constructor adecuado
    }

    @Test
    void testCreateStudent() {
        studentService.create(student);
        assertTrue(studentService.getStudentsIDs().contains(student.getId()), "El estudiante debería ser agregado.");
    }

    @Test
    void testReadStudent() {
        studentService.create(student);
        Student foundStudent = studentService.read(student.getId());
        assertNotNull(foundStudent, "El estudiante debería ser encontrado.");
        assertEquals(student.getName(), foundStudent.getName(), "Los nombres deberían coincidir.");
    }

    @Test
    void testDeleteStudent() {
        studentService.create(student);
        assertTrue(studentService.getStudentsIDs().contains(student.getId()), "El estudiante debería estar presente.");
        studentService.delete(student.getId());
        assertFalse(studentService.getStudentsIDs().contains(student.getId()), "El estudiante debería haber sido eliminado.");
    }

    @Test
    void testDeleteNonExistentStudent() {
        studentService.delete(999); // ID que no existe
        assertTrue(studentService.getStudentsIDs().isEmpty(), "No debería haber cambios si el estudiante no existe.");
    }

    @Test
    void testUpdateWithNonExistentStudent() {
        Student nonExistentStudent = new Student("Nonexistent");
        studentService.update(999, nonExistentStudent);
        assertTrue(studentService.getStudentsIDs().isEmpty(), "No debería haber cambios si el estudiante no existe.");
    }
}
