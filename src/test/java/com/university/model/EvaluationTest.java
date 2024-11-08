package com.university.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EvaluationTest {

    private Evaluation finalExamEvaluation;
    private Evaluation practicalWorkEvaluation;
    private Evaluation writtenExamEvaluation;
    private Evaluation oralExamEvaluation;

    @BeforeEach
    void setUp() {
        // Inicializando evaluaciones con datos de prueba
        finalExamEvaluation = new Evaluation("FINAL_PRACTICAL_WORK", "Final Exam", "Math");
        practicalWorkEvaluation = new Evaluation("PRACTICAL_WORK", "Lab Work", "Science");
        writtenExamEvaluation = new Evaluation("WRITTEN_EXAM", "Written Exam", "History");
        oralExamEvaluation = new Evaluation("ORAL_EXAM", "Oral Exam", "Literature");

        // Añadiendo resultados a las evaluaciones
        addGrades(finalExamEvaluation, 90, 85, 88);
        addGrades(practicalWorkEvaluation, 70, 75, 80);
        addGrades(writtenExamEvaluation, 60, 65, 70);
        addGrades(oralExamEvaluation, 95, 90);
    }

    private void addGrades(Evaluation evaluation, float... grades) {
        Map<String, Float> results = new HashMap<>();
        for (int i = 0; i < grades.length; i++) {
            results.put("Exercise " + (i + 1), grades[i]);
        }
        evaluation.setResults(results);
    }

    @Test
    void testAverageCalculation() {
        // Comprobando el cálculo de promedios
        assertEquals(87.67, finalExamEvaluation.getAverage(), 0.01, "El promedio de la evaluación final es incorrecto.");
        assertEquals(75.0, practicalWorkEvaluation.getAverage(), 0.01, "El promedio del trabajo práctico es incorrecto.");
        assertEquals(65.0, writtenExamEvaluation.getAverage(), 0.01, "El promedio del examen escrito es incorrecto.");
        assertEquals(92.5, oralExamEvaluation.getAverage(), 0.01, "El promedio del examen oral es incorrecto.");
    }

    @Test
    void testAverageByType() {
        // Comprobando el promedio por tipo de evaluación
        assertEquals(263.0, finalExamEvaluation.getAverageByType(), 0.01, "El promedio por tipo de evaluación FINAL_PRACTICAL_WORK es incorrecto.");
        assertEquals(80.0, practicalWorkEvaluation.getAverageByType(), 0.01, "El promedio por tipo de evaluación PRACTICAL_WORK es incorrecto.");
        assertEquals(65.0, writtenExamEvaluation.getAverageByType(), 0.01, "El promedio por tipo de evaluación WRITTEN_EXAM es incorrecto.");
        assertEquals(95.0, oralExamEvaluation.getAverageByType(), 0.01, "El promedio por tipo de evaluación ORAL_EXAM es incorrecto.");
    }

    @Test
    void testCompareTo() {
        // Comprobando la comparación de evaluaciones
        Evaluation anotherFinalExam = new Evaluation("FINAL_PRACTICAL_WORK", "Final Exam", "Math");
        addGrades(anotherFinalExam, 90, 85, 88);

        assertEquals(0, finalExamEvaluation.compareTo(anotherFinalExam), "Las evaluaciones deberían ser iguales.");

        anotherFinalExam.setStudent("John Doe");
        assertTrue(finalExamEvaluation.compareTo(anotherFinalExam) < 0, "La evaluación de 'John' debería ser mayor que la de 'Jane'.");

        anotherFinalExam.setName("Midterm Exam");
        assertTrue(finalExamEvaluation.compareTo(anotherFinalExam) < 0, "La evaluación 'Final Exam' debería ser mayor que 'Midterm Exam'.");
    }

    @Test
    void testToString() {
        // Comprobando la representación en cadena
        String expectedString = "Type: FINAL_PRACTICAL_WORK, Name: Final Exam, Subject: Math, Author: , Grade: {Exercise 1=90.0, Exercise 3=88.0, Exercise 2=85.0}";
        assertEquals(expectedString, finalExamEvaluation.toString(), "La salida de toString no es la esperada.");
    }
}
