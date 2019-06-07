import java.awt.Color;
import java.awt.geom.Point2D;

public interface IMotion {
  /**
   * Gets the starting tick of the motion.
   * @return the motion object's starting tick.
   */
  int getStartTick();

  /**
   * Gets the ending tick of the motion.
   * @return the motion object's ending tick.
   */
  int getEndTick();

  /**
   * Gets the initial position of the motion.
   * @return the motion object's initial position field.
   */
  Point2D getInitialPos();

  /**
   * Gets the final position of the motion.
   * @return the motion object's final position field.
   */
  Point2D getFinalPos();

  /**
   * Gets the initial width of the shape being acted upon by the motion.
   * @return the motion object's initial width field.
   */
  double getInitialWidth();

  /**
   * Gets the final width of the shape being acted upon by the motion.
   * @return the motion object's final width field.
   */
  double getFinalWidth();

  /**
   * Gets the initial height of the shape being acted upon by the motion.
   * @return the motion object's initial height field.
   */
  double getInitialHeight();

  /**
   * Gets the final height of the shape being acted upon by the motion.
   * @return the motion object's final height field.
   */
  double getFinalHeight();

  /**
   * Gets the initial color of the shape being acted upon by the motion.
   * @return the motion object's initial color field.
   */
  Color getInitialColor();

  /**
   * Gets the final color of the shape being acted upon by the motion.
   * @return the motion object's final color field.
   */
  Color getFinalColor();
}
