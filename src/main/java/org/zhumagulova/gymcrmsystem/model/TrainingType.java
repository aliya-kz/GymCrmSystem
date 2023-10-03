package org.zhumagulova.gymcrmsystem.model;

import lombok.Data;

@Data
public class TrainingType {
    private long id;
    private String trainingTypeName;

    public TrainingType(String trainingTypeName) {
        this.trainingTypeName = trainingTypeName;
    }
}
