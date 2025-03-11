package com.myapp.run.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.run.entity.ExerciseDetails;

@Repository
public interface ExerciseDetailsRepository extends JpaRepository<ExerciseDetails, Long> {
	List<ExerciseDetails> findByNameContainingIgnoreCase(String name);
}
