import java.awt.Color;

public interface IShape {
  void setX(int x);
  void setY(int y);
  int getX();
  int getY();
  void setVisible(boolean visible);
  boolean getVisible();
  void setColor(Color c);
  Color getColor();
  void setWidth(int width);
  void setHeight(int height);
  int getWidth();
  int getHeight();
}
