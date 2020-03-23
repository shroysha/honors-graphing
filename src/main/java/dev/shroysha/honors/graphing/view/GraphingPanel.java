package dev.shroysha.honors.graphing.view;

import javax.swing.*;
import java.awt.*;

public class GraphingPanel extends JPanel {

    private final int bufferXSpace;
    private final int bufferYSpace;
    private double[] xPoints, yPoints;
    private Double xIncrements = 0.0;
    private boolean axisDrawn = false;
    private Color backgroundColor, drawColor;

    public GraphingPanel(int sizeX, int sizeY) {
        this.setSize(sizeX, sizeY);
        this.setPreferredSize(new Dimension(sizeX, sizeY));

        bufferXSpace = sizeX / 25;
        bufferYSpace = sizeY / 25;
    }

    public void paintComponent(Graphics g) {
        int xAxisLocation, yAxisLocation = 0;
        double lowestXPoint = Double.MAX_VALUE, highestXPoint = Double.MIN_VALUE;
        double lowestYPoint = Double.MAX_VALUE, highestYPoint = Double.MIN_VALUE;
        Graphics2D g2d = (Graphics2D) g;

        if (backgroundColor != null) { // Draws Background
            g.setColor(backgroundColor);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

        try { // Draws axis and points
            if (xPoints != null && yPoints != null && drawColor != null) {
                if (xPoints.length != yPoints.length)
                    throw new Exception("x and y points are different lengths");

                for (int i = 0; i < xPoints.length; i++) { // Finds lowest and highest xs and ys for scaling
                    if (xPoints[i] < lowestXPoint)
                        lowestXPoint = xPoints[i];

                    if (xPoints[i] > highestXPoint)
                        highestXPoint = xPoints[i];

                    if (yPoints[i] < lowestYPoint)
                        lowestYPoint = yPoints[i];

                    if (yPoints[i] > highestYPoint)
                        highestYPoint = yPoints[i];
                }

                double xPixelLength = (this.getWidth() - bufferXSpace) / (highestXPoint - lowestXPoint);
                double yPixelLength = (this.getHeight() - bufferYSpace) / (highestYPoint - lowestYPoint);
                xAxisLocation = (int) ((0 - lowestXPoint) * xPixelLength);
                yAxisLocation = (int) ((0 - lowestYPoint) * yPixelLength);

                g2d.setStroke(new BasicStroke(5));  // Draws Axises

                g.setColor(drawColor);
                g2d.drawLine(xAxisLocation, bufferXSpace, xAxisLocation, this.getHeight() - bufferXSpace);
                g2d.drawLine(bufferYSpace, this.getHeight() - yAxisLocation, this.getWidth() - bufferYSpace, this.getHeight() - yAxisLocation);

                axisDrawn = true;
                // Draws Points
                for (int i = 0; i < xPoints.length; i++) {
                    this.drawPoint(g, xPoints[i], yPoints[i], xPixelLength, yPixelLength, xAxisLocation, yAxisLocation);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            axisDrawn = false;
        }

        if (xIncrements != null && axisDrawn) {
            int numberOfIncrements = (int) ((highestXPoint - lowestXPoint) / xIncrements);

            for (int i = 0; i < numberOfIncrements; i++) {
                g2d.setStroke(new BasicStroke(2));
                g.drawLine(5, this.getHeight() - yAxisLocation - 3, 10, this.getHeight() - yAxisLocation + 3);
            }
        }

    }

    public void drawPoint(Graphics g, double x, double y, double xPixelLength, double yPixelLength,
                          int xAxisLocation, int yAxisLocation) {
        int xPoint = xAxisLocation + (int) (x * xPixelLength);
        int yPoint = this.getHeight() - (yAxisLocation + (int) (y * yPixelLength));
        System.out.println("(" + xPoint + "," + yPoint + ")");
        g.fillOval(xPoint - 5, yPoint - 5, 10, 10);
    }

    public void setXIncrements(double x) {
        xIncrements = x;
    }

    public void setYIncrements(double y) {
        Double yIncrements = y;
    }

    public void setDrawColor(Color n) {
        drawColor = n;
        repaint();
    }

    public void setBackgroundColor(Color n) {
        backgroundColor = n;
        repaint();
    }


    public void setXValues(double[] n) {
        xPoints = n;
        repaint();
    }

    public void setYValues(double[] n) {
        yPoints = n;
        repaint();
    }

}
