package com.project.gymtracker;

import com.project.gymtracker.service.ExerciseService;
import com.project.gymtracker.service.WorkoutSessionService;
import com.project.gymtracker.ui.MainWindow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class GymTrackerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(GymTrackerApplication.class)
                        .headless(false)
                        .run(args);

        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = context.getBean(MainWindow.class);
            mainWindow.setVisible(true);
        });
    }

}
