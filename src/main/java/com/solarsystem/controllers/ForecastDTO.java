package com.solarsystem.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solarsystem.models.Weather;

/**
 * Created by renny on 11/12/16.
 */
public class ForecastDTO {
  private int day;
  private Weather weather;

  public ForecastDTO(@JsonProperty  final int day,
                     @JsonProperty final Weather weather) {
    this.day = day;
    this.weather = weather;
  }

  public int getDay() {
    return day;
  }

  public void setDay(final int day) {
    this.day = day;
  }

  public Weather getWeather() {
    return weather;
  }

  public void setWeather(final Weather weather) {
    this.weather = weather;
  }
}
