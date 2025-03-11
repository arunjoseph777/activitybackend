package com.myapp.run.entity;

import java.time.Duration;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sets")
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int reps;
    private Duration duration;
    private double weight;

    @ManyToOne
    @JoinColumn(name = "workout_exercise_id")
    private WorkoutExercise workoutExercise;
}
