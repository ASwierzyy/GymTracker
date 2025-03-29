package com.project.gymtracker.entity;

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

    public SetLog(){}

    public SetLog(int reps, double weight, ExerciseLog exerciseLog) {
        this.reps = reps;
        this.weight = weight;
        this.exerciseLog = exerciseLog;
    }

    public Long getId() {
        return id;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ExerciseLog getExerciseLog() {
        return exerciseLog;
    }

    public void setExerciseLog(ExerciseLog exerciseLog) {
        this.exerciseLog = exerciseLog;
    }
}
