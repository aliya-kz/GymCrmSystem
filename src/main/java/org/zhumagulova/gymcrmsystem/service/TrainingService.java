package org.zhumagulova.gymcrmsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhumagulova.gymcrmsystem.dao.TrainingDao;
import org.zhumagulova.gymcrmsystem.model.Training;

@Service
public class TrainingService {
    @Autowired
    private TrainingDao trainingDAO;

    public void create (Training training) {
        trainingDAO.create(training);
    }

    public Training select (long id) {
        return trainingDAO.select(id);
    }
}