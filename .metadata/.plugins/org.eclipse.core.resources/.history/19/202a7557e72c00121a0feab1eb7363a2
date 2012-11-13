package project.model;

import java.util.List;

public abstract interface CarHandler
{
  public abstract void accept(Car paramCar);

  public abstract void remove(Car paramCar);

  public abstract List<Car> getCars();

  public abstract int getYPos();

  public abstract int getXPos();

  public abstract boolean getDirect();

  public abstract int vertCompareTo(CarHandler paramCarHandler);

  public abstract int horCompareTo(CarHandler paramCarHandler);

  public abstract void addObserver(CarHandlerList paramCarHandlerList);

  public abstract void removeObserver();

  public abstract CarHandlerList getObservingCarHandlerList();

  public abstract double getDistancetoNextObstacle(Car paramCar);

  public abstract boolean getState();

  public abstract double getLength();
}