package com.solarsystem.models;


import static com.solarsystem.models.Planet.BETAZED;
import static com.solarsystem.models.Planet.FERENGINAR;
import static com.solarsystem.models.Planet.VULCANO;

/**
 * Created by renny on 30/11/16.
 */
public class PlanetService {

  public static final int EPSILON = 90000;

  /**
   * Returns true if the three planets are aligned. It calculates
   * the area of a triangle formed by the three planets with the formula written below
   * and compares it to zero (no need to divide by 2):
   * [ Ax * (By - Cy) + Bx * (Cy - Ay) + Cx * (Ay - By) ] = 0
   *
   */

  public boolean areAligned(){
    return getArea() < EPSILON;
  }

  public double getArea() {
    return Math.abs(0.5 * (FERENGINAR.getPositionX() * (VULCANO.getPositionY() - BETAZED.getPositionY())
        + VULCANO.getPositionX() * (BETAZED.getPositionY() - FERENGINAR.getPositionY())
        + BETAZED.getPositionX() * (FERENGINAR.getPositionY() - VULCANO.getPositionY())));
  }
  public double getArea(Point a, Point b, Point c) {
    return Math.abs(0.5 * (a.getX() * (b.getY() - c.getY())
        + b.getX() * (c.getY() - a.getY())
        + c.getX() * (a.getY() - b.getY())));
  }

  public boolean areAlignedWithSun(){
    return getArea(new Point(0,0), FERENGINAR.getPoint(), VULCANO.getPoint()) < 175000 &&
           getArea(new Point(0,0), FERENGINAR.getPoint(), BETAZED.getPoint()) < 175000 &&
           getArea(new Point(0,0), VULCANO.getPoint(), BETAZED.getPoint()) < 175000;
  }
  public boolean areTriangled(){
    return pointZeroInTriangle(FERENGINAR, BETAZED, VULCANO);
  }

  /**
   *
   * @param p1
   * @param p2
   * @return
   */
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
    FERENGINAR.calculatePosition(day);
    BETAZED.calculatePosition(day);
    VULCANO.calculatePosition(day);
  }
}


