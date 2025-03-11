package com.myapp.run.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.run.dto.ResponseAPI;
import com.myapp.run.entity.ExerciseDetails;
import com.myapp.run.service.ExerciseDetailsService;

@RestController
@RequestMapping("/api/exercise-details")
public class ExerciseDetailsController {

    @Autowired
    private ExerciseDetailsService exerciseDetailsService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseAPI> createExerciseDetails(@RequestBody ExerciseDetails exerciseDetails) {
        ResponseAPI responseAPI = exerciseDetailsService.createExerciseDetails(exerciseDetails);
        return new ResponseEntity<>(responseAPI, HttpStatus.valueOf(responseAPI.getStatusCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI> getExerciseDetails(@PathVariable Long id) {
        ResponseAPI responseAPI = exerciseDetailsService.getExerciseDetails(id);
        return new ResponseEntity<>(responseAPI, HttpStatus.valueOf(responseAPI.getStatusCode()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseAPI> updateExerciseDetails(@PathVariable Long id, @RequestBody ExerciseDetails updatedExerciseDetails) {
        ResponseAPI responseAPI = exerciseDetailsService.updateExerciseDetails(id, updatedExerciseDetails);
        return new ResponseEntity<>(responseAPI, HttpStatus.valueOf(responseAPI.getStatusCode()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseAPI> deleteExerciseDetails(@PathVariable Long id) {
        ResponseAPI responseAPI = exerciseDetailsService.deleteExerciseDetails(id);
        return new ResponseEntity<>(responseAPI, HttpStatus.valueOf(responseAPI.getStatusCode()));
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> getAllExerciseDetails() {
        ResponseAPI responseAPI = exerciseDetailsService.getAllExerciseDetails();
        return new ResponseEntity<>(responseAPI, HttpStatus.valueOf(responseAPI.getStatusCode()));
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseAPI> searchExercises(@RequestParam String query) {
        ResponseAPI responseAPI = exerciseDetailsService.searchExercises(query);
        return new ResponseEntity<>(responseAPI, HttpStatus.valueOf(responseAPI.getStatusCode()));
    }

}
