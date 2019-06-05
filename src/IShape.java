import java.awt.Color;

// Maybe only have getters since we can hold shapes as immutable,
// and make all the variables final
public interface IShape {
  void setX(double x);

  void setY(double y);

  double getX();

  double getY();

  void setVisible(boolean visible);

  boolean getVisible();

  void setColor(Color c);

  Color getColor();

  void setWidth(double width);

  void setHeight(double height);

  double getWidth();

  double getHeight();

  ShapeType getShapeType();
}
