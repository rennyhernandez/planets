package com.solarsystem.utils;

import com.solarsystem.models.Point;

/**
 * Created by renny on 11/12/16.
 */
public class MathUtils {

  public static int mcd(int a, int b){
    while (b > 0){
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }
  public static double getPerimeter(Point p1, Point p2, Point p3){
    double p1p2 = p1.distance(p2);
    double p1p3 = p1.distance(p3);
    double p2p3 = p2.distance(p3);
    return p1p2 + p1p3 + p2p3;
  }

  public static double getMinimalDistance(final Point p1, final Point p2, final Point p3){
    double p1p2 = p1.distance(p2);
    double p1p3 = p1.distance(p3);
    double p2p3 = p2.distance(p3);
    return Math.min(Math.min(p1p2,p1p3),p2p3);
  }

  public static int mcm(int a, int b){
    return a * (b / mcd(a, b));
  }
}
