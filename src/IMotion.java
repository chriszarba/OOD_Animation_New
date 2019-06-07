import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * An interface for motions associated with shapes. Motions start at a given tick and end at a later
 * tick. They have the starting and ending positions, dimensions, and color for the shape.
 */
public interface IMotion {

  /**
   * Get the tick this motion starts at.
   *
   * @return the tick this motion starts at.
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
   * Get the starting width of the shape associated with this motion.
   *
   * @return the starting width of the shape associated with this motion.
   */
  int getInitialWidth();

  /**
   * Get the ending width of the shape associated with this motion.
   *
   * @return the ending width of the shape associated with this motion.
   */
  int getFinalWidth();

  /**
   * Get the starting height of the shape associated with this motion.
   *
   * @return the starting height of the shape associated with this motion.
   */
  int getInitialHeight();

  /**
   * Get the ending height of the shape associated with this motion.
   *
   * @return the ending height of the shape associated with this motion.
   */
  int getFinalHeight();

  /**
   * Get the starting color of the shape associated with this motion.
   *
   * @return the starting color of the shape associated with this motion.
   */
  Color getInitialColor();

  /**
   * Get the ending color of the shape associated with this motion.
   *
   * @return the ending color of the shape associated with this motion.
   */
  Color getFinalColor();
}
