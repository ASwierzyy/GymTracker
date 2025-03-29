package com.project.gymtracker.ui;

import com.project.gymtracker.entity.ExerciseLog;
import com.project.gymtracker.entity.SetLog;
import com.project.gymtracker.entity.WorkoutSession;
import com.project.gymtracker.service.WorkoutSessionService;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Component
public class WorkoutHistoryPanel extends JPanel {

    private final WorkoutSessionService workoutSessionService;
    private final DefaultListModel<WorkoutSession> sessionListModel;
    private final JList<WorkoutSession> sessionJList;

    public WorkoutHistoryPanel(WorkoutSessionService workoutSessionService) {
        this.workoutSessionService = workoutSessionService;

        setLayout(new BorderLayout());

        sessionListModel = new DefaultListModel<>();
        sessionJList = new JList<>(sessionListModel);
        sessionJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sessionJList.setCellRenderer(new WorkoutSessionListCellRenderer());

        JButton detailsButton = new JButton("View Details");
        detailsButton.addActionListener(e -> viewDetails());

        add(new JScrollPane(sessionJList), BorderLayout.CENTER);
        add(detailsButton, BorderLayout.SOUTH);

        loadSessions();
    }

    private void loadSessions() {
        List<WorkoutSession> sessions = workoutSessionService.getAllSessions();
        sessionListModel.clear();
        sessions.forEach(sessionListModel::addElement);
    }

    private void viewDetails() {
        WorkoutSession selectedSession = sessionJList.getSelectedValue();
        if (selectedSession == null) {
            JOptionPane.showMessageDialog(this, "Please select a workout session first.");
            return;
        }

        StringBuilder details = new StringBuilder();
        details.append("Workout Date: ").append(selectedSession.getDate()).append("\n");
        details.append("Workout Name: ").append(selectedSession.getName()).append("\n\n");

        for (ExerciseLog exerciseLog : selectedSession.getExerciseLogs()) {
            details.append("Exercise: ").append(exerciseLog.getExercise().getName()).append("\n");
            for (SetLog set : exerciseLog.getSets()) {
                details.append(" - Set: ").append(set.getReps()).append(" reps | ")
                        .append(set.getWeight()).append(" kg\n");
            }
            details.append("\n");
        }

        JTextArea detailsArea = new JTextArea(details.toString());
        detailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "Workout Details", JOptionPane.INFORMATION_MESSAGE);
    }

    static class WorkoutSessionListCellRenderer extends JLabel implements ListCellRenderer<WorkoutSession> {

        @Override
        public java.awt.Component getListCellRendererComponent(JList<? extends WorkoutSession> list, WorkoutSession value,
                                                               int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.getDate() + " | " + value.getName());
            setOpaque(true);
            setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
            return this;
        }
    }
}
