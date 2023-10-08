package org.zhumagulova.gymcrmsystem.parser;

import org.springframework.stereotype.Component;
import org.zhumagulova.gymcrmsystem.model.Trainee;
import org.zhumagulova.gymcrmsystem.model.Trainer;
import org.zhumagulova.gymcrmsystem.model.TrainingType;
import org.zhumagulova.gymcrmsystem.model.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TrainerParser implements Parser {
    private final static String PROPERTIES_DELIMETER = ",";
    private final static String INTERNAL_DELIMETER = "/";
    private final static int ID_INDEX = 0;
    private final static int SPECIALIZATION_INDEX = 1;
    private final static int TRAINING_TYPE_INDEX = 2;
    private final static int USER_ID_INDEX = 3;
    private final static int TRAINEES_INDEX = 4;

    public Trainer parse(String entityData) {
        List<String> properties = Stream.of(entityData.split(PROPERTIES_DELIMETER))
                .map(String::trim)
                .collect(Collectors.toList());

        String id = properties.get(ID_INDEX);
        String specialization = properties.get(SPECIALIZATION_INDEX);
        String trainingType = properties.get(TRAINING_TYPE_INDEX);
        String userId = properties.get(USER_ID_INDEX);
        String trainees = properties.get(TRAINEES_INDEX);
        List<Trainee> traineeList = Stream.of(trainees.split(INTERNAL_DELIMETER))
                .map(String::trim)
                .map(s -> new Trainee(Long.parseLong(s)))
                .collect(Collectors.toList());

        Trainer trainer = Trainer.builder().user(new User(Long.parseLong(userId)))
                .id(Long.parseLong(id))
                .specialization(specialization)
                .trainingType(new TrainingType(trainingType))
                .traineeList(traineeList)
                .build();

        return trainer;
    }
}
