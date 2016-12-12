package com.solarsystem.models;

/**
 * Created by renny on 08/12/16.
 */
/**
 * Represents an x,y pair in a cartesian plane.
 */
public class Point implements Comparable<Point> {
  private transient double x;
  private transient double y;

  public Point(final double x, final double y) {
    this.x = x;
    this.y = y;
  }

  public Point() {
    this(0,0);
  }

  public Point(Point p){
    this.x = p.getX();
    this.y = p.getY();
  }

  public double getX() {
    return x;
  }

  public void setX(final Double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(final Double y) {
    this.y = y;
  }

  public double distance(final Point to){
    double dX = x - to.x;
    double dY = y - to.y;
    return Math.sqrt(dX * dX + dY * dY);
  }

  public int compareTo(final Point p1) {
    if (this.y < p1.y) return -1;
    if (this.y > p1.y) return +1;
    if (this.x < p1.x) return -1;
    if (this.x > p1.x) return +1;
    return 0;
  }
}