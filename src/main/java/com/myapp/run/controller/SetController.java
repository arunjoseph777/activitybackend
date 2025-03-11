package com.myapp.run.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.run.dto.ResponseAPI;
import com.myapp.run.entity.Set;
import com.myapp.run.service.SetService;

@RestController
@RequestMapping("/api/sets")
public class SetController {

    @Autowired
    private SetService setService;

    @PostMapping
    public ResponseAPI createSet(@RequestBody Set set) {
        return setService.createSet(set);
    }

    @GetMapping("/{id}")
    public ResponseAPI getSet(@PathVariable Long id) {
        return setService.getSet(id);
    }

    @PutMapping("/{id}")
    public ResponseAPI updateSet(@PathVariable Long id, @RequestBody Set set) {
        return setService.updateSet(id, set);
    }

    @DeleteMapping("/{id}")
    public ResponseAPI deleteSet(@PathVariable Long id) {
        return setService.deleteSet(id);
    }

    @GetMapping
    public ResponseAPI getAllSets() {
        return setService.getAllSets();
    }


    @GetMapping("/workout-exercise/{workoutExerciseId}")
    public ResponseAPI getSetsByWorkoutExerciseId(@PathVariable Long workoutExerciseId) {
        return setService.getSetsByWorkoutExerciseId(workoutExerciseId);
    }
}
