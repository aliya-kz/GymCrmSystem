package org.zhumagulova.gymcrmsystem.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class TrainingType {
    private long id;
    private String trainingTypeName;

    public TrainingType(String trainingTypeName) {
        this.trainingTypeName = trainingTypeName;
    }
}
