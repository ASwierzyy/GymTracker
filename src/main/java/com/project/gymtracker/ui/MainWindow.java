package com.project.gymtracker.ui;
import com.project.gymtracker.service.CategoryService;
import com.project.gymtracker.service.ExerciseService;
import com.project.gymtracker.service.WorkoutSessionService;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MainWindow extends JFrame {

    public MainWindow(WorkoutSessionService workoutSessionService, ExerciseService exerciseService, CategoryService categoryService) {
        setTitle("Gym Tracker");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("New Workout", new NewWorkoutPanel(workoutSessionService, exerciseService));
        tabs.add("Workout History", new WorkoutHistoryPanel(workoutSessionService));
        tabs.add("Progress Charts", new ProgressChartPanel(workoutSessionService, exerciseService));
        tabs.add("Settings", new SettingsPanel(categoryService, exerciseService));
        add(tabs);
    }
}