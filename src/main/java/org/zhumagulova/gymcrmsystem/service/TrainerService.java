package org.zhumagulova.gymcrmsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhumagulova.gymcrmsystem.dao.TrainerDao;
import org.zhumagulova.gymcrmsystem.model.Trainer;

@Service
public class TrainerService {
    @Autowired
    private TrainerDao trainerDAO;

    public void create(Trainer trainer) {
        trainerDAO.create(trainer);
    }

    public void update(long id, Trainer trainer) {
        trainerDAO.update(id, trainer);
    }

    public Trainer select(long id) {
        return trainerDAO.select(id);
    }
}