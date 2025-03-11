package com.myapp.run.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myapp.run.entity.User;
import com.myapp.run.entity.Workout;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUser(User user);

    @Query("SELECT w FROM Workout w JOIN w.exercises e WHERE w.user.id = :userId AND e.exerciseDetails.id = :exerciseDetailsId")
    List<Workout> findByUserIdAndExerciseDetailsId(
            @Param("userId") Long userId,
            @Param("exerciseDetailsId") Long exerciseDetailsId
    );
}
