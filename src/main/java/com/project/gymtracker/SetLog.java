package com.project.gymtracker;

import jakarta.persistence.*;

@Entity
public class SetLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int reps;
    private double weight;

    @ManyToOne
    @JoinColumn(name = "exercise_log_id")
    private ExerciseLog exerciseLog;


}
