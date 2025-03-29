package com.project.gymtracker.ui;

import javax.swing.*;
import java.awt.*;

public class WorkoutHistoryPanel extends JPanel {

    private JTable historyTable;
    private JButton viewDetailsButton;

    public WorkoutHistoryPanel() {
        setLayout(new BorderLayout());

        String[] columns = {"Date", "Workout Name"};
        Object[][] data = {
                {"2025-03-28", "Chest & Triceps"},
                {"2025-03-26", "Legs"},
                {"2025-03-24", "Back & Biceps"}
        };

        historyTable = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(historyTable);
        add(scrollPane, BorderLayout.CENTER);

        viewDetailsButton = new JButton("View Details");
        viewDetailsButton.addActionListener(e -> {
            int selectedRow = historyTable.getSelectedRow();
            if (selectedRow != -1) {
                String workoutDate = historyTable.getValueAt(selectedRow, 0).toString();
                JOptionPane.showMessageDialog(this, "Viewing details for workout on " + workoutDate);
                // Here open detailed panel or dialog
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(viewDetailsButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
