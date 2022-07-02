package main.firefighters;

import main.api.CityNode;
import main.api.FireDispatch;
import main.api.Firefighter;
import main.impls.CityImpl;

import java.util.ArrayList;
import java.util.List;

public class FireDispatchImpl implements FireDispatch {

  private List<FirefighterImpl> firefighters;
  private CityImpl victimCity;

  public FireDispatchImpl(CityImpl city){
    this.victimCity=city;
    this.firefighters = new ArrayList<FirefighterImpl>();
  }

  @Override
  public void setFirefighters(int numFirefighters) {
    for(int i=1; i<=numFirefighters; i++) {
      //adding firefighters to the list, letting them know layout of the city..ie. firehouse location
      this.firefighters.add(new FirefighterImpl(i, victimCity));
    }
  }

  @Override
  public List<Firefighter> getFirefighters() {
    return (List<Firefighter>)(List<?>)firefighters;
  }

  @Override
  public void dispatchFirefighers(CityNode... burningBuildings) {
    try {
      //case of one firefighter putting out all the fires. from fire to fire
      if (firefighters.size() == 1) {
        for (CityNode burningBuilding : burningBuildings) {
          this.firefighters.get(0).setLocation(burningBuilding);
          this.victimCity.getBuilding(burningBuilding).extinguishFire();
        }
      }

      //case of same number of firefighters putting out same number of fires.. divide and conquer
      else {
        for (int i = 0; i <= burningBuildings.length; i++) {
          this.firefighters.get(i).setLocation(burningBuildings[i]);
          this.victimCity.getBuilding(burningBuildings[i]).extinguishFire();
        }
      }
    }catch (Exception e){
    System.out.println(e.getLocalizedMessage());
    }
  }
}
