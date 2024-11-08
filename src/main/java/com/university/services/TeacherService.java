package com.university.services;

import com.university.model.Teacher;
import com.university.model.University;
import com.university.model.Entity;

import java.util.HashSet;
import java.util.Set;

public class TeacherService implements CRUDRepository<Teacher> {
    private final University university;
    private final Set<Integer> teachersIDs = new HashSet<>();

    private void updateIDs() {
        teachersIDs.clear();
        university.getTeachers().values().forEach(teacher -> teachersIDs.add(teacher.getId()));
    }

    public Set<Integer> getTeachersIDs() { return teachersIDs; }

    public TeacherService(University _university) {
        this.university = _university;
        updateIDs();
    }

    @Override
    public void update(int id, Teacher teacher) {
        // Actualiza el profesor en la universidad usando su nombre como clave
        university.getTeachers().put(teacher.getName(), teacher);
        updateIDs(); // Actualiza la lista de IDs
    }

    @Override
    public Teacher read(int id) {
        for (Teacher teacher : university.getTeachers().values()) {
            if (teacher.getId() == id) return teacher;
        }
        return null;
    }

    @Override
    public void create(Teacher teacher) {
        university.addTeacher(teacher);
        updateIDs();
    }

    @Override
    public void delete(int id) {
        if (!teachersIDs.contains(id)) return;
        university.getTeachers().values().removeIf(teacher -> teacher.getId() == id);
        updateIDs();
    }

    @Override
    public String getIdentifier() {
        return "Teacher";
    }

    @Override
    public Class<Teacher> getEntityClass() {
        return Teacher.class;
    }
}
