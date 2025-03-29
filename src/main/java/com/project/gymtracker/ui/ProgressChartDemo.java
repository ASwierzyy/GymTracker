package com.project.gymtracker.ui;

import com.project.gymtracker.entity.Exercise;
import com.project.gymtracker.entity.ExerciseLog;
import com.project.gymtracker.entity.SetLog;
import com.project.gymtracker.entity.WorkoutSession;
import com.project.gymtracker.service.ExerciseService;
import com.project.gymtracker.service.WorkoutSessionService;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class ProgressChartDemo {

    public static void main(String[] args) {
        WorkoutSessionService workoutSessionService = new WorkoutSessionService(null) {
            @Override
            public List<WorkoutSession> findAllByExerciseName(String exerciseName) {
                return List.of(
                        createSession("Bench Press", LocalDate.of(2025, 3, 22), new double[]{70, 75}, new int[]{8, 6}),
                        createSession("Bench Press", LocalDate.of(2025, 3, 23), new double[]{80, 85}, new int[]{8, 6}),
                        createSession("Bench Press", LocalDate.of(2025, 3, 24), new double[]{90, 95}, new int[]{8, 6}),
                        createSession("Bench Press", LocalDate.of(2025, 3, 25), new double[]{60, 65}, new int[]{8, 6})
                );
            }

        };

        ExerciseService exerciseService = new ExerciseService(null) {
            @Override
            public List<Exercise> getAllExercises() {
                Exercise benchPress = new Exercise();
                benchPress.setName("Bench Press");
                return List.of(benchPress);
            }

        };

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Progress Chart Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new ProgressChartPanel(workoutSessionService, exerciseService));
            frame.setVisible(true);
        });
    }

    private static WorkoutSession createSession(String exerciseName, LocalDate date, double[] weights, int[] reps) {
        WorkoutSession session = new WorkoutSession();
        session.setDate(date);

        Exercise exercise = new Exercise();
        exercise.setName(exerciseName);

        ExerciseLog log = new ExerciseLog();
        log.setExercise(exercise);

        for (int i = 0; i < weights.length; i++) {
            SetLog set = new SetLog();
            set.setWeight(weights[i]);
            set.setReps(reps[i]);
            log.getSets().add(set);
        }

        session.setExerciseLogs(List.of(log));
        return session;
    }
}