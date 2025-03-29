package com.project.gymtracker.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @OneToMany(mappedBy = "workoutSession", cascade = CascadeType.ALL)
    private List<ExerciseLog> exerciseLogs = new ArrayList<>();
}
