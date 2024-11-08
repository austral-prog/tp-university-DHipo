package com.university.services;

import com.university.model.Course;
import com.university.model.University;

import java.util.HashSet;
import java.util.Set;

public class CourseService implements CRUDRepository<Course>, Service {
    private static University university;
    private static Set<Integer> courseIDs = new HashSet<>();

    // Método para actualizar el conjunto de IDs de cursos actuales en la universidad
    public void updateIDs() {
        courseIDs.clear();
        university.getCourses().values().forEach(course -> courseIDs.add(course.getId()));
    }

    public Set<Integer> getCourseIDs() { return courseIDs; }

    public CourseService(University _university) {
        university = _university;
        updateIDs();
    }

    @Override
    public void update(int id, Course course) {
        // Verifica que el ID exista y que el curso no sea nulo
        if (!courseIDs.contains(id) || course == null) return;

        // Actualiza el curso en la universidad usando su nombre como clave
        university.getCourses().put(course.getName(), course);
        updateIDs(); // Actualiza la lista de IDs
    }

    @Override
    public Course read(int id) {
        for (Course course : university.getCourses().values()) {
            if (course.getId() == id) return course;
        }
        return null;
    }

    @Override
    public void create(Course course) {
        if (course == null) return;
        university.addCourse(course);
        updateIDs();
    }

    @Override
    public void delete(int id) {
        if (!courseIDs.contains(id)) return;
        university.getCourses().values().removeIf(course -> course.getId() == id);
        updateIDs();
    }

    @Override
    public String getIdentifier() {
        return "Course";
    }

    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }
}
