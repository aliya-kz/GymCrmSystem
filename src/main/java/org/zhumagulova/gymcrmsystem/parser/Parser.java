package org.zhumagulova.gymcrmsystem.parser;

import org.zhumagulova.gymcrmsystem.model.TrainingEntity;

public interface Parser  {
    TrainingEntity parse(String entityData);
}
