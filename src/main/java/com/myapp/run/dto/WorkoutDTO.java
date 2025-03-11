package com.myapp.run.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkoutDTO {

    private Long id;
    private String name;
    private LocalDateTime date;
    private Long userId;
    private List<WorkoutExerciseDTO> exercises;
}
