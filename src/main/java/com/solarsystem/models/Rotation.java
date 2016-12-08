package com.solarsystem.models;

/**
 * Created by renny on 30/11/16.
 */
public enum Rotation {
  CLOCKWISE(1.00),
  COUNTER_CLOCKWISE(-1.00);

  private final double motion;

  Rotation(final double motion) {
    this.motion = motion;
  }

  public double getMotion() {
    return motion;
  }
}
