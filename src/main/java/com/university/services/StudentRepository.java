package com.university.repository;

import com.university.model.Student;

public class StudentRepository implements CRUDRepository<Student> {
    @Override
    public void create(Student entity) {

    }

    @Override
    public Student read(int id) {
        return null;
    }

    @Override
    public void update(int id, Student entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public String getIdentifier() {
        return "";
    }

    @Override
    public Class<Student> getEntityClass() {
        return null;
    }
}
