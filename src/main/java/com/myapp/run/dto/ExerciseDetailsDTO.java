package com.myapp.run.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExerciseDetailsDTO {
    private Long id;
    private String name;
    private String type;
    private String description;
    private int difficulty;
    private List<WorkoutExerciseDTO> workoutExercises;
}
