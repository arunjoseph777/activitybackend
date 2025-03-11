package com.myapp.run.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "workouts")
public class Workout {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 private String name;
	 private LocalDateTime date;


	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "user_id")
	 private User user;

	 @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<WorkoutExercise> exercises;
}
