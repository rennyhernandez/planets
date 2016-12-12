package com.solarsystem.controllers;

import com.solarsystem.models.PlanetService;
import com.solarsystem.models.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.*;

/**
 * Created by renny on 09/12/16.
 */
@RestController
@RequestMapping("/forecast")
public class ForecastController {
  @Autowired PlanetService service;
  @RequestMapping(value = "/get-weather/{day}",
      method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
  ForecastDTO getWeather(@PathVariable int day) {
    Weather weather = service.forecast(day);
    return new ForecastDTO(day, weather);
  }
}
