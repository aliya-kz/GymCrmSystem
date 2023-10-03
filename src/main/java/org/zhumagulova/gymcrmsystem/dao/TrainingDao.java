package org.zhumagulova.gymcrmsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zhumagulova.gymcrmsystem.model.Training;


@Repository
public class TrainingDao {

    private Storage storage;

    @Autowired
    void setStorage(Storage storage) {
        this.storage = storage;
    }

    private final static String NAMESPACE = "Training_";

    public void create(Training training) {
        storage.put(NAMESPACE + training.getId(), training);
    }

    public Training select(long id) {
        try {
            return (Training) storage.get(NAMESPACE + id);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
