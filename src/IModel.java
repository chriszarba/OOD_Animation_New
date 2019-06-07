import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * An interface for the model of the animation project,
 * this extends {@link IReadOnlyModel} to allow mutation.
 * The model stores shapes and motions for those shapes.
 */
public interface IModel extends IReadOnlyModel {

  /**
   * Adds a shape to the model. If any of the parameters are incorrect or the name
   * is already in use, this method will fail.
   *
   * @param name - the id associated with the shape
   * @param type - the type of shape it is
   * @param pos - the initial (x, y) position of the shape
   * @param width - the width of the shape
   * @param height - the height of the shape
   * @param color - the color of the shape
   * @return true if the shape was sucessfully added to the model, false otherwise.
   */
  boolean addShape(String name, ShapeType type, Point2D pos, double width, double height, Color color);

  /**
   * Removes the shape and all of its associated motions from the model.
   *
   * @param name - the name associated with the shape to remove.
   * @return true if the shape was sucessfully removed, false otherwise (such as if it
   * didn't exist).
   */
  boolean removeShape(String name);

  /**
   * Adds a motion for a shape already in the model.
   *
   * @param name - the name of the shape the motion is associated with.
   * @param t0 - the starting tick of the motion.
   * @param t1 - the ending tick of the motion.
   * @param startPos - the starting position for the shape.
   * @param endPos - the ending position for the shape.
   * @param startWidth - the starting width for the shape.
   * @param startHeight - the starting height for the shape.
   * @param endWidth - the ending width for the shape.
   * @param endHeight - the ending height for the shape.
   * @param startColor - the starting color for the shape.
   * @param endColor - the ending color for the shape.
   * @return true if the motion was sucessfully added and associated with a shape,
   * false otherwise.
   */
  boolean addMotion(String name, int t0, int t1, Point2D startPos, Point2D endPos, double startWidth,
      double startHeight, double endWidth, double endHeight, Color startColor, Color endColor);
}
