package org.zhumagulova.gymcrmsystem.mapper;

import org.springframework.stereotype.Component;
import org.zhumagulova.gymcrmsystem.model.Trainee;
import org.zhumagulova.gymcrmsystem.model.Trainer;
import org.zhumagulova.gymcrmsystem.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TraineeParser {
    private final static String PROPERTIES_DELIMETER = ",";
    private final static String OPENING_BRACKET = "[";
    private final static String CLOSING_BRACKET = "]";

    private final static String INTERNAL_DELIMETER = "/";


    public Trainee toTrainee(String entityData) {
        List<String> properties = Stream.of(entityData.split(PROPERTIES_DELIMETER))
                .map(String::trim)
                .collect(Collectors.toList());

        String dateOfBirth = properties.get(0);
        String address = properties.get(1);
        String userId = properties.get(2);
        String trainers = properties.get(3);
        List<Trainer> traineeList = Stream.of(trainers.split(INTERNAL_DELIMETER))
                .map(String::trim)
                .map(s -> new Trainer(Long.parseLong(s)))
                .collect(Collectors.toList());

        Trainee trainee = Trainee.builder().user(new User(Long.parseLong(userId)))
                .dateOfBirth(LocalDate.parse(dateOfBirth))
                .address(address)
                .trainerList(traineeList)
                .build();

        return trainee;
    }

}
