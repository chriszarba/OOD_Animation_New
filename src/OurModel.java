import java.awt.Color;
import java.util.List;
import java.util.Map;

public class OurModel implements IModel {
  Map<String, IShape> shapes;

  @Override
  public void addShape(String name, ShapeType type, int x, int y, int width, int height,
      Color color) {

  }

  @Override
  public void removeShape(String name) {

  }

  @Override
  public void moveShape(String name, int t0, int t1, int x1, int y1) {

  }

  @Override
  public void visibleShape(String name, int t0, int t1, boolean visible) {

  }

  @Override
  public void resizeShape(String name, int t0, int t1, int newWidth, int newHeight) {

  }

  @Override
  public void changeColorShape(String name, int t0, int t1, Color newColor) {

  }

  @Override
  public List<IShape> getShapes() {
    return null;
  }
}
