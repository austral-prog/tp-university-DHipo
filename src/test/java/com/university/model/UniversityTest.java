package com.university.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniversityTest {

    private University university;
    private Student student;
    private Teacher teacher;
    private Course course;

    @BeforeEach
    void setUp() {
        university = new University();

        // Creación de objetos con solo un parámetro
        student = new Student("John Doe", "john.doe@example.com");
        teacher = new Teacher("Dr. Smith");
        course = new Course("Math 101");

        university.addStudent(student);
        university.addTeacher(teacher);
        university.addCourse(course);
    }

    @Test
    void testAddStudent() {
        // Comprobando que el estudiante se añade correctamente
        Student newStudent = new Student("Jane Doe", "jane.doe@example.com");
        assertTrue(university.addStudent(newStudent), "El estudiante debería haberse añadido correctamente.");
        assertFalse(university.addStudent(student), "El estudiante no debe añadirse dos veces.");
    }

    @Test
    void testAddTeacher() {
        // Comprobando que el profesor se añade correctamente
        Teacher newTeacher = new Teacher("Dr. Brown");
        assertTrue(university.addTeacher(newTeacher), "El profesor debería haberse añadido correctamente.");
        assertFalse(university.addTeacher(teacher), "El profesor no debe añadirse dos veces.");
    }

    @Test
    void testAddCourse() {
        // Comprobando que el curso se añade correctamente
        Course newCourse = new Course("Science 101");
        assertTrue(university.addCourse(newCourse), "El curso debería haberse añadido correctamente.");
        assertFalse(university.addCourse(course), "El curso no debe añadirse dos veces.");
    }

    @Test
    void testAddEvaluation() {
        // Creación de la evaluación con tres parámetros (tipo de evaluación, nombre de la evaluación, y materia)
        Evaluation mathEval = new Evaluation("FINAL", "Primer Final", "Math");
        university.addEvaluation(mathEval);

        // Verificando que la evaluación fue añadida correctamente
        assertTrue(university.getEvaluations().containsKey("Math"), "La materia 'Math' debería contener evaluaciones.");
        assertEquals(1, university.getEvaluations().get("Math").size(), "La cantidad de evaluaciones debería ser 1.");
    }

    @Test
    void testCheckStudentInUniversity() {
        // Comprobando que se puede verificar si un estudiante está en la universidad
        assertTrue(university.checkStudentInUniversity("John Doe"), "El estudiante debería estar en la universidad.");
        assertFalse(university.checkStudentInUniversity("Nonexistent Student"), "El estudiante no debería estar en la universidad.");
    }

    @Test
    void testCheckTeacherInUniversity() {
        // Comprobando que se puede verificar si un profesor está en la universidad
        assertTrue(university.checkTeacherInUniversity("Dr. Smith"), "El profesor debería estar en la universidad.");
        assertFalse(university.checkTeacherInUniversity("Nonexistent Teacher"), "El profesor no debería estar en la universidad.");
    }

    @Test
    void testCheckSubjectInCourses() {
        // Comprobando que se puede verificar si una materia está en los cursos
        assertTrue(university.checkSubjectInCourses("Math 101"), "El curso 'Math 101' debería estar en la universidad.");
        assertFalse(university.checkSubjectInCourses("History 101"), "El curso 'History 101' no debería estar en la universidad.");
    }

    @Test
    void testInUniversity() {
        // Comprobando el método genérico inUniversity
        assertTrue(university.inUniversity(student), "El estudiante debería estar en la universidad.");
        assertTrue(university.inUniversity(teacher), "El profesor debería estar en la universidad.");
        assertTrue(university.inUniversity(course), "El curso debería estar en la universidad.");
        assertFalse(university.inUniversity(new Evaluation("FINAL", "Primer Final", "History")), "La evaluación no debería estar en la universidad.");
    }

    @Test
    void testPrintData() {
        // Este método imprime los datos de la universidad, por lo que verificamos que no lance excepciones.
        assertDoesNotThrow(() -> university.printData(), "El método printData lanzó una excepción.");
    }

    @Test
    void testToString() {
        // Comprobando la representación en cadena de la universidad
        String expectedString = "University: Austral University";
        assertEquals(expectedString, university.toString(), "La representación en cadena no es la esperada.");
    }
}
