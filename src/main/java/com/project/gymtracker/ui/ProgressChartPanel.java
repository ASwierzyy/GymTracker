package com.project.gymtracker.ui;

import com.project.gymtracker.entity.WorkoutSession;
import com.project.gymtracker.service.ExerciseService;
import com.project.gymtracker.service.WorkoutSessionService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class ProgressChartPanel extends JPanel {

    private final WorkoutSessionService workoutSessionService;
    private final ExerciseService exerciseService;
    private JComboBox<String> exerciseComboBox;
    ChartPanel chartPanel;

    public ProgressChartPanel(WorkoutSessionService workoutSessionService, ExerciseService exerciseService) {
        this.workoutSessionService = workoutSessionService;
        this.exerciseService = exerciseService;
        setLayout(new BorderLayout());

        exerciseComboBox = new JComboBox<>();
        exerciseService.getAllExercises().forEach(e -> exerciseComboBox.addItem(e.getName()));
        exerciseComboBox.addActionListener(e -> refreshChart());

        add(exerciseComboBox, BorderLayout.NORTH);
        chartPanel = new ChartPanel(null);
        add(chartPanel, BorderLayout.CENTER);

        refreshChart();
    }

    private void refreshChart() {
        String exerciseName = (String) exerciseComboBox.getSelectedItem();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

       var sessions = workoutSessionService.findAllByExerciseName(exerciseName);

        for (WorkoutSession session : sessions) {
            double totalWeight = session.getExerciseLogs().stream()
                    .filter(el -> el.getExercise().getName().equals(exerciseName))
                    .flatMap(el -> el.getSets().stream())
                    .mapToDouble(sl -> sl.getReps() * sl.getWeight())
                    .sum();

            dataset.addValue(totalWeight, exerciseName, session.getDate().toString());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Total Weight Over Time (" + exerciseName + ")",
                "Date",
                "Total Weight (kg)",
                dataset
        );

        chartPanel.setChart(chart);
    }
}