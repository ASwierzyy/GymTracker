package com.project.gymtracker.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static javax.swing.text.StyleConstants.setBackground;

public class WorkoutSessionListItem extends JPanel{

    private JLabel dateLabel;
    private JLabel nameLabel;

    public WorkoutSessionListItem(LocalDate date, String sessionName) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        dateLabel = new JLabel("📅 " + date.toString());
        dateLabel.setPreferredSize(new Dimension(120, 20));

        nameLabel = new JLabel(sessionName);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        add(dateLabel, BorderLayout.WEST);
        add(nameLabel, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(WorkoutSessionListItem.this,
                        "Details for session: " + sessionName + " on " + date);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(230, 230, 250));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(UIManager.getColor("Panel.background"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
}
