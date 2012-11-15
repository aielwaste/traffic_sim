package project.util;

import java.awt.Graphics;

/**
 * Callback interface for {@link SwingAnimator}.
 * @see SwingAnimator
 */
public abstract interface SwingAnimatorPainter {
  public abstract void paint(Graphics arg);
}