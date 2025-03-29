package com.project.gymtracker.service;
import com.project.gymtracker.entity.SetLog;
import com.project.gymtracker.entity.ExerciseLog;
import com.project.gymtracker.entity.WorkoutSession;
import com.project.gymtracker.repository.WorkoutSessionRepository;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutSessionService {

    private final WorkoutSessionRepository workoutSessionRepository;

    public WorkoutSessionService(WorkoutSessionRepository workoutSessionRepository) {
        this.workoutSessionRepository = workoutSessionRepository;
    }

    public List<WorkoutSession> getAllSessions() {
        return workoutSessionRepository.findAll();
    }

    public Optional<WorkoutSession> getSessionById(Long id) {
        return workoutSessionRepository.findById(id);
    }

    @Transactional
    public WorkoutSession saveSession(WorkoutSession session) {
        return workoutSessionRepository.save(session);
    }

    @Transactional
    public void deleteSession(Long id) {
        workoutSessionRepository.deleteById(id);
    }

    public List<WorkoutSession> getSessionsByDateRange(LocalDate start, LocalDate end) {
        return workoutSessionRepository.findByDateBetween(start, end);
    }

    public List<String> getWorkoutSummaryList() {
        return workoutSessionRepository.findAllByOrderByDateDesc()
                .stream()
                .map(session -> session.getDate() + " | " + session.getName())
                .collect(Collectors.toList());
    }


    public List<WorkoutSession> findAllByExerciseName(String exerciseName) {
        return workoutSessionRepository.findAll().stream()
                .filter(session -> session.getExerciseLogs().stream()
                        .anyMatch(log -> log.getExercise().getName().equalsIgnoreCase(exerciseName)))
                .sorted(Comparator.comparing(WorkoutSession::getDate))
                .collect(Collectors.toList());
    }

    public DefaultCategoryDataset getExerciseProgress(String exerciseName) {
        List<WorkoutSession> sessions = workoutSessionRepository.findAllByOrderByDateAsc();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (WorkoutSession session : sessions) {
            for (ExerciseLog exerciseLog : session.getExerciseLogs()) {
                if (exerciseLog.getExercise().getName().equals(exerciseName)) {
                    double maxWeight = exerciseLog.getSets().stream()
                            .mapToDouble(SetLog::getWeight)
                            .max()
                            .orElse(0);
                    dataset.addValue(maxWeight, exerciseName, session.getDate().toString());
                }
            }
        }

        return dataset;
    }
}