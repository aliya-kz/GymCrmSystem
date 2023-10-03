package org.zhumagulova.gymcrmsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Trainer {
    private long id;
    private String specialization;
    private List<Trainee> traineeList;
    private User user;
    private TrainingType trainingType;

    public Trainer(long id) {
        this.id = id;
    }
}
