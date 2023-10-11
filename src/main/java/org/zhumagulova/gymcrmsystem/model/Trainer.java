package org.zhumagulova.gymcrmsystem.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Trainer implements TrainingEntity {
    private long id;
    private String specialization;
    private List<Trainee> traineeList;
    private User user;
    private TrainingType trainingType;

    public Trainer(long id) {
        this.id = id;
    }
}
