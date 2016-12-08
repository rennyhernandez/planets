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
  public void calculatePosition(final int day){
    // we need to calculate how much degrees did the planet moved in a day
    if(rotation.equals(Rotation.CLOCKWISE)){
      point.setX(distance * Math.cos(Math.toRadians(day * angularVelocity)));
      point.setY(distance * Math.sin(Math.toRadians(day * angularVelocity)));
    } else {
      point.setX(distance * Math.sin(Math.toRadians(day * angularVelocity)));
      point.setY(distance * Math.cos(Math.toRadians(day * angularVelocity)));
    }

  }

}
