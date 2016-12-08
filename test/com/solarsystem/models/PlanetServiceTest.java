package com.solarsystem.models;

import org.junit.Assert;
import org.junit.Test;

import static com.solarsystem.models.Planet.FERENGINAR;


/**
 * Created by renny on 30/11/16.
 */
public class PlanetServiceTest {

  private PlanetService service;

  public PlanetServiceTest() {
    service = new com.solarsystem.models.PlanetService();
  }

  @Test
  public void ferenginarPositionAtDayZero(){
    // Given a day counter representing zero days
    int days = 0;
    double distance = FERENGINAR.getDistance();
    // when asking for position in day 0.
    FERENGINAR.calculatePosition(0);
    // then It should return position (distance,0).
    Assert.assertEquals(distance, FERENGINAR.getPositionX(), 1);
    Assert.assertEquals(0.0, FERENGINAR.getPositionY(), 1);
  }

  @Test
  public void ferenginarPositionAtDayAfterYear(){
    //Given a day count set to one
    int dayOne = 1;
    int dayAfterYear = 361;
    double xAtDayOne, yAtDayOne;
    double distance = FERENGINAR.getDistance();
    // When calculating the ferenginar position at day one
    FERENGINAR.calculatePosition(dayOne);
    xAtDayOne = FERENGINAR.getPositionX();
    yAtDayOne = FERENGINAR.getPositionY();
    FERENGINAR.calculatePosition(dayAfterYear);
    // Then they should be the same
    Assert.assertEquals(xAtDayOne, FERENGINAR.getPositionX(), 1);
    Assert.assertEquals(yAtDayOne, FERENGINAR.getPositionY(), 1);
  }

  @Test
  public void areThePlanetsAligned() {
    int day = 0;
    while(!service.areAligned()){
      service.movePlanetsToDay(day);
      day++;
    }
    System.out.printf("FERENGINAR: X = %f, Y = %f\n", FERENGINAR.getPositionX(), FERENGINAR.getPositionY());
  }
  @Test
  public void areAlignedWithSun(){
    service.movePlanetsToDay(99);
    service.areAlignedWithSun();

  }

}