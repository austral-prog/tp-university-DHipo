package com.university.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("John Doe", "john.doe@example.com");

        // Añadiendo cursos y evaluaciones
        student.addCourse("Math");
        student.addCourse("Science");

        Evaluation mathEval = new Evaluation("FINAL_PRACTICAL_WORK", "Final Exam", "Math");
        Evaluation scienceEval = new Evaluation("FINAL_PRACTICAL_WORK", "Final Exam", "Science");

        // Añadiendo resultados a las evaluaciones
        addGrades(mathEval, 90, 85, 88);
        addGrades(scienceEval, 78, 82, 88);

        student.addEvaluation(mathEval);
        student.addEvaluation(scienceEval);
    }

    private void addGrades(Evaluation evaluation, float... grades) {
        Map<String, Float> results = new HashMap<>();
        for (int i = 0; i < grades.length; i++) {
            results.put("Exercise " + (i + 1), grades[i]);
        }
        evaluation.setResults(results);
    }

    @Test
    void testAddCourse() {
        // Comprobando que un curso se añade correctamente
        student.addCourse("History");
        assertTrue(student.getCourses().contains("History"), "El curso no fue añadido correctamente.");

        // Intentando añadir un curso ya existente
        student.addCourse("Math");
        assertEquals(3, student.getCourses().size(), "El curso 'Math' no debe ser añadido nuevamente.");
    }

    @Test
    void testRemoveCourse() {
        // Comprobando la eliminación de un curso
        int result = student.removeCourse("Math");
        assertEquals(1, result, "El curso debería haberse eliminado correctamente.");

        // Intentando eliminar un curso que no existe
        result = student.removeCourse("History");
        assertEquals(-1, result, "El curso no debería ser eliminado porque no existe.");
    }

    @Test
    void testAddEvaluation() {
        // Comprobando que las evaluaciones se añaden correctamente
        Evaluation historyEval = new Evaluation("FINAL_PRACTICAL_WORK", "Final Exam", "History");
        addGrades(historyEval, 85, 90, 92);
        student.addEvaluation(historyEval);

        assertEquals(3, student.getGrades().size(), "La evaluación no se añadió correctamente.");
    }

    @Test
    void testGetAllPromedio() {
        // Comprobando que se obtienen los promedios correctamente
        Map<String, Map<String, Float>> averages = student.getAllPromedio();
        assertTrue(averages.containsKey("Math"), "No se encontró la materia 'Math'.");
        assertTrue(averages.containsKey("Science"), "No se encontró la materia 'Science'.");

        assertEquals(263.0, averages.get("Math").get("Final Exam"), 0.01, "El promedio de 'Math' es incorrecto.");
        assertEquals(248.0, averages.get("Science").get("Final Exam"), 0.01, "El promedio de 'Science' es incorrecto.");
    }

    @Test
    void testShowEvaluations() {
        // Este método es solo para mostrar información, así que se puede omitir la comprobación directa en las pruebas.
        // Sin embargo, puedes verificar si el método no lanza excepciones.
        assertDoesNotThrow(() -> student.showEvaluations(), "El método showEvaluations lanzó una excepción.");
    }
}
