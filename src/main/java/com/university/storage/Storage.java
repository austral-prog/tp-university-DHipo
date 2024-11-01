package com.university.storage;

import com.university.model.Student;

import java.util.List;

public interface Storage {
    String rootPath = "src/";
    void save(Student student);
    Student get(String id);
    List<Student> getAll();
    <T> void update(T Student);
    void delete(String id);
}
