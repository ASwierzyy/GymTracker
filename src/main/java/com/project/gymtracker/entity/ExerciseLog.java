package com.project.gymtracker.entity;

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

    @OneToMany(mappedBy = "exerciseLog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SetLog> sets = new ArrayList<>();

    public ExerciseLog(){}

    public ExerciseLog(WorkoutSession workoutSession, Exercise exercise, List<SetLog> sets) {
        this.workoutSession = workoutSession;
        this.exercise = exercise;
        this.sets = sets;
    }

    public Long getId() {
        return id;
    }

    public WorkoutSession getWorkoutSession() {
        return workoutSession;
    }

    public void setWorkoutSession(WorkoutSession workoutSession) {
        this.workoutSession = workoutSession;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public List<SetLog> getSets() {
        return sets;
    }

    public void setSets(List<SetLog> sets) {
        this.sets = sets;
    }
}
