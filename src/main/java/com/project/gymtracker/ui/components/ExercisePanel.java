package com.project.gymtracker.ui.components;

import javax.swing.*;
import java.awt.*;

public class ExercisePanel extends JPanel{

    private JComboBox<String> exerciseCombo;
    private JPanel setsPanel;

    public ExercisePanel() {
        setLayout(new BorderLayout());
        exerciseCombo = new JComboBox<>(new String[]{"Bench Press", "Squats", "Deadlift"});

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(new JLabel("Exercise:"));
        headerPanel.add(exerciseCombo);
        add(headerPanel, BorderLayout.NORTH);

        setsPanel = new JPanel();
        setsPanel.setLayout(new BoxLayout(setsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(setsPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton addSetButton = new JButton("+ Add Set");
        addSetButton.addActionListener(e -> addSetRow());

        add(addSetButton, BorderLayout.SOUTH);

        addSetRow();
    }

    private void addSetRow() {
        JPanel setRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setRow.add(new JLabel("Set #" + (setsPanel.getComponentCount() + 1)));
        setRow.add(new JTextField(5)); // Reps
        setRow.add(new JTextField(5)); // Weight
        JButton removeButton = new JButton("❌");
        removeButton.addActionListener(e -> {
            setsPanel.remove(setRow);
            setsPanel.revalidate();
            setsPanel.repaint();
        });
        setRow.add(removeButton);
        setsPanel.add(setRow);
        setsPanel.revalidate();
        setsPanel.repaint();
    }
}
