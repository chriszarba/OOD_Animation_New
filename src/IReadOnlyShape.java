import java.awt.Color;

/**
 * Interface to represent a shape which can only be read; its fields cannot be changed through this
 * interface.
 */
public interface IReadOnlyShape {

  /**
   * Gets the X coordinate of a shape.
   *
   * @return the X coordinate of a shape.
   */
  double getX();

  /**
   * Gets the Y coordinate of a shape.
   *
   * @return the Y coordinate of a shape.
   */
  double getY();

  /**
   * Gets the visible field of a shape.
   *
   * @return the visible field of a shape.
   */
  boolean getVisible();

  /**
   * Gets the Color enum of a shape.
   *
   * @return the Color enum of a shape.
   */
  Color getColor();

  /**
   * Gets the width of a shape.
   *
   * @return the width of a shape.
   */
  double getWidth();

  /**
   * Gets the height of a shape.
   *
   * @return the height of a shape.
   */
  double getHeight();

  /**
   * Gets the ShapeType enum of a shape.
   *
   * @return the ShapeType enum of a shape.
   */
  ShapeType getShapeType();
}
