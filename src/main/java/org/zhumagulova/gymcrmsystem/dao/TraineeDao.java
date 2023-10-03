package org.zhumagulova.gymcrmsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zhumagulova.gymcrmsystem.model.Trainee;


@Repository
public class TraineeDao {

    private Storage storage;

    @Autowired
    void setStorage(Storage storage) {
        this.storage = storage;
    }
    private static final String NAMESPACE = "Trainee_";

    public void create(Trainee trainee) {
        storage.put(NAMESPACE + trainee.getId(), trainee);
    }

    public void update(Trainee trainee) {
        storage.put(NAMESPACE + trainee.getId(), trainee);
    }

    public void delete(long id) {
        storage.delete(NAMESPACE + id);
    }

    public Trainee select(long id) {
        try {
            return (Trainee) storage.get(NAMESPACE + id);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
