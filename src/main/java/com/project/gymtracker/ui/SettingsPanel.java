package com.project.gymtracker.ui;

import com.project.gymtracker.entity.Category;
import com.project.gymtracker.entity.Exercise;
import com.project.gymtracker.service.CategoryService;
import com.project.gymtracker.service.ExerciseService;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Component
public class SettingsPanel extends JPanel {

    private final CategoryService categoryService;
    private final ExerciseService exerciseService;

    private final DefaultListModel<String> categoryListModel = new DefaultListModel<>();
    private final DefaultListModel<String> exerciseListModel = new DefaultListModel<>();

    public SettingsPanel(CategoryService categoryService, ExerciseService exerciseService) {
        this.categoryService = categoryService;
        this.exerciseService = exerciseService;
        initializeUI();
        refreshData();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.add("Categories", createCategoryPanel());
        tabbedPane.add("Exercises", createExercisePanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createCategoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JList<String> categoryList = new JList<>(categoryListModel);
        JScrollPane scrollPane = new JScrollPane(categoryList);

        JTextField newCategoryField = new JTextField();
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        JPanel inputPanel = new JPanel(new GridLayout(1, 3));
        inputPanel.add(newCategoryField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        addButton.addActionListener(e -> {
            String name = newCategoryField.getText().trim();
            if (!name.isEmpty()) {
                categoryService.saveCategory(new Category(name));
                refreshCategories();
                newCategoryField.setText("");
            }
        });

        removeButton.addActionListener(e -> {
            String selected = categoryList.getSelectedValue();
            if (selected != null) {
                Category category = categoryService.findByName(selected);
                if (category != null) {
                    categoryService.deleteCategory(category.getId());
                    refreshCategories();
                }
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createExercisePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JList<String> exerciseList = new JList<>(exerciseListModel);
        JScrollPane scrollPane = new JScrollPane(exerciseList);

        JTextField newExerciseField = new JTextField();
        JComboBox<String> categoryComboBox = new JComboBox<>();
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        JPanel inputPanel = new JPanel(new GridLayout(1, 4));
        inputPanel.add(newExerciseField);
        inputPanel.add(categoryComboBox);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        addButton.addActionListener(e -> {
            String name = newExerciseField.getText().trim();
            String categoryName = (String) categoryComboBox.getSelectedItem();
            if (!name.isEmpty() && categoryName != null) {
                Category category = categoryService.findByName(categoryName);
                if (category != null) {
                    exerciseService.saveExercise(new Exercise(name, category));
                    refreshExercises();
                    newExerciseField.setText("");
                }
            }
        });

        removeButton.addActionListener(e -> {
            String selected = exerciseList.getSelectedValue();
            if (selected != null) {
                Exercise exercise = exerciseService.findByName(selected);
                if (exercise != null) {
                    exerciseService.deleteExercise(exercise.getId());
                    refreshExercises();
                }
            }
        });

        List<Category> categories = categoryService.getAllCategories();
        for (Category c : categories) {
            categoryComboBox.addItem(c.getName());
        }

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void refreshData() {
        refreshCategories();
        refreshExercises();
    }

    private void refreshCategories() {
        categoryListModel.clear();
        List<Category> categories = categoryService.getAllCategories();
        for (Category c : categories) {
            categoryListModel.addElement(c.getName());
        }
    }

    private void refreshExercises() {
        exerciseListModel.clear();
        List<Exercise> exercises = exerciseService.getAllExercises();
        for (Exercise e : exercises) {
            exerciseListModel.addElement(e.getName());
        }
    }
}