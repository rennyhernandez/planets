package com.solarsystem.models;

import org.junit.Assert;
import org.junit.Test;

import static com.solarsystem.models.Planet.*;


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
    FERENGINAR.setPositionForDay(0);
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
    FERENGINAR.setPositionForDay(dayOne);
    xAtDayOne = FERENGINAR.getPositionX();
    yAtDayOne = FERENGINAR.getPositionY();
    FERENGINAR.setPositionForDay(dayAfterYear);

    // Then they should be the same
    Assert.assertEquals(xAtDayOne, FERENGINAR.getPositionX(), 1);
    Assert.assertEquals(yAtDayOne, FERENGINAR.getPositionY(), 1);
  }
  @Test
  public void areAlignedBruteForced(){
    // Given a Planet with position 45 degrees
    FERENGINAR.setPositionForAngle(45);
    VULCANO.setPositionForAngle(45);
    BETAZED.setPositionForAngle(225);
    // When asking if planets are aligned Then it should return true;
    Assert.assertEquals(true, service.areAligned());

  }

  @Test
  public void areAlignedInDay(){
    int day = 308;
    service.movePlanetsToDay(308);
    double area = service.getArea();
    System.out.println(area);
  }
  @Test
  public void areAligned(){
    //Given a day where planets are aligned with the sun
    int day = 1;
    int firstAlignment = findFirstAlignment(day);

    System.out.println(firstAlignment);


  }

  private int findFirstAlignment(int day) {
    while(day < Integer.MAX_VALUE){
      service.movePlanetsToDay(day);
      if(!service.arePlanetsAlignedWithSun() && service.areAligned())
        return day;
      day++;
    }
    return -1;
  }

  @Test
  public void areAlignedWithSunBruteForced(){
    //Given three planets when planets are aligned with the sun on 45º, 45º and 225º
    FERENGINAR.setPositionForAngle(45);
    BETAZED.setPositionForAngle(45);
    VULCANO.setPositionForAngle(225);
    // Then the service must return true
    Assert.assertEquals(true, service.arePlanetsAlignedWithSun());
  }
  @Test
  public void areAlignedWithSun(){
    //Given a day where planets are aligned with the sun
    int day = 1;
    int firstAlignment = findFirstAlignmentWithSun(day);
    //when forecasting day 1351
    Weather forecast = service.forecast(firstAlignment);
    //then forecast must be equals to DROUGHT
    Assert.assertEquals(Weather.DROUGHT, forecast);
  }

  private int findFirstAlignmentWithSun(int day) {
    while(day < 3650){
      service.movePlanetsToDay(day);
      if(service.arePlanetsAlignedWithSun())
        return day;
      day++;
    }
    return -1;
  }

}
