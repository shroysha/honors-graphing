package dev.shroysha.honors.graphing;

import dev.shroysha.honors.graphing.view.GraphingPanel;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {

        double[] xPoints = {-3, 5, 6, 10};
        double[] yPoints = {-3, 5, 10, 10};

        JFrame frame = new JFrame();
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        GraphingPanel graphingPanel = new GraphingPanel(frame.getWidth(), frame.getHeight());
        frame.add(graphingPanel, BorderLayout.CENTER);

        graphingPanel.setBackgroundColor(Color.red);
        graphingPanel.setDrawColor(Color.blue);
        graphingPanel.setXValues(xPoints);
        graphingPanel.setYValues(yPoints);
        graphingPanel.setXIncrements(10);
        graphingPanel.setVisible(true);

        frame.setVisible(true);
    }

}
