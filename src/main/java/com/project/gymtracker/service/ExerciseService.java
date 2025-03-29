package com.project.gymtracker.service;

import com.project.gymtracker.entity.Exercise;
import com.project.gymtracker.repository.ExerciseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findById(id);
    }

    @Transactional
    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Transactional
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    public List<Exercise> getExercisesByCategory(Long categoryId) {
        return exerciseRepository.findByCategoryId(categoryId);
    }
}
