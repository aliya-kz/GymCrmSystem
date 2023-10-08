package org.zhumagulova.gymcrmsystem.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParserFactory {
    private final TrainerParser trainerParser;
    private final TraineeParser traineeParser;
    private final TrainingParser trainingParser;

    private final static String TRAINER = "Trainer";
    private final static String TRAINEE = "Trainee";
    private final static String TRAINING = "Training";
    @Autowired
    public ParserFactory(
            TrainerParser trainerParser,
            TraineeParser traineeParser,
            TrainingParser trainingParser
    ) {
        this.trainerParser = trainerParser;
        this.traineeParser = traineeParser;
        this.trainingParser = trainingParser;
    }

    public Parser getParser(String entityType) {
        switch (entityType) {
            case TRAINER:
                return trainerParser;
            case TRAINEE:
                return traineeParser;
            case TRAINING:
                return trainingParser;
            default:
                throw new IllegalArgumentException("Unsupported entity type: " + entityType);
        }
    }
}