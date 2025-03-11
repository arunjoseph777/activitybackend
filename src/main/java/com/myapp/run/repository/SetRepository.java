package com.myapp.run.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.run.entity.Set;
import com.myapp.run.entity.WorkoutExercise;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {
    List<Set> findByWorkoutExercise(WorkoutExercise workoutExercise);
}
