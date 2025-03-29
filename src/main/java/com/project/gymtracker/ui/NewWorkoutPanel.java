package com.project.gymtracker.ui;

import com.project.gymtracker.entity.*;
import com.project.gymtracker.service.ExerciseService;
import com.project.gymtracker.service.WorkoutSessionService;
import com.project.gymtracker.ui.components.ExercisePanel;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewWorkoutPanel extends JPanel {

    private final WorkoutSessionService workoutSessionService;
    private final ExerciseService exerciseService;
    private JPanel exercisesContainer;
    private List<ExercisePanel> exercisePanels = new ArrayList<>();
    private JTextField sessionNameField;

    public NewWorkoutPanel(WorkoutSessionService workoutSessionService, ExerciseService exerciseService) {
        this.workoutSessionService = workoutSessionService;
        this.exerciseService = exerciseService;

        setLayout(new BorderLayout());

        JButton saveBtn = new JButton("Save Session");
        saveBtn.addActionListener(e -> saveWorkout());

        JButton addExerciseBtn = new JButton("+ Add Exercise");
        addExerciseBtn.addActionListener(e -> addExercisePanel());

        sessionNameField = new JTextField(20);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Workout Name:"));
        topPanel.add(sessionNameField);
        topPanel.add(addExerciseBtn);
        topPanel.add(saveBtn);

        add(topPanel, BorderLayout.NORTH);

        exercisesContainer = new JPanel();
        exercisesContainer.setLayout(new BoxLayout(exercisesContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(exercisesContainer);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addExercisePanel() {
        ExercisePanel exercisePanel = new ExercisePanel(exerciseService, this::removeExercisePanel);
        exercisePanels.add(exercisePanel);
        exercisesContainer.add(exercisePanel);
        refreshUI();
    }

    private void removeExercisePanel(ExercisePanel panel) {
        exercisePanels.remove(panel);
        exercisesContainer.remove(panel);
        refreshUI();
    }

    private void saveWorkout() {
        String workoutName = sessionNameField.getText().trim();
        if(workoutName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a workout name!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        WorkoutSession session = new WorkoutSession();
        session.setDate(LocalDate.now());
        session.setName(workoutName);

        for (ExercisePanel exPanel : exercisePanels) {
            ExerciseLog log = exPanel.toExerciseLog(session, exerciseService);
            if (log != null) session.getExerciseLogs().add(log);
        }

        workoutSessionService.saveSession(session);
        JOptionPane.showMessageDialog(this, "Workout saved successfully!");
        exercisesContainer.removeAll();
        exercisePanels.clear();
        sessionNameField.setText("");
        refreshUI();
    }

    private void refreshUI() {
        revalidate();
        repaint();
    }
}
