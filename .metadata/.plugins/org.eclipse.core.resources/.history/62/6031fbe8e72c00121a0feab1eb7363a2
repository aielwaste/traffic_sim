package project.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import project.util.Animator;

/**
 * An example to model for a simple visualization.
 * The model contains two roads.
 * See {@link #SimpleModel(AnimatorBuilder)}.
 */
public class SimpleModel extends Observable {
  private List<Agent> _agents;
  private Animator _animator;
  private boolean _disposed;
  private double _time;

  /** Creates a model to be visualized using the <code>builder</code>.
   *  If the builder is null, no visualization is performed.
   *  Each road has one {@link Car}.
   *
   */
  public SimpleModel(AnimatorBuilder builder) {
    if (builder == null) {
      builder = new NullAnimatorBuilder();
    }
    _agents = new ArrayList<Agent>();
    setup(builder);
    _animator = builder.getAnimator();
    super.addObserver(_animator);
  }

  /**
   * Run the simulation for <code>duration</code> model seconds.
   */
  public void run(int duration) {
    if (_disposed)
      throw new IllegalStateException();
    for (int i=0; i<duration; i++) {
      _time++;
      Agent[] agents_copy = _agents.toArray(new Agent[0]);
      for (Agent a : agents_copy) {
        a.run(_time);
      }
      super.setChanged();
      super.notifyObservers();
    }
  }

  /**
   * Throw away this model.
   */
  public void dispose() {
    _animator.dispose();
    _disposed = true;
  }

  private void setup(AnimatorBuilder builder) {
      Road r1 = new Road();
      builder.addHorizontalRoad(r1, 0, 1, false);
      Car c = new Car();
      r1.accept(c);
      _agents.add(c);
      Road r2 = new Road();
      builder.addHorizontalRoad(r2, 1, 1, false);

      // Road oldroad = null;
      // for (int j = 0; j <= columns; j++) {
      //   Road newroad = new Road();
      //   builder.addHorizontalRoad(newroad, 0, (eastToWest? columns-j : j), eastToWest);	  
      //   newroad.setNext(oldroad);
      //   oldroad = newroad;
      // }
  }
}
