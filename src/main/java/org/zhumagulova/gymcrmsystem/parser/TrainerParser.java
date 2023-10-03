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
public class TrainerParser {
    private final static String PROPERTIES_DELIMETER = ",";
    private final static String INTERNAL_DELIMETER = "/";


    public Trainer toTrainer(String entityData) {
        List<String> properties = Stream.of(entityData.split(PROPERTIES_DELIMETER))
                .map(String::trim)
                .collect(Collectors.toList());

        String specialization = properties.get(0);
        String trainingType = properties.get(1);
        String userId = properties.get(2);
        String trainees = properties.get(3);
        List<Trainee> traineeList = Stream.of(trainees.split(INTERNAL_DELIMETER))
                .map(String::trim)
                .map(s -> new Trainee(Long.parseLong(s)))
                .collect(Collectors.toList());

        Trainer trainer = Trainer.builder().user(new User(Long.parseLong(userId)))
                .specialization(specialization)
                .trainingType(new TrainingType(trainingType))
                .traineeList(traineeList)
                .build();

        return trainer;
    }
}
