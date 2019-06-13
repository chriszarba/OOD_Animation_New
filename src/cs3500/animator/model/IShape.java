package cs3500.animator.model;

import java.awt.Color;

/**
 * Interface to represent a shape, can be both read and changed unlike cs3500.animator.model.IReadOnlyShape.
 */
public interface IShape extends IReadOnlyShape {

  /**
   * Set a shape's x coordinate.
   *
   * @param x the new x coordinate to be set for the shape.
   * @return true if successful, false otherwise.
   */
  boolean setX(double x);

  /**
   * Set a shape's y coordinate.
   *
   * @param y the new y coordinate to be set for the shape.
   * @return true if successful, false otherwise.
   */
  boolean setY(double y);

  /**
   * Set a shape's visibility to true or false.
   *
   * @param visible the boolean value that the shape's visible field will be set to.
   */
  void setVisible(boolean visible);

  /**
   * Set a shape's color.
   *
   * @param c the color enum that the shape's color will be set to.
   * @return true if successful, false otherwise.
   */
  boolean setColor(Color c);

  /**
   * Set a shape's width.
   *
   * @param width the width to set the shape to.
   * @return true if successful, false otherwise.
   */
  boolean setWidth(double width);

  /**
   * Set a shape's height.
   *
   * @param height the height to set the shape to.
   * @return true if successful, false otherwise.
   */
  boolean setHeight(double height);

}
