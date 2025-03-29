package com.project.gymtracker.ui;

import com.project.gymtracker.ui.components.ExercisePanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class NewWorkoutPanel extends JPanel {
    private JPanel exercisesContainer;
    private JButton addExerciseButton;
    private JButton saveSessionButton;

    public NewWorkoutPanel() {
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(new JLabel("New Workout: " + LocalDate.now()));

        saveSessionButton = new JButton("Save Session");
        headerPanel.add(saveSessionButton);

        add(headerPanel, BorderLayout.NORTH);

        exercisesContainer = new JPanel();
        exercisesContainer.setLayout(new BoxLayout(exercisesContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(exercisesContainer);
        add(scrollPane, BorderLayout.CENTER);

        addExerciseButton = new JButton("+ Add Another Exercise");
        addExerciseButton.addActionListener(e -> addExercisePanel());

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.add(addExerciseButton);
        add(footerPanel, BorderLayout.SOUTH);

        addExercisePanel();
    }

    private void addExercisePanel() {
        ExercisePanel exercisePanel = new ExercisePanel();
        exercisesContainer.add(exercisePanel);
        exercisesContainer.revalidate();
        exercisesContainer.repaint();
    }
}
