package com.myapp.run.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

	private Long id;
    private String email;
    private String name;
    private String role;
    private int age;
    private double weight;
    private double height;

    private List<WorkoutDTO> workoutHistory;
}
