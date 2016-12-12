package com.solarsystem.models;


/**
 * Created by renny on 23/11/16.
 */
public enum Planet {

  FERENGINAR(500, Rotation.CLOCKWISE, 1),
  BETAZED(2000, Rotation.CLOCKWISE, 3),
  VULCANO(1000, Rotation.COUNTER_CLOCKWISE, 5);

  private final int distance; // the radius of the circumference respect to the sun (0,0)

  private final Rotation rotation;
  private final double angularVelocity;
  private final Point point;
  Planet(final int distance,
                final Rotation rotation,
                final double angularVelocity) {
    this.distance = distance;
    this.rotation = rotation;
    this.angularVelocity = angularVelocity;
    this.point = new Point(distance, 0);
  }

  public int getDistance() {
    return distance;
  }

  public double getPositionX(){return point.getX();}
  public double getPositionY(){return point.getY();}
  public Point getPoint(){return this.point;}

  public int getPeriod() {
    return (int) (360 / Math.abs(angularVelocity));
  }

  /**
   * Sets the position pair by transforming the angle in a given day
   * to polar coordinates
   */
  public void setPositionForDay(final double day){
    // we need to calculate the position for how much radians did the planet moved in a day
    if(rotation.equals(Rotation.CLOCKWISE)){
      point.setX(distance * Math.cos(Math.toRadians(day * angularVelocity)));
      point.setY(distance * Math.sin(Math.toRadians(day * angularVelocity)));
    } else {
      point.setX(distance * Math.cos(Math.toRadians(360 - day * angularVelocity)));
      point.setY(distance * Math.sin(Math.toRadians(360 - day * angularVelocity)));
    }
  }

  public double getAngle(){
    double res = Math.atan2(point.getY(),point.getX());
    return res < 0 ? Math.toDegrees(2 * Math.PI + res) : Math.toDegrees(res);
  }

  /**
   * Sets the position in rectangular coordinates given an angle in degrees.
   *
   */
  public void setPositionForAngle(double angle){
    point.setX(distance * Math.cos(Math.toRadians(angle)));
    point.setY(distance * Math.cos(Math.toRadians(angle)));
  }

}
