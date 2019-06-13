package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Class to represent a shape that is an cs3500.animator.model.Ellipse.
 */
public class Ellipse extends AbstractShape {

  /**
   * Constructor for an cs3500.animator.model.Ellipse shape.
   *
   * @param name the name associated with this shape
   * @param x the x position of the cs3500.animator.model.Ellipse.
   * @param y the y position of the cs3500.animator.model.Ellipse.
   * @param color the color of the cs3500.animator.model.Ellipse.
   * @param width the width of the cs3500.animator.model.Ellipse.
   * @param height the height of the cs3500.animator.model.Ellipse.
   * @param visible whether or not the cs3500.animator.model.Ellipse is visible.
   */
  Ellipse(String name, double x, double y, Color color, double width, double height,
      boolean visible) {
    super(name, x, y, color, width, height, visible);
  }

  /**
   * Constructor for an cs3500.animator.model.Ellipse shape.
   *
   * @param name the name associated with this shape
   * @param pos the 2D position of the cs3500.animator.model.Ellipse.
   * @param color the color of the cs3500.animator.model.Ellipse.
   * @param width the width of the cs3500.animator.model.Ellipse.
   * @param height the height of the cs3500.animator.model.Ellipse.
   * @param visible whether or not the cs3500.animator.model.Ellipse is visible.
   */
  Ellipse(String name, Point2D pos, Color color, double width, double height, boolean visible) {
    super(name, pos, color, width, height, visible);
  }

  @Override
  public ShapeType getShapeType() {
    return ShapeType.ELLIPSE;
  }
}
