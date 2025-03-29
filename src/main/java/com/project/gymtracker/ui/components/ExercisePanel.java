package com.project.gymtracker.ui.components;

import com.project.gymtracker.entity.*;
import com.project.gymtracker.service.ExerciseService;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ExercisePanel extends JPanel {

    private final JComboBox<String> exerciseComboBox;
    private final JPanel setsContainer;
    private final List<SetRow> sets = new ArrayList<>();
    private final Runnable removeExerciseCallback;

    public ExercisePanel(ExerciseService exerciseService, Consumer<ExercisePanel> removeExerciseCallback) {
        this.removeExerciseCallback = () -> removeExerciseCallback.accept(this);

        setLayout(new BorderLayout());
        exerciseComboBox = new JComboBox<>();
        exerciseService.getAllExercises().forEach(e -> exerciseComboBox.addItem(e.getName()));

        JButton removeExerciseBtn = new JButton("- Remove Exercise");
        removeExerciseBtn.addActionListener(e -> this.removeExerciseCallback.run());

        JPanel top = new JPanel(new FlowLayout());
        top.add(new JLabel("Exercise:"));
        top.add(exerciseComboBox);
        top.add(removeExerciseBtn);

        JButton addSetBtn = new JButton("+ Add Set");
        addSetBtn.addActionListener(e -> addSet());

        setsContainer = new JPanel();
        setsContainer.setLayout(new BoxLayout(setsContainer, BoxLayout.Y_AXIS));

        JPanel bottom = new JPanel(new FlowLayout());
        bottom.add(addSetBtn);

        add(top, BorderLayout.NORTH);
        add(setsContainer, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        addSet();
    }

    private void addSet() {
        SetRow setRow = new SetRow(this::removeSet);
        sets.add(setRow);
        setsContainer.add(setRow);
        refreshUI();
    }

    private void removeSet(SetRow setRow) {
        sets.remove(setRow);
        setsContainer.remove(setRow);
        refreshUI();
    }

    public ExerciseLog toExerciseLog(WorkoutSession session, ExerciseService exerciseService) {
        if (sets.isEmpty()) return null;

        String selectedExerciseName = (String) exerciseComboBox.getSelectedItem();
        Exercise exercise = exerciseService.findByName(selectedExerciseName);
        if (exercise == null) {
            JOptionPane.showMessageDialog(this, "Selected exercise not found!");
            return null;
        }

        ExerciseLog exerciseLog = new ExerciseLog();
        exerciseLog.setExercise(exercise);
        exerciseLog.setWorkoutSession(session);

        for (SetRow set : sets) {
            SetLog setLog = set.toSetLog(exerciseLog);
            if (setLog != null) {
                exerciseLog.getSets().add(setLog);
            }
        }

        return exerciseLog.getSets().isEmpty() ? null : exerciseLog;
    }

    private void refreshUI() {
        revalidate();
        repaint();
    }
}

class SetRow extends JPanel {

    private final JTextField repsField = new JTextField(4);
    private final JTextField weightField = new JTextField(4);
    private final Runnable removeCallback;

    public SetRow(Consumer<SetRow> removeCallback) {
        this.removeCallback = () -> removeCallback.accept(this);

        setLayout(new FlowLayout());
        add(new JLabel("Reps:"));
        add(repsField);
        add(new JLabel("Weight (kg):"));
        add(weightField);

        JButton removeBtn = new JButton("- Remove Set");
        removeBtn.addActionListener(e -> this.removeCallback.run());
        add(removeBtn);
    }

    public SetLog toSetLog(ExerciseLog exerciseLog) {
        try {
            int reps = Integer.parseInt(repsField.getText());
            double weight = Double.parseDouble(weightField.getText());
            SetLog setLog = new SetLog();
            setLog.setReps(reps);
            setLog.setWeight(weight);
            setLog.setExerciseLog(exerciseLog);
            return setLog;
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}