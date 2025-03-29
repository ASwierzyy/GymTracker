package com.project.gymtracker.repository;

import com.project.gymtracker.entity.SetLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SetLogRepository extends JpaRepository<SetLog, Long> {
    List<SetLog> findByExerciseLogId(Long exerciseLogId);
}
