package com.solarsystem.models;


import static com.solarsystem.models.Planet.BETAZED;
import static com.solarsystem.models.Planet.FERENGINAR;
import static com.solarsystem.models.Planet.VULCANO;

/**
 * Created by renny on 30/11/16.
 */
public class PlanetService {

  /* Used when calculating alignment between planets*/
  public static final double EPSILON = 1e+5;


  /** Three colinear points must satisfy that (x1y2 - x2y1) + (x2y3 - x3y2) + (x3y1 - x1y3) = 0*/
  public boolean areAligned(){
    Point p1 = FERENGINAR.getPoint();
    Point p2 = VULCANO.getPoint();
    Point p3 = BETAZED.getPoint();
    return getSlopeDiff(p1, p2, p3) < 1.5e+5;
  }
  /**
   * Calculates the slope difference between three lines formed by three points
   */
  private double getSlopeDiff(final Point p1, final Point p2, final Point p3) {
    double x1 = p1.getX();
    double y1 = p1.getY();
    double x2 = p2.getX();
    double y2 = p2.getY();
    double x3 = p3.getX();
    double y3 = p3.getY();

    return Math.abs((x1*y2 - x2*y1) + (x2*y3 - x3*y2) + (x3*y1 - x1*y3));
  }

  /**
   * Returns true if the three planets are aligned. It calculates
   * the area of a triangle formed by the three planets with the formula written below
   * and compares it to zero:
   * [ Ax * (By - Cy) + Bx * (Cy - Ay) + Cx * (Ay - By) ] / 2 = 0
   *
   */
  public double getArea() {
    return getArea(FERENGINAR.getPoint(), VULCANO.getPoint(), BETAZED.getPoint());
  }

  public double getArea(Point a, Point b, Point c) {
    return Math.abs(0.5 * (a.getX() * (b.getY() - c.getY())
        + b.getX() * (c.getY() - a.getY())
        + c.getX() * (a.getY() - b.getY())));
  }

  public boolean areAlignedWithSun(){
    return getSlopeDiff(new Point(0,0), FERENGINAR.getPoint(), VULCANO.getPoint()) < EPSILON &&
           getSlopeDiff(new Point(0,0), FERENGINAR.getPoint(), BETAZED.getPoint()) < EPSILON &&
           getSlopeDiff(new Point(0,0), VULCANO.getPoint(), BETAZED.getPoint()) < EPSILON;
  }
  public boolean areTriangled(){
    return pointZeroInTriangle(FERENGINAR, BETAZED, VULCANO);
  }


  private double sign (Planet p1, Planet p2)
  {
    return -p2.getPositionX() * (p1.getPositionY() - p2.getPositionY()) - (p1.getPositionX() -
        p2.getPositionX()) * -p2.getPositionY();
  }

  private boolean pointZeroInTriangle(Planet v1, Planet v2, Planet v3)
  {
    boolean b1, b2, b3;

    b1 = sign(v1, v2) < 0.0d;
    b2 = sign(v2, v3) < 0.0d;
    b3 = sign(v3, v1) < 0.0d;

    return ((b1 == b2) && (b2 == b3));
  }

  public void movePlanetsToDay(final int day) {
    FERENGINAR.setPositionForDay(day);
    BETAZED.setPositionForDay(day);
    VULCANO.setPositionForDay(day);
  }
}


