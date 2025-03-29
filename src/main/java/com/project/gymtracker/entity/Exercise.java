package com.project.gymtracker.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<ExerciseLog> exerciseLogs = new ArrayList<>();

    public Exercise(){}

    public Exercise(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Exercise(String name) {
        this.name = name;
    }

    public Exercise(String name, Category category, List<ExerciseLog> exerciseLogs) {
        this.name = name;
        this.category = category;
        this.exerciseLogs = exerciseLogs;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ExerciseLog> getExerciseLogs() {
        return exerciseLogs;
    }

    public void setExerciseLogs(List<ExerciseLog> exerciseLogs) {
        this.exerciseLogs = exerciseLogs;
    }
}
