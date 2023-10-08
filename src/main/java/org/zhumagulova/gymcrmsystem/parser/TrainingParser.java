package org.zhumagulova.gymcrmsystem.parser;

import org.springframework.stereotype.Component;
import org.zhumagulova.gymcrmsystem.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TrainingParser implements Parser {
    private final static String PROPERTIES_DELIMETER = ",";
    private final static int ID_INDEX = 0;
    private final static int TRAINEE_ID_INDEX = 1;
    private final static int TRAINER_ID_INDEX = 2;
    private final static int TRAINING_NAME_INDEX = 3;
    private final static int TRAINING_DATE_INDEX = 4;
    private final static int TRAINING_DURATION_INDEX = 5;
    public Training parse(String entityData) {
        List<String> properties = Stream.of(entityData.split(PROPERTIES_DELIMETER))
                .map(String::trim)
                .collect(Collectors.toList());

        String id = properties.get(ID_INDEX);
        String traineeId = properties.get(TRAINEE_ID_INDEX);
        String trainerId = properties.get(TRAINER_ID_INDEX);
        String trainingName = properties.get(TRAINING_NAME_INDEX);
        String trainingDate = properties.get(TRAINING_DATE_INDEX);
        String trainingDuration = properties.get(TRAINING_DURATION_INDEX);

        Training training = Training.builder()
                .id(Long.parseLong(id))
                .trainer(new Trainer(Long.parseLong(trainerId)))
                .trainee(new Trainee(Long.parseLong(traineeId)))
                .trainingName(trainingName)
                .trainingDate(LocalDate.parse(trainingDate))
                .trainingDuration(Integer.parseInt(trainingDuration))
                .build();

        return training;
    }
}
