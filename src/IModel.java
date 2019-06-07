import java.awt.Color;
import java.awt.geom.Point2D;

public interface IModel extends IReadOnlyModel {

  /**
   * Adds a shape to the model.
   * @param name the unique name of the shape so it can be stored for reference.
   * @param type the type of shape (enum).
   * @param pos the position of the shape.
   * @param width the width of the shape.
   * @param height the height of the shape.
   * @param color the color of the shape.
   * @return true if the shape is successfully placed in the model, otherwise false.
   */
  boolean addShape(String name, ShapeType type, Point2D pos, double width, double height, Color color);

  /**
   * Removes a shape from the model.
   * @param name the name of the shape.
   * @return true if the shape is successfully removed, false otherwise.
   */
  boolean removeShape(String name);

  /**
   * Adds a motion object to the model.
   * @param name the name of the shape to be acted on.
   * @param t0 the starting tick of the motion.
   * @param t1 the ending tick of the motion.
   * @param startPos the starting position of the shape.
   * @param endPos the ending position of the shape.
   * @param startWidth the starting width of the shape.
   * @param startHeight the starting height of the shape.
   * @param endWidth the ending width of the shape.
   * @param endHeight the ending height of the shape.
   * @param startColor the starting color of the shape.
   * @param endColor the ending color of the shape.
   * @return true if the motion is successfully added, false otherwise.
   */
  boolean addMotion(String name, int t0, int t1, Point2D startPos, Point2D endPos, int startWidth,
      int startHeight, int endWidth, int endHeight, Color startColor, Color endColor);
}
