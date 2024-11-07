package com.university.services;

import com.university.model.University;
import com.university.model.Entity;
import com.university.services.CRUDRepository;

public class TeacherService implements CRUDRepository {
    private University university;

    public TeacherService (University _university) {
      this.university = _university;
    }
    @Override
    public void update(int id, Entity entity) {return;}
    @Override
    public Entity read(int id){return null;}
    @Override
    public void create(Entity entity) {return;}
    @Override
    public void delete(int id){return;}
    @Override
    public String getIdentifier(){return null;}
    @Override
    public Class<Entity> getEntityClass(){return null;}
}
