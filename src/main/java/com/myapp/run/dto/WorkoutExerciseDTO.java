package com.myapp.run.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkoutExerciseDTO {

    private Long id;
    private Long workoutId;
    private Long exerciseDetailsId;
    private String exerciseDetailsName;
    private List<SetDTO> sets;
}
