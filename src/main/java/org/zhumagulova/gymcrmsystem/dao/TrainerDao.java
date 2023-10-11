package org.zhumagulova.gymcrmsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zhumagulova.gymcrmsystem.model.Trainer;


@Repository
public class TrainerDao {

    private final Storage storage;

    @Autowired
    public TrainerDao(Storage storage) {
        this.storage = storage;
    }


    private final static String NAMESPACE = "Trainer_";

    public void create (Trainer trainer) {
        storage.put(NAMESPACE + trainer.getId(), trainer);
    }

    public void update (long id, Trainer trainer) {
        storage.put(NAMESPACE + id, trainer);
    }

    public Trainer select (long id) {
        try {
            return (Trainer) storage.get(NAMESPACE + id);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
