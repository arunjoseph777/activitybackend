package com.myapp.run.dto;

import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SetDTO {

    private Long id;
    private int reps;
    private Duration duration;
    private double weight;
    private Long workoutExerciseId;
}
