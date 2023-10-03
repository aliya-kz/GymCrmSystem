package org.zhumagulova.gymcrmsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zhumagulova.gymcrmsystem.parser.TraineeParser;
import org.zhumagulova.gymcrmsystem.parser.TrainerParser;
import org.zhumagulova.gymcrmsystem.parser.TrainingParser;
import org.zhumagulova.gymcrmsystem.model.Trainee;
import org.zhumagulova.gymcrmsystem.model.Trainer;
import org.zhumagulova.gymcrmsystem.model.Training;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Storage {
    @Value("${data.file.path}")
    private String dataFilePath;
    private Map<String, Object> storageMap = new HashMap<>();

    private final static String NAMESPACE_AND_ID_DELIMETER = "_";
    private final static String KEY_AND_OBJECT_DELIMETER = ";";

    private final TrainerParser trainerParser;
    private final TraineeParser traineeParser;
    private final TrainingParser trainingParser;

    @Autowired
    public Storage( TrainerParser trainerParser, TraineeParser traineeParser, TrainingParser trainingParser) {
        this.trainerParser = trainerParser;
        this.traineeParser = traineeParser;
        this.trainingParser = trainingParser;
    }


    @PostConstruct
    public void initialize() {
        if (dataFilePath != null) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(dataFilePath));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(KEY_AND_OBJECT_DELIMETER);

                    if (parts.length >= 2) {
                        String key = parts[0].trim();
                        String entityType = key.substring(0, key.indexOf(NAMESPACE_AND_ID_DELIMETER));
                        String id = key.substring(key.indexOf(NAMESPACE_AND_ID_DELIMETER) + 1);
                        String entityData = parts[1].trim();
                        switch (entityType) {
                            case "Trainer":
                                Trainer trainer = trainerParser.toTrainer(entityData);
                                trainer.setId(Long.parseLong(id));
                                storageMap.put(key, trainer);
                                break;
                            case "Trainee":
                                Trainee trainee = traineeParser.toTrainee(entityData);
                                trainee.setId(Long.parseLong(id));
                                storageMap.put(key, trainee);
                                break;
                            case "Training":
                                Training training = trainingParser.toTraining(entityData);
                                training.setId(Long.parseLong(id));
                                storageMap.put(key, training);
                                break;
                            default:
                                throw new Exception("Unsupported key or value");
                        }
                    }
                }
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException("Failed to read data from the file.", e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void put(String key, Object object) {
        storageMap.put(key, object);
    }

    public Object get(String key) throws ClassNotFoundException {
        return storageMap.get(key);
    }

    public void delete(String key) {
        storageMap.remove(key);
    }

}