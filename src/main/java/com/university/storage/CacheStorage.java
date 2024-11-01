package com.university.storage;

import com.university.model.Student;

import java.io.*;
import java.util.List;

public class CacheStorage implements Storage{

    private String id;
    private String path;
    private Writer writer;
    private Reader reader;

    CacheStorage(String id) {
        this.id = id;
        this.path = rootPath.concat(String.format("cache/%s.tmp", id));
        try {
            this.writer = new BufferedWriter(new FileWriter(this.path));
            this.reader = new BufferedReader(new FileReader(this.path));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
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
