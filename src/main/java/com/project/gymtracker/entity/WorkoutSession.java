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
    private String name;

    @OneToMany(mappedBy = "workoutSession", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ExerciseLog> exerciseLogs = new ArrayList<>();

    public WorkoutSession(){}

    public WorkoutSession(LocalDate date, String name, List<ExerciseLog> exerciseLogs) {
        this.date = date;
        this.name = name;
        this.exerciseLogs = exerciseLogs;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExerciseLog> getExerciseLogs() {
        return exerciseLogs;
    }

    public void setExerciseLogs(List<ExerciseLog> exerciseLogs) {
        this.exerciseLogs = exerciseLogs;
    }

    @Override
    public String toString() {
        return date + " | " + name;
    }
}
