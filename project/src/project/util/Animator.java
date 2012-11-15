package project.util;

import java.util.Observer;
  
/**
 * An interface for displaying simulations.
 */
public abstract interface Animator extends Observer {
  public abstract void dispose();
}