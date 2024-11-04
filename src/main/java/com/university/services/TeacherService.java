package com.university.services;

import com.university.model.Student;

import java.util.List;

public class TeacherService implements Service {
    @Override
    public void guardar(Student alumno) {
        return;
    }

    @Override
    public Student obtener(String id) {
        return null;
    }

    @Override
    public List<Student> obtenerTodos() {
        return List.of();
    }

    @Override
    public void actualizar(Student alumno) {

    }

    @Override
    public void eliminar(String id) {

    }

	@Override
	public void saveRepository() { return;}
}
