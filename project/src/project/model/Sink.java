package project.model;

import java.util.ArrayList;
import java.util.List;

public class Sink implements CarHandler {
  private double length = 0.0D;
  private int xposition;
  private int yposition;
  private boolean trafficDirection;
  private CarHandlerList observer;
  private List<Car> _cars = new ArrayList<>();

  Sink(int x, int y, boolean direction) {
    xposition = x;
    yposition = y;
    trafficDirection = direction;
  }

  public void accept(Car d) {
    d.addObserver(this);
    remove(d);
  }

  public void remove(Car d) {
    if (d == null) throw new IllegalArgumentException();

    d.getOberver().getObservingCarHandlerList().getObserver().getAgents().remove(d);
  }

  public List<Car> getCars()
  {
    return _cars;
  }

  public void update(Car car)
  {
    System.out.println("Update received from Subject, state changed to : " + car.getState());
  }

  public int getYPos() {
    return yposition;
  }
  public int getXPos() {
    return xposition;
  }
  public boolean getDirect() {
    return trafficDirection;
  }

  public int vertCompareTo(Road that)
  {
    if (yposition < that.getYPos()) {
      return -1;
    }
    if (yposition > that.getYPos()) {
      return 1;
    }
    return 0;
  }

  public int horCompareTo(CarHandler that) {
    if (xposition < that.getXPos()) {
      return -1;
    }
    if (xposition > that.getXPos()) {
      return 1;
    }
    return 0;
  }

  public void addObserver(CarHandlerList roadlist) {
    observer = roadlist;
  }

  public void removeObserver() {
    observer = null;
  }

  public CarHandlerList getObservingCarHandlerList() {
    return observer;
  }

  public int vertCompareTo(CarHandler that) {
    return 0;
  }

  public double getDistancetoNextObstacle(Car car) {
    return (1.0D / 0.0D);
  }

  public boolean getState() {
    return true;
  }

  public double getLength() {
    return length;
  }
}