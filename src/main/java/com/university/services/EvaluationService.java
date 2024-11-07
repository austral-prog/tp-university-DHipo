package com.university.services;

import com.university.model.University;
import com.university.services.CRUDRepository;
import com.university.model.Entity;
import java.util.List;

public class EvaluationService implements CRUDRepository {
    private University university;

    public EvaluationService(University _university) {
      this.university = _university;
    }

    @Override
    public void update(int id, Entity entity) {return;}
    @Override
    public Entity read(int id) {return null;}
    @Override
    public void create(Entity entity) {}
    @Override
    public void delete(int id) {}
    @Override
    public String getIdentifier() {return null;}
    @Override
    public Class<Entity> getEntityClass() {return null;} 
}
