package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * An interface for keyframes that doesn't allow mutation.
 */
public interface IReadOnlyKeyFrame {

  /**
   * Get the tick of the keyframe.
   * @return - the tick of the keyframe.
   */
  int getTick();

  /**
   * Get the position of the keyframe.
   * @return - the position of the keyframe.
   */
  Point2D getPosition();

  /**
   * Gets the color of the keyframe.
   * @return - the color of the keyframe.
   */
  Color getColor();

  /**
   * Gets the width of the keyframe.
   * @return - the width of the keyframe.
   */
  double getWidth();

  /**
   * Gets the height of the keyframe.
   * @return - the height of the keyframe.
   */
  double getHeight();
}
