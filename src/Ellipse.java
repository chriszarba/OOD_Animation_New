import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Class to represent a shape that is an Ellipse.
 */
public class Ellipse extends AbstractShape {

  /**
   * Constructor for an Ellipse shape.
   *
   * @param x the x position of the Ellipse.
   * @param y the y position of the Ellipse.
   * @param color the color of the Ellipse.
   * @param width the width of the Ellipse.
   * @param height the height of the Ellipse.
   * @param visible whether or not the Ellipse is visible.
   */
  Ellipse(double x, double y, Color color, double width, double height,
      boolean visible) {
    super(x, y, color, width, height, visible);
  }

  /**
   * Constructor for an Ellipse shape.
   *
   * @param pos the 2D position of the Ellipse.
   * @param color the color of the Ellipse.
   * @param width the width of the Ellipse.
   * @param height the height of the Ellipse.
   * @param visible whether or not the Ellipse is visible.
   */
  Ellipse(Point2D pos, Color color, double width, double height, boolean visible) {
    super(pos, color, width, height, visible);
  }

  @Override
  public ShapeType getShapeType() {
    return ShapeType.ELLIPSE;
  }
}
