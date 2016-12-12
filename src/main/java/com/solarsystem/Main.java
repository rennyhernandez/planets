package com.solarsystem;

import com.solarsystem.models.PlanetService;
import com.solarsystem.models.Weather;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by renny on 11/12/16.
 */
public class Main {

  private static final int DAYS_IN_A_YEAR = 365;
  private static final int YEARS = 10;
  private static PlanetService service = new PlanetService();

  public static void main(String[] args) {
    Map<Weather, Integer> daysByWeather = new HashMap<>();
    System.out.printf("FOR %d YEARS OF %d DAYS THE FORECAST IS:\n", YEARS, DAYS_IN_A_YEAR);
    System.out.printf("%d DAYS OF DROUGHT\n", daysByWeather.get(Weather.DROUGHT));
    System.out.printf("%d DAYS OF RAIN\n", daysByWeather.get(Weather.RAIN));
    System.out.printf("FULL RAIN IN DAY %d\n", daysByWeather.get(Weather.FULL_RAIN));
    System.out.printf("%d DAYS OF OPTIMAL CONDITIONS\n", daysByWeather.get(Weather.OPTIMAL));
  }
}
