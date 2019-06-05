import java.awt.Color;
import java.awt.geom.Point2D;

public interface IModel extends IReadOnlyModel {

  boolean addShape(String name, ShapeType type, Point2D pos, int width, int height, Color color);

  boolean removeShape(String name);

  boolean addMotion(String name, int t0, int t1, Point2D startPos, Point2D endPos, int startWidth,
      int startHeight, int endWidth, int endHeight, Color startColor, Color endColor);
}
