package org.zhumagulova.gymcrmsystem.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.zhumagulova.gymcrmsystem.model.TrainingEntity;
import org.zhumagulova.gymcrmsystem.parser.Parser;
import org.zhumagulova.gymcrmsystem.parser.ParserFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StorageInitiatizationAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Value("${data.file.path}")
    private String dataFilePath;
    private Map<String, Object> storageMap = new HashMap<>();

    private final static String NAMESPACE_AND_ID_DELIMETER = "_";
    private final static String KEY_AND_OBJECT_DELIMETER = ";";
    private final static String PARAMETERS_DELIMETER = ",";
    private final ParserFactory parserFactory;

    public StorageInitiatizationAnnotationBeanPostProcessor(ParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.getAnnotation(InitializeStorage.class) != null) {
                initializeStorageMap();
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, storageMap);
            }
        }
        return bean;
    }

    public void initializeStorageMap() {
        Path path = Paths.get(dataFilePath);
        try {
            List<String> contents = Files.readAllLines(path);
            for (String line : contents) {
                String[] parts = line.split(KEY_AND_OBJECT_DELIMETER);
                if (parts.length >= 2) {
                    String key = parts[0].trim();
                    String entityType = key.substring(0, key.indexOf(NAMESPACE_AND_ID_DELIMETER));
                    String id = key.substring(key.indexOf(NAMESPACE_AND_ID_DELIMETER) + 1);
                    String entityData = id + PARAMETERS_DELIMETER + parts[1].trim();
                    Parser parser = parserFactory.getParser(entityType);
                    TrainingEntity trainingEntity = parser.parse(entityData);
                    storageMap.put(key, trainingEntity);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

