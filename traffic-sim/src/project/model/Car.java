package project.model;

import java.awt.Color;

/**
 * A car remembers its position from the beginning of its road.
 * Cars have random velocity and random movement pattern:
 * when reaching the end of a road, the dot either resets its position
 * to the beginning of the road, or reverses its direction.
 */
public class Car implements Agent {
  private String state = "";
  private CarHandler observer;
  private double _position = 0.0D;
  private double _carLength = (int)(MP.maxCarLength * Math.random()) + MP.minCarLength;
  private double _maxVelocity = Math.random() * (MP.maxVelocity - MP.minVelocity) + MP.minVelocity;
  private double _velocity = _maxVelocity;
  private double _breakDistance = Math.random() * (MP.maxBreakDistance - MP.minBreakDistance) + MP.minBreakDistance;
  private double _stopDistance = Math.random() * (MP.maxStopDistance - MP.minStopDistance) + MP.minStopDistance;

  private Color _color = new Color((int)Math.ceil(Math.random() * 255.0D), (int)Math.ceil(Math.random() * 255.0D), (int)Math.ceil(Math.random() * 255.0D));

	
  Car() {
	  _maxVelocity = (Math.random() * (MP.maxVelocity - MP.minVelocity) + MP.minVelocity);
  } // Created only by this package

  public double getPosition()
  {
    return _position;
  }

  public double getNextPosition() {
    return this._position += _velocity;
  }

  public double getVelocity() {
    return _velocity;
  }

  public Color getColor() {
    return _color;
  }

  public void run(double time)
  {
    checkTailGate();
    checkPosOnRoad();

    _position += _velocity;
  }

  public void checkTailGate()
  {
    double distanceBetween = observer.getDistancetoNextObstacle(this);

    if (distanceBetween <= _breakDistance)
    {
      if (distanceBetween <= _stopDistance) {
        _velocity *= MP.stopFactor;
      }
      else {
        _velocity *= MP.breakFactor;

        if (_velocity >= distanceBetween) {
          _velocity = MP.minVelocity;
        }

      }

    }
    else if (_velocity == 0.0D)
      _velocity = (_maxVelocity / 2.0D);
    else
      _velocity = _maxVelocity;
  }

  public void checkPosOnRoad()
  {
    if (getPosition() > observer.getLength() - _carLength)
    {
      CarHandler nextCarHandler = observer.getObservingCarHandlerList().getNext(observer);

      if (nextCarHandler != observer)
      {
        if (nextCarHandler.getState())
        {
          observer.remove(this);
          nextCarHandler.accept(this);
          _position = 0.0D;
        }
      }
      else
      {
        _velocity = 0.0D;
        observer.remove(this);
      }
    }
  }

  public String getState()
  {
    return state;
  }

  public void addObserver(CarHandler carhandler) {
    observer = carhandler;
  }

  public CarHandler getOberver() {
    return observer;
  }

  public void removeObserver() {
    observer = null;
  }

  public double getCarLength()
  {
    return _carLength;
  }

  public double getBreakDistance()
  {
    return _breakDistance;
  }

  public void setPosition(double pos) {
    _position = pos;
  }

  public void setSpeed(double speed)
  {
    _maxVelocity = speed;
  }
}