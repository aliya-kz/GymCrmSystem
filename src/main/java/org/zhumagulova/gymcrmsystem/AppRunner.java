package org.zhumagulova.gymcrmsystem;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.zhumagulova.gymcrmsystem.config.Config;
import org.zhumagulova.gymcrmsystem.service.TrainerService;


@Component
public class AppRunner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        TrainerService trainerService = context.getBean(TrainerService.class);
        System.out.println("printing trainer: " + trainerService.select(1));
    }
}
