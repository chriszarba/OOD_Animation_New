package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Class to represent a shape that is a rectangle.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructor for a cs3500.animator.model.Rectangle shape.
   *
   * @param name the name associated with this shape
   * @param x the x position of the cs3500.animator.model.Rectangle.
   * @param y the y position of the cs3500.animator.model.Rectangle.
   * @param color the color of the cs3500.animator.model.Rectangle.
   * @param width the width of the cs3500.animator.model.Rectangle.
   * @param height the height of the cs3500.animator.model.Rectangle.
   * @param visible whether or not the cs3500.animator.model.Rectangle is visible.
   */
  Rectangle(String name, double x, double y, Color color, double width, double height,
      boolean visible) {
    super(name, x, y, color, width, height, visible);
  }

  /**
   * Constructor for an cs3500.animator.model.Rectangle shape.
   *
   * @param name the name associated with this shape
   * @param pos the 2D position of the cs3500.animator.model.Rectangle.
   * @param color the color of the cs3500.animator.model.Rectangle.
   * @param width the width of the cs3500.animator.model.Rectangle.
   * @param height the height of the cs3500.animator.model.Rectangle.
   * @param visible whether or not the cs3500.animator.model.Rectangle is visible.
   */
  Rectangle(String name, Point2D pos, Color color, double width, double height, boolean visible) {
    super(name, pos, color, width, height, visible);
  }

  @Override
  public ShapeType getShapeType() {
    return ShapeType.RECTANGLE;
  }
}
