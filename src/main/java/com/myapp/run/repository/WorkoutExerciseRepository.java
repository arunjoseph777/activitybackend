package com.myapp.run.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.run.entity.WorkoutExercise;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
	List<WorkoutExercise> findByWorkoutId(Long workoutId);

    List<WorkoutExercise> findByExerciseDetailsId(Long exerciseDetailsId);
}
