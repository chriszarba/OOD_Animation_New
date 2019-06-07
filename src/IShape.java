import java.awt.Color;

// Maybe only have getters since we can hold shapes as immutable,
// and make all the variables final
public interface IShape extends IReadOnlyShape {
  void setX(double x);

  void setY(double y);

  void setVisible(boolean visible);

  void setColor(Color c);

  void setWidth(double width);

  void setHeight(double height);

}
