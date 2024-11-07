package com.university.services;

import com.university.model.University;
import com.university.services.CRUDRepository;

import com.university.model.Entity;
import com.university.model.Student;

public class StudentService implements CRUDRepository {
    private University university;

    public StudentService (University _university) {
      this.university = _university;
    }
    @Override
    public void update(int id, Entity entity){
      return;
    }
    
    @Override
    public Entity read(int id){
      university.getStudents().values();
      return null;
    }
    @Override
    public void create(Entity entity){
      if (entity instanceof Student == false) return;
      Student student = (Student) entity;
      university.addStudent(student);
    }
    @Override
    public void delete(int id){return;}
    @Override
    public String getIdentifier(){return null;}
    @Override
    public Class<Entity> getEntityClass() {return null;}
}
