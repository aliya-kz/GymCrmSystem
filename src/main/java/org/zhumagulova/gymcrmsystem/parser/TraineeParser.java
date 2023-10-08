package org.zhumagulova.gymcrmsystem.parser;

import org.springframework.stereotype.Component;
import org.zhumagulova.gymcrmsystem.model.Trainee;
import org.zhumagulova.gymcrmsystem.model.Trainer;
import org.zhumagulova.gymcrmsystem.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TraineeParser implements Parser {
    private final static String PROPERTIES_DELIMETER = ",";
    private final static String INTERNAL_DELIMETER = "/";

    private final static int ID_INDEX = 0;
    private final static int DATE_OF_BIRTH_INDEX = 1;
    private final static int ADDRESS_INDEX = 2;
    private final static int USER_ID_INDEX = 3;
    private final static int TRAINERS_INDEX = 4;

    public Trainee parse(String entityData) {
        List<String> properties = Stream.of(entityData.split(PROPERTIES_DELIMETER))
                .map(String::trim)
                .collect(Collectors.toList());

        String id = properties.get(ID_INDEX);
        String dateOfBirth = properties.get(DATE_OF_BIRTH_INDEX);
        String address = properties.get(ADDRESS_INDEX);
        String userId = properties.get(USER_ID_INDEX);
        String trainers = properties.get(TRAINERS_INDEX);
        List<Trainer> traineeList = Stream.of(trainers.split(INTERNAL_DELIMETER))
                .map(String::trim)
                .map(s -> new Trainer(Long.parseLong(s)))
                .collect(Collectors.toList());

        Trainee trainee = Trainee.builder().user(new User(Long.parseLong(userId)))
                .id(Long.parseLong(id))
                .dateOfBirth(LocalDate.parse(dateOfBirth))
                .address(address)
                .trainerList(traineeList)
                .build();

        return trainee;
    }
}
