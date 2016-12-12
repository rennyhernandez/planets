package com.solarsystem;

import com.solarsystem.models.Planet;
import com.solarsystem.models.PlanetService;
import com.solarsystem.utils.MathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
  Logger logger = LoggerFactory.getLogger(VisualPlanet.class);
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
    Logger logger = LoggerFactory.getLogger(VisualPlanet.class);
    public static final int SCALE_SIZE = 10;
    public static final int TIME_DELAY = 500;
    public static final int PLANET_SIZE = 10;
    private Ellipse2D ferenginar, betazed, vulcano;
    private Ellipse2D orbit;
    private int day = 0;
    PlanetService service;
    private Timer timer;
    public Pane() {
      service = new PlanetService();
      timer = new Timer(TIME_DELAY, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          service.movePlanetsToDay(day);
          logInfo();
          day++;
          repaint();
        }

      });
      timer.start();
    }

    private void logInfo() {
      logger.info("---- day {} ----", day);
      logger.info("Angle for Ferenginar: {}º", FERENGINAR.getAngle());
      logger.info("Angle for Vulcano: {}º", VULCANO.getAngle());
      logger.info("Angle for Betazed: {}º", BETAZED.getAngle());
      logger.info("The weather for this day is: {}", service.forecast(day).name());
      logger.info("Area is: {}", service.getArea());
      logger.info("Minimal distance is: {}", MathUtils.getMinimalDistance(FERENGINAR.getPoint(), VULCANO.getPoint(),
          BETAZED.getPoint()));
      logger.info("---- End of day {} ----\n", day);
    }

    @Override
    public Dimension getPreferredSize() {
      return new Dimension(500, 500);
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      drawOrbits(g);
      drawFromFerenginarToBetazed(g);
      drawFromFerenginarToVulcano(g);
      drawDistanceFromBetazedToVulcano(g);
      drawSun(g);
      drawFerenginar(g);
      drawBetazed(g);
      drawVulcano(g);
    }
    private void drawOrbits(final Graphics g){
      drawOrbit(g, FERENGINAR);
      drawOrbit(g, VULCANO);
      drawOrbit(g, BETAZED);
    }

    private void drawOrbit(final Graphics g, Planet planet){
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setColor(Color.LIGHT_GRAY);
      g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      double x = (getWidth() / 2);
      double y =  (getHeight() / 2);
      double radius = scale(planet.getDistance());

      orbit = new Ellipse2D.Double(
          x - radius + PLANET_SIZE / 2,
          y - radius + PLANET_SIZE / 2,
          radius * 2, radius * 2);
      g2d.draw(orbit);
    }

    private void drawDistanceFromBetazedToVulcano(final Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
      if(service.areAligned()){
        g2D.setColor(Color.RED);
      }
      if(service.sunInTriangle()){
        g2D.setColor(Color.ORANGE);
      }
      if(service.arePlanetsAlignedWithSun()){
        g2D.setColor(Color.GREEN);
      }
      g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      Line2D ferenginarToVulcano = new Line2D.Double(
          getCenterX() + scale(BETAZED.getPositionX()),
          getCenterY() + scale(BETAZED.getPositionY()),
          getCenterX() + scale(VULCANO.getPositionX()),
          getCenterY() + scale(VULCANO.getPositionY()));
      g2D.draw(ferenginarToVulcano);
    }

    private void drawFromFerenginarToBetazed(final Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
      g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      if(service.areAligned()){
        g2D.setColor(Color.RED);
      }
      if(service.sunInTriangle()){
        g2D.setColor(Color.ORANGE);
      }
      if(service.arePlanetsAlignedWithSun()){
        g2D.setColor(Color.GREEN);
      }
      Line2D ferenginarToVulcano = new Line2D.Double(
          getCenterX() + scale(FERENGINAR.getPositionX()),
          getCenterY() + scale(FERENGINAR.getPositionY()),
          getCenterX() + scale(BETAZED.getPositionX()),
          getCenterY() + scale(BETAZED.getPositionY()));
      g2D.draw(ferenginarToVulcano);
    }
    private void drawFromFerenginarToVulcano(final Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
      g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      if(service.areAligned()){
        g2D.setColor(Color.RED);
      }
      if(service.sunInTriangle()){
        g2D.setColor(Color.ORANGE);
      }
      if(service.arePlanetsAlignedWithSun()){
        g2D.setColor(Color.GREEN);
      }

      Line2D ferenginarToVulcano = new Line2D.Double(
          getCenterX() + scale(FERENGINAR.getPositionX()),
          getCenterY() + scale(FERENGINAR.getPositionY()),
          getCenterX() + scale(VULCANO.getPositionX()),
          getCenterY() + scale(VULCANO.getPositionY()));
      g2D.draw(ferenginarToVulcano);
    }
    // Scaling values for rendering purposes (actual distances and values might not be rendered properly)
    private double scale(double x){
      return x / SCALE_SIZE;
    }

    private int getCenterX(){
      return getWidth() / 2  + (PLANET_SIZE / 2);
    }

    private int getCenterY(){
      return getWidth() / 2 + (PLANET_SIZE / 2);
    }

    private void drawBetazed(final Graphics g) {
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setColor(Color.BLUE);
      g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      double x1 = (getWidth() / 2) + scale(BETAZED.getPositionX());
      double y1 =  (getHeight() / 2) + scale(BETAZED.getPositionY());
      betazed = new Ellipse2D.Double(x1, y1, PLANET_SIZE, PLANET_SIZE);
      g2d.fill(betazed);
    }

    private void drawFerenginar(final Graphics g) {
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setColor(Color.MAGENTA);
      g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      double x = (getWidth() / 2) + scale(FERENGINAR.getPositionX());
      double y =  (getHeight() / 2) + scale(FERENGINAR.getPositionY());
      ferenginar = new Ellipse2D.Double(x, y, PLANET_SIZE, PLANET_SIZE);
      g2d.fill(ferenginar);
    }

    private void drawVulcano(final Graphics g) {
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setColor(Color.PINK);
      g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      double x = (getWidth() / 2) + scale(VULCANO.getPositionX());
      double y =  (getHeight() / 2) + scale(VULCANO.getPositionY());
      vulcano = new Ellipse2D.Double(x, y, PLANET_SIZE, PLANET_SIZE);
      g2d.fill(vulcano);
    }

    private void drawSun(final Graphics g) {
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setColor(Color.ORANGE);
      g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      double x = (getWidth() / 2);
      double y =  (getHeight() / 2);
      orbit = new Ellipse2D.Double(x, y, PLANET_SIZE, PLANET_SIZE);
      g2d.fill(orbit);
    }

  }
}
