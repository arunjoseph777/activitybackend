package com.myapp.run.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.run.dto.ResponseAPI;
import com.myapp.run.entity.ExerciseDetails;
import com.myapp.run.exception.OurException;
import com.myapp.run.repository.ExerciseDetailsRepository;
import com.myapp.run.util.DtoConverter;

@Service
public class ExerciseDetailsService {

    @Autowired
    private ExerciseDetailsRepository exerciseDetailsRepository;

    public ResponseAPI createExerciseDetails(ExerciseDetails exerciseDetails) {
        ResponseAPI responseAPI = new ResponseAPI();

        try {
            ExerciseDetails savedExerciseDetails = exerciseDetailsRepository.save(exerciseDetails);
            responseAPI.setStatusCode(201);
            responseAPI.setMessage("Exercise created successfully");
            responseAPI.setExerciseDetails(DtoConverter.toExerciseDetailsDTO(savedExerciseDetails));
        } catch (Exception e) {
            responseAPI.setStatusCode(500);
            responseAPI.setMessage("Error creating exercise: " + e.getMessage());
        }

        return responseAPI;
    }

    public ResponseAPI getExerciseDetails(Long id) {
        ResponseAPI responseAPI = new ResponseAPI();

        try {
            ExerciseDetails exerciseDetails = exerciseDetailsRepository.findById(id)
                    .orElseThrow(() -> new OurException("Exercise not found with id: " + id));
            responseAPI.setStatusCode(200);
            responseAPI.setMessage("Exercise retrieved successfully");
            responseAPI.setExerciseDetails(DtoConverter.toExerciseDetailsDTO(exerciseDetails));
        } catch (OurException e) {
            responseAPI.setStatusCode(404);
            responseAPI.setMessage(e.getMessage());
        } catch (Exception e) {
            responseAPI.setStatusCode(500);
            responseAPI.setMessage("Error retrieving exercise: " + e.getMessage());
        }

        return responseAPI;
    }

    public ResponseAPI updateExerciseDetails(Long id, ExerciseDetails updatedExerciseDetails) {
        ResponseAPI responseAPI = new ResponseAPI();

        try {
            ExerciseDetails exerciseDetails = exerciseDetailsRepository.findById(id)
                    .orElseThrow(() -> new OurException("Exercise not found with id: " + id));

            exerciseDetails.setName(updatedExerciseDetails.getName());
            exerciseDetails.setType(updatedExerciseDetails.getType());
            exerciseDetails.setDescription(updatedExerciseDetails.getDescription());
            exerciseDetails.setDifficulty(updatedExerciseDetails.getDifficulty());

            ExerciseDetails savedExerciseDetails = exerciseDetailsRepository.save(exerciseDetails);
            responseAPI.setStatusCode(200);
            responseAPI.setMessage("Exercise updated successfully");
            responseAPI.setExerciseDetails(DtoConverter.toExerciseDetailsDTO(savedExerciseDetails));
        } catch (OurException e) {
            responseAPI.setStatusCode(404);
            responseAPI.setMessage(e.getMessage());
        } catch (Exception e) {
            responseAPI.setStatusCode(500);
            responseAPI.setMessage("Error updating exercise: " + e.getMessage());
        }

        return responseAPI;
    }

    public ResponseAPI deleteExerciseDetails(Long id) {
        ResponseAPI responseAPI = new ResponseAPI();

        try {
            ExerciseDetails exerciseDetails = exerciseDetailsRepository.findById(id)
                    .orElseThrow(() -> new OurException("Exercise not found with id: " + id));
            exerciseDetailsRepository.delete(exerciseDetails);
            responseAPI.setStatusCode(200);
            responseAPI.setMessage("Exercise deleted successfully");
        } catch (OurException e) {
            responseAPI.setStatusCode(404);
            responseAPI.setMessage(e.getMessage());
        } catch (Exception e) {
            responseAPI.setStatusCode(500);
            responseAPI.setMessage("Error deleting exercise: " + e.getMessage());
        }

        return responseAPI;
    }

    public ResponseAPI getAllExerciseDetails() {
        ResponseAPI responseAPI = new ResponseAPI();

        try {
            List<ExerciseDetails> exerciseDetailsList = exerciseDetailsRepository.findAll();
            responseAPI.setStatusCode(200);
            responseAPI.setMessage("Exercises retrieved successfully");
            responseAPI.setExerciseDetailsList(DtoConverter.toExerciseDetailsDTOList(exerciseDetailsList));
        } catch (Exception e) {
            responseAPI.setStatusCode(500);
            responseAPI.setMessage("Error retrieving exercises: " + e.getMessage());
        }

        return responseAPI;
    }

    public ResponseAPI searchExercises(String query){
        ResponseAPI responseAPI = new ResponseAPI();
        try{
            List<ExerciseDetails> exerciseSearchResults = exerciseDetailsRepository.findByNameContainingIgnoreCase(query);
            responseAPI.setStatusCode(200);
            responseAPI.setMessage("Exercises retrieved successfully");
            responseAPI.setExerciseDetailsList(DtoConverter.toExerciseDetailsDTOList(exerciseSearchResults));
        } catch (Exception e) {
            responseAPI.setStatusCode(500);
            responseAPI.setMessage("Error retrieving exercises: " + e.getMessage());
        }
        return responseAPI;
    }
}
