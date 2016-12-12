package com.solarsystem.utils;

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


  public static int mcm(int a, int b){
    return a * (b / mcd(a, b));
  }
}
