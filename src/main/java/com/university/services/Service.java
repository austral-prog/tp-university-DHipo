package com.university.services;

import com.university.model.Student;

import java.util.List;

public interface Service {
    void guardar(Student alumno);
    Student obtener(String id);
    List<Student> obtenerTodos();
    void actualizar(Student alumno);
    void eliminar(String id);
    void saveRepository();
}
