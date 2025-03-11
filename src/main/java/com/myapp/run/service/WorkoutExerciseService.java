package com.myapp.run.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.run.dto.ResponseAPI;
import com.myapp.run.entity.ExerciseDetails;
import com.myapp.run.entity.Workout;
import com.myapp.run.entity.WorkoutExercise;
import com.myapp.run.exception.OurException;
import com.myapp.run.repository.ExerciseDetailsRepository;
import com.myapp.run.repository.WorkoutExerciseRepository;
import com.myapp.run.repository.WorkoutRepository;
import com.myapp.run.util.DtoConverter;

@Service
public class WorkoutExerciseService {

    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private ExerciseDetailsRepository exerciseDetailsRepository;

    public ResponseAPI createWorkoutExercise(WorkoutExercise workoutExercise) {
        ResponseAPI responseAPI = new ResponseAPI();

        try {
            Workout workout = workoutRepository.findById(workoutExercise.getWorkout().getId())
                    .orElseThrow(() -> new OurException("Workout not found with id: " + workoutExercise.getWorkout().getId()));

            ExerciseDetails exerciseDetails = exerciseDetailsRepository.findById(workoutExercise.getExerciseDetails().getId())
                    .orElseThrow(() -> new OurException("ExerciseDetails not found with id: " + workoutExercise.getExerciseDetails().getId()));

            workoutExercise.setWorkout(workout);
            workoutExercise.setExerciseDetails(exerciseDetails);

            WorkoutExercise savedWorkoutExercise = workoutExerciseRepository.save(workoutExercise);
            responseAPI.setStatusCode(201);
            responseAPI.setMessage("WorkoutExercise created successfully");
            responseAPI.setWorkoutExercise(DtoConverter.toWorkoutExerciseDTO(savedWorkoutExercise));
        } catch (Exception e) {
            responseAPI.setStatusCode(500);
            responseAPI.setMessage("Error creating WorkoutExercise: " + e.getMessage());
        }

        return responseAPI;
    }

    public ResponseAPI getWorkoutExercise(Long id) {
        ResponseAPI responseAPI = new ResponseAPI();

        try {
            WorkoutExercise workoutExercise = workoutExerciseRepository.findById(id)
                    .orElseThrow(() -> new OurException("WorkoutExercise not found with id: " + id));
            responseAPI.setStatusCode(200);
            responseAPI.setMessage("WorkoutExercise retrieved successfully");
            responseAPI.setWorkoutExercise(DtoConverter.toWorkoutExerciseDTO(workoutExercise));
        } catch (OurException e) {
            responseAPI.setStatusCode(404);
            responseAPI.setMessage(e.getMessage());
        } catch (Exception e) {
            responseAPI.setStatusCode(500);
            responseAPI.setMessage("Error retrieving WorkoutExercise: " + e.getMessage());
        }

        return responseAPI;
    }

    public ResponseAPI updateWorkoutExercise(Long id, WorkoutExercise updatedWorkoutExercise) {
        ResponseAPI responseAPI = new ResponseAPI();

        try {
            WorkoutExercise workoutExercise = workoutExerciseRepository.findById(id)
                    .orElseThrow(() -> new OurException("WorkoutExercise not found with id: " + id));

            Workout workout = workoutRepository.findById(updatedWorkoutExercise.getWorkout().getId())
                    .orElseThrow(() -> new OurException("Workout not found with id: " + updatedWorkoutExercise.getWorkout().getId()));

            ExerciseDetails exerciseDetails = exerciseDetailsRepository.findById(updatedWorkoutExercise.getExerciseDetails().getId())
                    .orElseThrow(() -> new OurException("ExerciseDetails not found with id: " + updatedWorkoutExercise.getExerciseDetails().getId()));

            workoutExercise.setWorkout(workout);
            workoutExercise.setExerciseDetails(exerciseDetails);
            workoutExercise.setSets(updatedWorkoutExercise.getSets());

            WorkoutExercise savedWorkoutExercise = workoutExerciseRepository.save(workoutExercise);
            responseAPI.setStatusCode(200);
            responseAPI.setMessage("WorkoutExercise updated successfully");
            responseAPI.setWorkoutExercise(DtoConverter.toWorkoutExerciseDTO(savedWorkoutExercise));
        } catch (OurException e) {
            responseAPI.setStatusCode(404);
            responseAPI.setMessage(e.getMessage());
        } catch (Exception e) {
            responseAPI.setStatusCode(500);
            responseAPI.setMessage("Error updating WorkoutExercise: " + e.getMessage());
        }

        return responseAPI;
    }

    public ResponseAPI deleteWorkoutExercise(Long id) {
        ResponseAPI responseAPI = new ResponseAPI();

        try {
            WorkoutExercise workoutExercise = workoutExerciseRepository.findById(id)
                    .orElseThrow(() -> new OurException("WorkoutExercise not found with id: " + id));
            workoutExerciseRepository.delete(workoutExercise);
            responseAPI.setStatusCode(200);
            responseAPI.setMessage("WorkoutExercise deleted successfully");
        } catch (OurException e) {
            responseAPI.setStatusCode(404);
            responseAPI.setMessage(e.getMessage());
        } catch (Exception e) {
            responseAPI.setStatusCode(500);
            responseAPI.setMessage("Error deleting WorkoutExercise: " + e.getMessage());
        }

        return responseAPI;
    }

    public ResponseAPI getAllWorkoutExercises() {
        ResponseAPI responseAPI = new ResponseAPI();

        try {
            List<WorkoutExercise> workoutExerciseList = workoutExerciseRepository.findAll();
            responseAPI.setStatusCode(200);
            responseAPI.setMessage("WorkoutExercises retrieved successfully");
            responseAPI.setWorkoutExerciseList(DtoConverter.toWorkoutExerciseDTOList(workoutExerciseList));
        } catch (Exception e) {
            responseAPI.setStatusCode(500);
            responseAPI.setMessage("Error retrieving WorkoutExercises: " + e.getMessage());
        }

        return responseAPI;
    }
}
