package org.zhumagulova.gymcrmsystem.parser;

import org.springframework.stereotype.Component;
import org.zhumagulova.gymcrmsystem.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TrainingParser {
    private final static String PROPERTIES_DELIMETER = ",";

    public Training toTraining(String entityData) {
        List<String> properties = Stream.of(entityData.split(PROPERTIES_DELIMETER))
                .map(String::trim)
                .collect(Collectors.toList());

        String traineeId = properties.get(0);
        String trainerId = properties.get(1);
        String trainingName = properties.get(2);
        String trainingDate = properties.get(3);
        String trainingDuration = properties.get(4);

        Training training = Training.builder()
                .trainer(new Trainer(Long.parseLong(trainerId)))
                .trainee(new Trainee(Long.parseLong(traineeId)))
                .trainingName(trainingName)
                .trainingDate(LocalDate.parse(trainingDate))
                .trainingDuration(Integer.parseInt(trainingDuration))
                .build();

        return training;
    }
}
