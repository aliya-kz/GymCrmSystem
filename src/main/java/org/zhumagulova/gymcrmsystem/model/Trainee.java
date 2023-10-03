package org.zhumagulova.gymcrmsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Trainee {
    private long id;
    private LocalDate dateOfBirth;
    private String address;
    private List<Trainer> trainerList;
    private User user;

    public Trainee(long id) {
        this.id = id;
    }
}
