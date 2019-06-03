import java.awt.Color;
import java.util.List;

public interface IModel {
  void addShape(String name, ShapeType type, int x, int y, int width, int height, Color color);
  void removeShape(String name);
  void moveShape(String name, int t0, int t1, int x1, int y1);
  void visibleShape(String name, int t0, int t1, boolean visible);
  void resizeShape(String name, int t0, int t1, int newWidth, int newHeight);
  void changeColorShape(String name, int t0, int t1, Color newColor);
  List<IShape> getShapes();
}
