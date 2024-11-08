package com.university.service;

import com.university.model.Teacher;
import com.university.model.University;
import com.university.services.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherServiceTest {

    private University university;
    private TeacherService teacherService;
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        university = new University();
        teacherService = new TeacherService(university);
        teacher = new Teacher("Dr. John Smith"); // El ID se asigna automáticamente
    }

    @Test
    void testCreateTeacher() {
        teacherService.create(teacher);
        assertTrue(teacherService.getTeachersIDs().contains(teacher.getId()), "El profesor debería ser agregado.");
    }

    @Test
    void testReadTeacher() {
        teacherService.create(teacher);
        Teacher foundTeacher = teacherService.read(teacher.getId());
        assertNotNull(foundTeacher, "El profesor debería ser encontrado.");
        assertEquals(teacher.getName(), foundTeacher.getName(), "Los nombres deberían coincidir.");
    }

    @Test
    void testUpdateTeacher() {
        teacherService.create(teacher);
        Teacher updatedTeacher = new Teacher("Dr. Jane Doe"); // El ID se mantiene al actualizar
        updatedTeacher.setId(teacher.getId()); // Mantén el mismo ID del original
        teacherService.update(teacher.getId(), updatedTeacher);
        Teacher foundTeacher = teacherService.read(teacher.getId());
        assertNotNull(foundTeacher, "El profesor debería ser encontrado.");
        assertEquals("Dr. Jane Doe", foundTeacher.getName(), "El nombre debería haber sido actualizado.");
    }

    @Test
    void testDeleteTeacher() {
        teacherService.create(teacher);
        assertTrue(teacherService.getTeachersIDs().contains(teacher.getId()), "El profesor debería estar presente.");
        teacherService.delete(teacher.getId());
        assertFalse(teacherService.getTeachersIDs().contains(teacher.getId()), "El profesor debería haber sido eliminado.");
    }

    @Test
    void testDeleteNonExistentTeacher() {
        teacherService.delete(999); // ID que no existe
        assertTrue(teacherService.getTeachersIDs().isEmpty(), "No debería haber cambios si el profesor no existe.");
    }
}
