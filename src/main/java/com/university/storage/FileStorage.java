package com.university.storage;

import com.university.model.Student;

import java.util.List;

public class FileStorage implements Storage {
    private String id;
    private String path;

    FileStorage (String id) {
        this.id = id;
        this.path = rootPath.concat(String.format("data/%s.txt", id));
    }

    @Override
    public void save(Student student) {

    }

    @Override
    public Student get(String id) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        return List.of();
    }

    @Override
    public <T> void update(T Student) {

    }

    @Override
    public void delete(String id) {

    }
}
