import java.awt.*;
import java.awt.geom.Point2D;

public class Rectangle extends AbstractShape {

  Rectangle(double x, double y, Color color, double width, double height,
          boolean visible){
    super( x, y, color, width, height, visible);
  }

  @Override
  public ShapeType getShapeType(){
    return ShapeType.RECTANGLE;
  }
}
