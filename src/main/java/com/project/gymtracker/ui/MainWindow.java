package com.project.gymtracker.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Gym Tracker");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("New Workout", new NewWorkoutPanel());
        tabbedPane.addTab("Workout History", new WorkoutHistoryPanel());
        tabbedPane.addTab("Progress Charts", new ProgressChartPanel());

        add(tabbedPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}