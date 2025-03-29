package com.project.gymtracker.repository;

import com.project.gymtracker.entity.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession,Long> {
    List<WorkoutSession> findByDateBetween(LocalDate start, LocalDate end);
    List<WorkoutSession> findAllByOrderByDateAsc();
    List<WorkoutSession> findAllByOrderByDateDesc();
}
