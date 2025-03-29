package com.project.gymtracker.ui.components;

import com.project.gymtracker.entity.WorkoutSession;

import javax.swing.*;
import java.awt.*;

@org.springframework.stereotype.Component
public class WorkoutSessionListItem extends JLabel implements ListCellRenderer<WorkoutSession> {

    @Override
    public Component getListCellRendererComponent(JList<? extends WorkoutSession> list, WorkoutSession value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.getDate() + " | " + value.getName());
        setOpaque(true);
        setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
        return this;
    }
}