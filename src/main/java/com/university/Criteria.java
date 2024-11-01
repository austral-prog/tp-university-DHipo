package com.university;

import java.util.List;

public class Criteria {
    public CriteriaType m_criteriaType;
    public float grade = 0;

    public enum CriteriaType {
        AVERAGE_ABOVE_VALUE(0),
        MAX_ABOVE_VALUE(1),
        MIN_ABOVE_VALUE(2);

        int value = 0;
        CriteriaType(int _v) {value = _v;}
    }

    public Criteria(String _criteriaType, float _grade) {
        grade = _grade;
        setCriteriaType(_criteriaType);
    }

    public Criteria(String _criteriaType, String _grade) {
        grade = Float.parseFloat(_grade);
        setCriteriaType(_criteriaType);
    }

    public Criteria(CriteriaType _criteriaType, float _grade) {
        m_criteriaType = _criteriaType;
        grade = _grade;
    }

    public void setCriteriaType(CriteriaType criteriaType) {
        m_criteriaType = criteriaType;
    }

    public void setCriteriaType(int criteriaIndex) {
        m_criteriaType = CriteriaType.values()[criteriaIndex];
    }

    public void setCriteriaType(String criteriaType) {
        m_criteriaType = CriteriaType.valueOf(criteriaType);
    }

    @Override
    public String toString() {
        return String.format(" {criteriaType= %s, grade= %.2f}", m_criteriaType, grade);
    }
}
