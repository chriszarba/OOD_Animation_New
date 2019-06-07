import java.awt.*;
import java.awt.geom.Point2D;

public class Rectangle extends AbstractShape {

  /**
   * Constructor for a Rectangle shape.
   * @param x the x position of the Rectangle.
   * @param y the y position of the Rectangle.
   * @param color the color of the Rectangle.
   * @param width the width of the Rectangle.
   * @param height the height of the Rectangle.
   * @param visible whether or not the Rectangle is visible.
   */
  Rectangle(double x, double y, Color color, double width, double height,
          boolean visible){
    super( x, y, color, width, height, visible);
  }

  /**
   * Constructor for an Rectangle shape.
   * @param pos the 2D position of the Rectangle.
   * @param color the color of the Rectangle.
   * @param width the width of the Rectangle.
   * @param height the height of the Rectangle.
   * @param visible whether or not the Rectangle is visible.
   */
  Rectangle(Point2D pos, Color color, double width, double height, boolean visible){
    super(pos, color, width, height, visible);
  }

  @Override
  public ShapeType getShapeType(){
    return ShapeType.RECTANGLE;
  }
}
