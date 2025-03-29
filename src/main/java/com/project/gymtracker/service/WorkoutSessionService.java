package com.project.gymtracker.service;

import com.project.gymtracker.entity.WorkoutSession;
import com.project.gymtracker.repository.WorkoutSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
}