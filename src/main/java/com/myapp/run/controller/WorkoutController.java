package com.myapp.run.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.run.dto.ResponseAPI;
import com.myapp.run.dto.WorkoutDTO;
import com.myapp.run.service.WorkoutService;

@RestController
@RequestMapping("/api/workouts")
//@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping
    public ResponseEntity<ResponseAPI> createWorkout(@RequestBody WorkoutDTO workoutDTO) {
        ResponseAPI responseAPI = workoutService.createWorkout(workoutDTO);
        return new ResponseEntity<>(responseAPI, HttpStatus.valueOf(responseAPI.getStatusCode()));
    }

    @GetMapping("/{workoutId}")
    public ResponseEntity<ResponseAPI> getWorkout(@PathVariable Long workoutId) {
        ResponseAPI responseAPI = workoutService.getWorkout(workoutId);
        return new ResponseEntity<>(responseAPI, HttpStatus.valueOf(responseAPI.getStatusCode()));
    }

    @PutMapping("/update/{workoutId}")
    public ResponseEntity<ResponseAPI> updateWorkout(
            @PathVariable Long workoutId,
            @RequestBody WorkoutDTO workoutDTO) {

        ResponseAPI responseAPI = workoutService.updateWorkout(workoutId, workoutDTO);
        return new ResponseEntity<>(responseAPI, HttpStatus.valueOf(responseAPI.getStatusCode()));
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<ResponseAPI> deleteWorkout(@PathVariable Long workoutId) {
        ResponseAPI responseAPI = workoutService.deleteWorkout(workoutId);
        return new ResponseEntity<>(responseAPI, HttpStatus.valueOf(responseAPI.getStatusCode()));
    }

    @GetMapping("/user/{userId}/exercise/{exerciseDetailsId}")
    public ResponseEntity<ResponseAPI> getWorkoutsByUserAndExercise(
            @PathVariable Long userId,
            @PathVariable Long exerciseDetailsId) {

        ResponseAPI response = workoutService.getWorkoutsByUserAndExercise(userId, exerciseDetailsId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @GetMapping("/users/{userId}/history-with-names")
    public ResponseEntity<ResponseAPI> getWorkoutHistoryWithNames(@PathVariable Long userId) {
        ResponseAPI response = workoutService.getWorkoutHistoryWithNames(userId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }
}
