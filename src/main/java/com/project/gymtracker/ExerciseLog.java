package com.project.gymtracker;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ExerciseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_session_id")
    private WorkoutSession workoutSession;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @OneToMany(mappedBy = "exerciseLog", cascade = CascadeType.ALL)
    private List<SetLog> sets = new ArrayList<>();
}
