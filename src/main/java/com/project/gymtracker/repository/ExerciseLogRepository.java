package com.project.gymtracker.repository;

import com.project.gymtracker.entity.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseLogRepository extends JpaRepository<ExerciseLog,Long> {
    List<ExerciseLog> findByWorkoutSessionId(Long sessionId);
    List<ExerciseLog> findByExerciseId(Long exerciseId);
}
