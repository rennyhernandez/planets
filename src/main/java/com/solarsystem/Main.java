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
    int maxRain = -1;
    double maxPerimeter = Double.MIN_VALUE;
    for(int day = 1; day <= DAYS_IN_A_YEAR * YEARS; day++){
      Weather weather = service.forecast(day);
      if(service.sunInTriangle()){
        double perimeter = service.getPerimeter();
        if(perimeter >= maxPerimeter) {
          maxPerimeter = perimeter;
          maxRain = day;
        }
      }
      if(daysByWeather.containsKey(weather)) {
        daysByWeather.put(weather, daysByWeather.get(weather) + 1);
      } else {
        daysByWeather.put(weather, 1);
      }
    }

    System.out.printf("FOR 10 YEARS OF %d DAYS THE FORECAST IS:\n", DAYS_IN_A_YEAR);
    System.out.printf("%d DAYS OF DROUGHT\n", daysByWeather.get(Weather.DROUGHT));
    System.out.printf("%d DAYS OF RAIN\n", daysByWeather.get(Weather.RAIN));
    System.out.printf("FULL RAIN IN DAY %d\n", maxRain);
    System.out.printf("%d DAYS OF OPTIMAL CONDITIONS\n", daysByWeather.get(Weather.OPTIMAL));
  }
}
