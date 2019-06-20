package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * An interface for keyframes that allows mutation. Extends
 * {@link IReadOnlyKeyFrame}.
 */
public interface IKeyFrame extends IReadOnlyKeyFrame {
  //void setTick(int tick) throws IllegalArgumentException;

  /**
   * Sets the height of the keyframe to the given height.
   * @param height - the new height for the keyframe.
   * @throws IllegalArgumentException - if the given height is negative.
   */
  void setHeight(double height) throws IllegalArgumentException;

  /**
   * Set the width of the keyframe to the given width.
   * @param width - the new width for the keyframe.
   * @throws IllegalArgumentException - if the given width is negative.
   */
  void setWidth(double width) throws IllegalArgumentException;

  /**
   * Sets the position of the keyframe to the given position
   * @param pos - the new position for the keyframe.
   * @throws IllegalArgumentException - if the given pos is null.
   */
  void setPosition(Point2D pos) throws IllegalArgumentException;

  /**
   * Sets the color of the keyframe to the given color.
   * @param color - the new color for the keyframe.
   * @throws IllegalArgumentException - if the given color is null.
   */
  void setColor(Color color) throws IllegalArgumentException;
}
