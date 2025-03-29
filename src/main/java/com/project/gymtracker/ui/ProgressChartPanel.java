package com.project.gymtracker.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class ProgressChartPanel extends JPanel {

    public ProgressChartPanel() {
        setLayout(new BorderLayout());

        XYSeries series = new XYSeries("Bench Press 1RM");
        series.add(1, 60);
        series.add(2, 65);
        series.add(3, 70);
        series.add(4, 75);
        series.add(5, 80);

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Bench Press Progress", "Workout Session", "Weight (kg)",
                dataset, PlotOrientation.VERTICAL, true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }
}
