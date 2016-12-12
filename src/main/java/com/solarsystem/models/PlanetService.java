package com.solarsystem.models;


import com.solarsystem.utils.MathUtils;
import org.springframework.stereotype.Service;

import static com.solarsystem.models.Planet.*;

/**
 * Created by renny on 30/11/16.
 */
@Service
public class PlanetService {

  /*
  * Used when calculating alignment between planets
  * this is value was assigned after calculating minimal areas for
  * every run when the planet are near to be collinear
  */
  public static final double EPSILON = 9e+4;

  public double getArea() {
    return getArea(FERENGINAR.getPoint(), VULCANO.getPoint(), BETAZED.getPoint());
  }

  public double getArea(Point a, Point b, Point c){
    return getArea(a, b, c, true);
  }



  /**
   * Returns true if the three planets are aligned. It calculates
   * the area of a triangle formed by the three planets with the formula written below
   * and compares it to zero:
   * [ Ax * (By - Cy) + Bx * (Cy - Ay) + Cx * (Ay - By) ] / 2 = 0
   *
   */
  public double getArea(Point a, Point b, Point c, boolean unsigned){

    double area = 0.5 * (a.getX() * (b.getY() - c.getY())
        + b.getX() * (c.getY() - a.getY())
        + c.getX() * (a.getY() - b.getY()));

    return unsigned ? Math.abs(area) : area;
  }

  /**
   * Three planets are considered aligned when the area of the forming triangle is
   * between zero and an epsilon.
   * */

  public boolean areAligned(){
    Point p1 = FERENGINAR.getPoint();
    Point p2 = VULCANO.getPoint();
    Point p3 = BETAZED.getPoint();
    double area = getArea(p1, p2, p3);
    return area < EPSILON;

  }

  public boolean arePlanetsAlignedWithSun(){
    return getArea(new Point(0,0), FERENGINAR.getPoint(), VULCANO.getPoint()) < EPSILON &&
           getArea(new Point(0,0), FERENGINAR.getPoint(), BETAZED.getPoint()) < EPSILON &&
           getArea(new Point(0,0), VULCANO.getPoint(), BETAZED.getPoint()) < EPSILON;
  }
  public boolean sunInTriangle(){
    return sunInTriangle(FERENGINAR, BETAZED, VULCANO);
  }


  /**
   *  Given three Planets p0, p1 and p2, calculate if the sun is inside their triangle formation using
   *  barycentric coordinates according to http://stackoverflow.com/a/2049712/2744577
   *  and returns whether if it is inside or not.
   * @param p0 a given Point (x0,y0)
   * @param p1 a given Point (x1,y1)
   * @param p2 a given Point (x2,y2)
   * @return true if the sun is inside the triangle, false if not.
   */

  public boolean sunInTriangle(Planet p0, Planet p1, Planet p2)
  {
    double x0 = p0.getPositionX();
    double y0 = p0.getPositionY();
    double x1 = p1.getPositionX();
    double y1 = p1.getPositionY();
    double x2 = p2.getPositionX();
    double y2 = p2.getPositionY();
    double xs, ys;
    xs = ys = 0.0;
    double area = 0.5 * (-y1 * x2 + y0 * (-x1 + x2) + x0 * (y1 - y2) + x1 * y2);
    int sign = area < 0 ? -1 : 1;
    double s = (y0 * x2 - x0 * y2 + (y2 - y0) * xs + (x0 - x2) * ys) * sign;
    double t = (x0 * y1 - y0 * x1 + (y0 - y1) * xs + (x1 - x0) * ys) * sign;

    return s > 0 && t > 0 && (s + t) < 2 * area * sign;
  }


  public void movePlanetsToDay(final int day) {
    FERENGINAR.setPositionForDay(day);
    BETAZED.setPositionForDay(day);
    VULCANO.setPositionForDay(day);
  }

  public Weather forecast(final int day) {
    movePlanetsToDay(day);
    if(arePlanetsAlignedWithSun()){
      return Weather.DROUGHT;
    } else if(areAligned()){
      return Weather.OPTIMAL;
    } else if(sunInTriangle()){
      return Weather.RAIN;
    }
    return Weather.NORMAL;
  }

  public double getMinimalDistance(final Point p1, final Point p2, final Point p3){
    double p1p2 = p1.distance(p2);
    double p1p3 = p1.distance(p3);
    double p2p3 = p2.distance(p3);
    return Math.min(Math.min(p1p2,p1p3),p2p3);
  }

  public double getPerimeter(){
    return getPerimeter(FERENGINAR.getPoint(), VULCANO.getPoint(), BETAZED.getPoint());
  }
  public double getPerimeter(Point p1, Point p2, Point p3){
    double p1p2 = p1.distance(p2);
    double p1p3 = p1.distance(p3);
    double p2p3 = p2.distance(p3);
    return p1p2 + p1p3 + p2p3;
  }
}


