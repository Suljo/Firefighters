package main.firefighters;

import main.api.CityNode;
import main.api.Firefighter;
import main.impls.CityImpl;


public class FirefighterImpl implements Firefighter {
  private int nameNumber;
  private CityNode currentLocation;
  private CityNode newLocation;
  private int totalDistance;


  public FirefighterImpl(int nameNumber, CityImpl city) {
    this.totalDistance=0;
    //set initial location to location of firehouse
    this.currentLocation = new CityNode(city.getFireStation().getLocation().getX(), city.getFireStation().getLocation().getY());
    //giving the firefighters to distinguish them
    this.nameNumber = nameNumber;

  }

  @Override
  public CityNode getLocation() {
    return this.currentLocation;
  }

  public void setLocation(CityNode cityNode){
    this.newLocation=cityNode;
  }

  //this method is called upon dispatch
  @Override
  public int distanceTraveled() {
    totalDistance = totalDistance + (this.newLocation.getX()-this.currentLocation.getX())+(this.newLocation.getY()-this.currentLocation.getY());
    //new location becomes current location
    this.currentLocation=this.newLocation;
    return totalDistance;
  }
}
