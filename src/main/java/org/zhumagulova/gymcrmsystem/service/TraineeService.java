package org.zhumagulova.gymcrmsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhumagulova.gymcrmsystem.dao.TraineeDao;
import org.zhumagulova.gymcrmsystem.model.Trainee;

@Service
public class TraineeService {
    @Autowired
    private TraineeDao traineeDAO;

    public void create (Trainee trainee) {
        traineeDAO.create(trainee);
    }

    public void update (Trainee trainee) {
        traineeDAO.update(trainee);
    }

    public void delete (long id) {
        traineeDAO.delete(id);
    }

    public Trainee select (long id) {
        return traineeDAO.select(id);
    }

}

