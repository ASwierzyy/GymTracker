package com.project.gymtracker.repository;

import com.project.gymtracker.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {
    List<Exercise> findByCategoryId(Long categoryId);
    Optional<Exercise> findByName(String name);
}
