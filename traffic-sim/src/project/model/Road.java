package project.model;

import java.util.ArrayList;
import java.util.List;

public class Road implements CarHandler {
  private double length = Math.random() * (MP.maxroadLength - MP.minroadLength) + MP.minroadLength;
  private int xposition;
  private int yposition;
  private boolean trafficDirection;
  private CarHandlerList observer;
  private List<Car> _cars = new ArrayList<>();

  Road(int x, int y, boolean direction) {
    xposition = x;
    yposition = y;
    trafficDirection = direction;
  }

  public void accept(Car d) {
    if (d == null) throw new IllegalArgumentException();
    _cars.add(d);
    d.addObserver(this);
  }

  public void remove(Car d) {
    if (d == null) throw new IllegalArgumentException();
    _cars.remove(d);
    d.removeObserver();
  }

  public List<Car> getCars() {
    return _cars;
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

  public int vertCompareTo(Road that) {
    if (yposition < that.yposition) {
      return -1;
    }
    if (yposition > that.yposition) {
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
    if ((_cars.size() > 1) && (_cars.indexOf(car) != 0)) {
      Car comparingCar = (Car)_cars.get(_cars.indexOf(car) - 1);

      return comparingCar.getPosition() - car.getPosition() - car.getCarLength();
    }

    if ((length - car.getPosition() - car.getCarLength() <= car.getBreakDistance()) && (!observer.getNext(this).getState())) {
      return length - car.getPosition() - car.getCarLength();
    }

    if (!observer.getNext(observer.getNext(this)).getCars().isEmpty()) {
      return ((Car)observer.getNext(observer.getNext(this)).getCars().get(0)).getPosition() + length - car.getPosition() - car.getCarLength();
    }
    return (1.0D / 0.0D);
  }

  public boolean getState() {
    return true;
  }

  public double getLength() {
    return length;
  }
}