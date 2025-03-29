package com.project.gymtracker.service;

import com.project.gymtracker.entity.ExerciseLog;
import com.project.gymtracker.repository.ExerciseLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseLogService {

    private final ExerciseLogRepository exerciseLogRepository;

    public ExerciseLogService(ExerciseLogRepository exerciseLogRepository) {
        this.exerciseLogRepository = exerciseLogRepository;
    }

    public List<ExerciseLog> getAllExerciseLogs() {
        return exerciseLogRepository.findAll();
    }

    public Optional<ExerciseLog> getExerciseLogById(Long id) {
        return exerciseLogRepository.findById(id);
    }

    @Transactional
    public ExerciseLog saveExerciseLog(ExerciseLog exerciseLog) {
        return exerciseLogRepository.save(exerciseLog);
    }

    @Transactional
    public void deleteExerciseLog(Long id) {
        exerciseLogRepository.deleteById(id);
    }

    public List<ExerciseLog> getLogsByWorkoutSession(Long sessionId) {
        return exerciseLogRepository.findByWorkoutSessionId(sessionId);
    }

    public List<ExerciseLog> getLogsByExercise(Long exerciseId) {
        return exerciseLogRepository.findByExerciseId(exerciseId);
    }
}