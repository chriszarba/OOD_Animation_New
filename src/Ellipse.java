import java.awt.*;

public class Ellipse extends AbstractShape {

  Ellipse(double x, double y, Color color, double width, double height,
          boolean visible){
    super( x, y, color, width, height, visible);
  }

  @Override
  public ShapeType getShapeType(){
    return ShapeType.OVAL;
  }
}
