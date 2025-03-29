package com.project.gymtracker.service;

import com.project.gymtracker.entity.Exercise;
import com.project.gymtracker.repository.ExerciseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public List<String> findAllNames() {
        return exerciseRepository.findAll()
                .stream()
                .map(Exercise::getName)
                .collect(Collectors.toList());
    }

    public Exercise findByName(String name) {
        return exerciseRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Exercise not found: " + name));
    }
}
