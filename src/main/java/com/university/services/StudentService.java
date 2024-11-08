package com.university.services;

import com.university.model.Student;
import com.university.model.University;

import java.util.HashSet;
import java.util.Set;

public class StudentService implements CRUDRepository<Student>, Service {
    private static University university = null;
    private static Set<Integer> studentsIDs = new HashSet<>();

    // Actualiza el conjunto de IDs de los estudiantes actuales en la universidad
    public void updateIDs() {
        studentsIDs.clear();
        university.getStudents().values().forEach(student -> studentsIDs.add(student.getId()));
    }

    public Set<Integer> getStudentsIDs() { return studentsIDs; }

    public StudentService(University _university) {
        university = _university;
        updateIDs();
    }

    @Override
    public void update(int id, Student student) {
        // Verifica que el ID exista y que el estudiante no sea nulo
        if (!studentsIDs.contains(id) || student == null) return;

        // Actualiza el estudiante en la universidad usando su nombre como clave
        university.getStudents().put(student.getName(), student);
        updateIDs(); // Actualiza la lista de IDs
    }

    @Override
    public Student read(int id) {
        for (Student student : university.getStudents().values()) {
            if (student.getId() == id) return student;
        }
        return null;
    }

    @Override
    public void create(Student student) {
        if (student == null) return;
        university.addStudent(student);
        updateIDs();
    }

    @Override
    public void delete(int id) {
        if (!studentsIDs.contains(id)) return;
        university.getStudents().values().removeIf(student -> student.getId() == id);
        updateIDs();
    }

    @Override
    public String getIdentifier() {
        return "Student";
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
}
