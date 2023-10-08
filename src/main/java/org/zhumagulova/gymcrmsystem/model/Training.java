package org.zhumagulova.gymcrmsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Training implements TrainingEntity {
    private long id;
    private Trainee trainee;
    private Trainer trainer;
    private String trainingName;
    private TrainingType trainingType;
    private LocalDate trainingDate;
    //minutes
    private int trainingDuration;
}
