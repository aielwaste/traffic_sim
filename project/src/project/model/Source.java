package project.model;

import java.util.ArrayList;
import java.util.List;

public class Source implements CarHandler, Agent {
  private double generationInterval;
  private Queue<Car> carQueue = new Queue<>();

  private double length = 0.0D;
  private int xposition;
  private int yposition;
  private boolean trafficDirection;
  private CarHandlerList observer;
  private List<Car> _cars = new ArrayList<>();
  @SuppressWarnings("unused")
  private int carCount = 1;

  Source(int x, int y, boolean direction) {
    xposition = x;
    yposition = y;
    trafficDirection = direction;
    generationInterval = ((int)(MP.maxCarGeneration * Math.random()) + MP.minCarGeneration);
  }

  public void run(double time) {
    Model model = observer.getObserver();

    if (!carQueue.isEmpty()) {
      moveWaitingCarsAcross();
    }
    if ((time % generationInterval == 0.0D) && (carQueue.size() < 5)) {
      Car x = new Car();
      observer.accept(x, model);
      carCount += 1;
    }
  }

  public void accept(Car d)
  {
    d.addObserver(this);
    carQueue.enqueue(d);
    moveWaitingCarsAcross();
  }

  private void moveWaitingCarsAcross()
  {
    Model model = observer.getObserver();

    List<Car> nextRoadTraffic = observer.getNext(this).getCars();

    if (!nextRoadTraffic.isEmpty()) {
      Car nextCar = (Car)nextRoadTraffic.get(nextRoadTraffic.size() - 1);

      double distance = nextCar.getPosition();

      if (distance > MP.maxCarLength) {
        model.getAgents().add(carQueue.peek());
        observer.getNext(this).accept((Car)carQueue.dequeue());
      }

    }
    else {
      model.getAgents().add(carQueue.peek());
      observer.getNext(this).accept((Car)carQueue.dequeue());
    }
  }

  public void remove(Car d)
  {
    if (d == null) throw new IllegalArgumentException();
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

  public int vertCompareTo(CarHandler that)
  {
    if (yposition < that.getYPos()) {
      return -1;
    }
    if (yposition > that.getYPos()) {
      return 1;
    }
    return 0;
  }

  public int horCompareTo(CarHandler that)
  {
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

  public double getDistancetoNextObstacle(Car car) {
    if (!observer.getNext(this).getCars().isEmpty()) {
      return ((Car)observer.getNext(this).getCars().get(0)).getPosition() + length - car.getPosition() + car.getCarLength();
    }
    return car.getBreakDistance() + 0.1D;
  }

  public boolean getState() {
    return true;
  }

  public double getLength() {
    return length;
  }
}