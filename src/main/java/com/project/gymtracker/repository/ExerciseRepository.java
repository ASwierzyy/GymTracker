package com.project.gymtracker.repository;

import com.project.gymtracker.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {
    List<Exercise> findByCategoryId(Long categoryId);
}
