package com.university.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CriteriaTest {

    private Criteria averageCriteria;
    private Criteria maxCriteria;
    private Criteria minCriteria;

    @BeforeEach
    void setUp() {
        averageCriteria = new Criteria(Criteria.CriteriaType.AVERAGE_ABOVE_VALUE, 75);
        maxCriteria = new Criteria(Criteria.CriteriaType.MAX_ABOVE_VALUE, 90);
        minCriteria = new Criteria(Criteria.CriteriaType.MIN_ABOVE_VALUE, 60);
    }

    @Test
    void testAverageAboveValueCriteria() {
        // Promedio por encima del valor
        assertTrue(averageCriteria.getStateOfCriteria(80), "El grado debería estar por encima del promedio.");
        assertFalse(averageCriteria.getStateOfCriteria(70), "El grado debería estar por debajo del promedio.");
    }

    @Test
    void testMaxAboveValueCriteria() {
        // Valor máximo por encima del valor
        assertFalse(maxCriteria.getStateOfCriteria(91), "El grado no debería ser mayor al valor máximo.");
        assertTrue(maxCriteria.getStateOfCriteria(90), "El grado debería ser igual al valor máximo.");
        assertTrue(maxCriteria.getStateOfCriteria(89), "El grado debería estar por debajo del valor máximo.");
    }

    @Test
    void testMinAboveValueCriteria() {
        // Valor mínimo por encima del valor
        assertTrue(minCriteria.getStateOfCriteria(61), "El grado debería estar por encima del valor mínimo.");
        assertFalse(minCriteria.getStateOfCriteria(59), "El grado debería estar por debajo del valor mínimo.");
        assertTrue(minCriteria.getStateOfCriteria(60), "El grado debería ser igual al valor mínimo.");
    }

    @Test
    void testConstructorFromString() {
        Criteria criteria = new Criteria("MAX_ABOVE_VALUE", "85");
        assertEquals(Criteria.CriteriaType.MAX_ABOVE_VALUE, criteria.m_criteriaType, "El tipo de criterio debería ser MAX_ABOVE_VALUE.");
        assertEquals(85.0f, criteria.grade, "La calificación debería ser 85.");
    }

    @Test
    void testConstructorFromCriteriaType() {
        Criteria criteria = new Criteria(Criteria.CriteriaType.MIN_ABOVE_VALUE, 50);
        assertEquals(Criteria.CriteriaType.MIN_ABOVE_VALUE, criteria.m_criteriaType, "El tipo de criterio debería ser MIN_ABOVE_VALUE.");
        assertEquals(50.0f, criteria.grade, "La calificación debería ser 50.");
    }

    @Test
    void testSetCriteriaTypeFromString() {
        Criteria criteria = new Criteria("AVERAGE_ABOVE_VALUE", 70);
        criteria.setCriteriaType("MAX_ABOVE_VALUE");
        assertEquals(Criteria.CriteriaType.MAX_ABOVE_VALUE, criteria.m_criteriaType, "El tipo de criterio debería haber cambiado a MAX_ABOVE_VALUE.");
    }

    @Test
    void testSetCriteriaTypeFromInt() {
        Criteria criteria = new Criteria(Criteria.CriteriaType.AVERAGE_ABOVE_VALUE, 70);
        criteria.setCriteriaType(2); // Cambia a MIN_ABOVE_VALUE
        assertEquals(Criteria.CriteriaType.MIN_ABOVE_VALUE, criteria.m_criteriaType, "El tipo de criterio debería haber cambiado a MIN_ABOVE_VALUE.");
    }

    @Test
    void testToString() {
        String expectedString = " {criteriaType= AVERAGE_ABOVE_VALUE, grade= 75.00}";
        assertEquals(expectedString, averageCriteria.toString(), "La salida de toString no es la esperada.");
    }
}
