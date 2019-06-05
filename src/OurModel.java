import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

public class OurModel implements IModel {
  Map<String, IShape> shapesMap;
  Map<String, List<IMotion>> motionsMap;

  @Override
  public void addShape(String name, ShapeType type, Point2D pos, int width, int height,
                       Color color) {

  }

  @Override
  public void removeShape(String name) {

  }

  @Override
  public void addMotion(String name, int t0, int t1, Point2D startPos, Point2D endPos,
                        int startWidth, int startLength, int endWidth, int endLength, Color startColor, Color endColor) {

  }
}
