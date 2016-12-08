package com.solarsystem.client;

import com.solarsystem.models.PlanetService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import static com.solarsystem.models.Planet.*;

/**
 * Created by renny on 08/12/16.
 * This VisualPlanetApp is intended for visual completion and verification
 * purposes.
 */
public class VisualPlanet {
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        Pane plane = new Pane();
        JFrame frame = new JFrame("Visual Planets");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(plane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }
    });
  }
  public static class Pane extends JPanel {

    public static final int SCALE_SIZE = 10;
    public static final int TIME_DELAY = 1000;
    private Ellipse2D ferenginar, betazed, vulcano;
    private Ellipse2D sun;
    private int day = 0;
    private double less;
    PlanetService service;
    private Timer timer;
    public Pane() {
      service = new PlanetService();
      less = 100000;
      System.out.printf("area(VULCANO, BETAZED, FERENGINAR) = %f\n", service.getArea());
      timer = new Timer(TIME_DELAY, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          service.movePlanetsToDay(++day);
          System.out.printf("---- day %d ----\n", day);
          System.out.printf("area(VULCANO, BETAZED, FERENGINAR) = %f\n", service.getArea());
          if(service.areAlignedWithSun()){
            System.out.println("Aligned at day " + day);
          }
          repaint();
        }

      });
      timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
      return new Dimension(500, 500);
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      drawFromFerenginarToBetazed(g);
      drawFromFerenginarToVulcano(g);
      drawDistanceFromBetazedToVulcano(g);
      drawFromSunToBetazed(g);
      drawSun(g);
      drawFerenginar(g);
      drawBetazed(g);
      drawVulcano(g);
    }

    private void drawFromSunToBetazed(final Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
      if(service.areAlignedWithSun()){
        g2D.setColor(Color.GREEN);
      }
      g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      Line2D sunToBetazed = new Line2D.Double(
          getCenterX(),
          getCenterY(),
          getCenterX() + scaleToTen(BETAZED.getPositionX()),
          getCenterY() + scaleToTen(BETAZED.getPositionY()));
      g2D.draw(sunToBetazed);
    }

    private void drawDistanceFromBetazedToVulcano(final Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
      if(service.areAligned()){
        g2D.setColor(Color.RED);
      }
      if(service.areAlignedWithSun()){
        g2D.setColor(Color.GREEN);
      }
      g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      Line2D ferenginarToVulcano = new Line2D.Double(
          getCenterX() + scaleToTen(BETAZED.getPositionX()),
          getCenterY() + scaleToTen(BETAZED.getPositionY()),
          getCenterX() + scaleToTen(VULCANO.getPositionX()),
          getCenterY() + scaleToTen(VULCANO.getPositionY()));
      g2D.draw(ferenginarToVulcano);
    }

    private void drawFromFerenginarToBetazed(final Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
      g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      if(service.areAligned()){
        g2D.setColor(Color.RED);
      }
      if(service.areAlignedWithSun()){
        g2D.setColor(Color.GREEN);
      }
      Line2D ferenginarToVulcano = new Line2D.Double(
          getCenterX() + scaleToTen(FERENGINAR.getPositionX()),
          getCenterY() + scaleToTen(FERENGINAR.getPositionY()),
          getCenterX() + scaleToTen(BETAZED.getPositionX()),
          getCenterY() + scaleToTen(BETAZED.getPositionY()));
      g2D.draw(ferenginarToVulcano);
    }
    private void drawFromFerenginarToVulcano(final Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
      g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      if(service.areAligned()){
        g2D.setColor(Color.RED);
      }
      if(service.areAlignedWithSun()){
        g2D.setColor(Color.GREEN);
      }
      Line2D ferenginarToVulcano = new Line2D.Double(
          getCenterX() + scaleToTen(FERENGINAR.getPositionX()),
          getCenterY() + scaleToTen(FERENGINAR.getPositionY()),
          getCenterX() + scaleToTen(VULCANO.getPositionX()),
          getCenterY() + scaleToTen(VULCANO.getPositionY()));
      g2D.draw(ferenginarToVulcano);
    }

    private double scaleToTen(double x){
      return x / SCALE_SIZE;
    }

    private int getCenterX(){
      return getWidth() / 2  + 5;
    }

    private int getCenterY(){
      return getWidth() / 2 + 5;
    }

    private void drawBetazed(final Graphics g) {
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setColor(Color.BLUE);

      g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      double x1 = (getWidth() / 2) + (BETAZED.getPositionX() / 10);
      double y1 =  (getHeight() / 2) + (BETAZED.getPositionY() / 10);
      betazed = new Ellipse2D.Double(x1, y1, 10, 10);
      g2d.fill(betazed);
    }

    private void drawFerenginar(final Graphics g) {
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setColor(Color.MAGENTA);
      g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      double x = (getWidth() / 2) + (FERENGINAR.getPositionX() / 10);
      double y =  (getHeight() / 2) + (FERENGINAR.getPositionY() / 10);
      ferenginar = new Ellipse2D.Double(x, y, 10, 10);
      g2d.fill(ferenginar);
    }

    private void drawVulcano(final Graphics g) {
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setColor(Color.PINK);
      g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      double x = (getWidth() / 2) + (VULCANO.getPositionX() / 10);
      double y =  (getHeight() / 2) + (VULCANO.getPositionY() / 10);
      vulcano = new Ellipse2D.Double(x, y, 10, 10);
      g2d.fill(vulcano);
    }

    private void drawSun(final Graphics g) {
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setColor(Color.ORANGE);
      g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      double x = (getWidth() / 2);
      double y =  (getHeight() / 2);
      sun = new Ellipse2D.Double(x, y, 10, 10);
      g2d.fill(sun);
    }

    private void drawDistanceBetweenPlanets(final Graphics g, Point a, Point b){
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      g2d.draw(null);
    }
  }
}
