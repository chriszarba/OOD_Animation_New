import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * An interface for motions associated with shapes. Motions start at a given tick and end at a later
 * tick. They have the starting and ending positions, dimensions, and color for the shape.
 */
public interface IMotion {
  
  /**
   * Gets the starting tick of the motion.
   * @return the motion object's starting tick.
   */
  int getStartTick();

  /**
   * Get the tick this motion ends at.
   *
   * @return the tick this motion ends at.
   */
  int getEndTick();

  /**
   * Get a {@link Point2D} representing the starting (x, y) position of the shape associated with
   * this motion.
   *
   * @return the starting (x, y) position of the shape associated with this motion.
   */
  Point2D getInitialPos();

  /**
   * Get a {@link Point2D} representing the ending (x, y) position of the shape associated with this
   * motion.
   *
   * @return the ending (x, y) position of the shape associated with this motion.
   */
  Point2D getFinalPos();

  /**

   * Gets the initial width of the shape being acted upon by the motion.
   *
   * @return the motion object's initial width field.
   */
  double getInitialWidth();

  /**
   * Gets the final width of the shape being acted upon by the motion.
   *
   * @return the motion object's final width field.
   */
  double getFinalWidth();

  /**
   * Gets the initial height of the shape being acted upon by the motion.
   *
   * @return the motion object's initial height field.
   */
  double getInitialHeight();

  /**
   * Gets the final height of the shape being acted upon by the motion.
   *
   * @return the motion object's final height field.
   */
  double getFinalHeight();

  /**
   * Gets the initial color of the shape being acted upon by the motion.
   *
   * @return the motion object's initial color field.
   */
  Color getInitialColor();

  /**
   * Gets the final color of the shape being acted upon by the motion.
   *
   * @return the motion object's final color field.
   */
  Color getFinalColor();
}
