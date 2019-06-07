import java.awt.*;

public interface IReadOnlyShape {

  double getX();

  double getY();

  boolean getVisible();

  Color getColor();

  double getWidth();

  double getHeight();

  ShapeType getShapeType();
}
