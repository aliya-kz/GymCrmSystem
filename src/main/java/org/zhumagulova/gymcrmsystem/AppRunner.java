package org.zhumagulova.gymcrmsystem;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.zhumagulova.gymcrmsystem.config.Config;
import org.zhumagulova.gymcrmsystem.dao.Storage;
import org.zhumagulova.gymcrmsystem.service.TrainerService;

@Component
public class GymFacade {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        TrainerService trainerService = context.getBean(TrainerService.class);
        System.out.println("printing entities: ");
        System.out.println(trainerService.select(1));

    }
}
