import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of {@link IModel}. Stores the shapes and motions for the animation, is the
 * model of the model-view-controller design scheme.
 * Design Decisions and Justification:
 * <p>To associate shapes with a name, and motions with the shape,
 * we used HashMaps, due to the fact they have good performance characteristics for this (O(1)
 * insert/read, order unimportant). The names aren't stored in the motion or shape, because they are
 * only associated with the shape in this model. For example if a user wants to call the same shape
 * something else in a different class, that is possible in this implementation. Furthermore the
 * motions don't store a reference to the shape they refer to for the same reason, and because this
 * model handles the animation, which is the only thing both are required for. In this
 * implementation they are loosely coupled, allowing for greater flexibility in the future. The
 * motions are stored in a list that the name is associated with in the map. This is because a
 * single shape may have multiple associated motions, and the order of these motions does matter.
 * Therefore this allows us to keep the motions sorted by starting tick, using insertion sort (which
 * inserts in O(n) time, keeping the list sorted). This means they are already in chronological
 * order, which both makes it easier to find the correct motion for a given tick. Furthermore
 * motions and shapes are constructed internally, which means that the user does not have to worry
 * about their constructions. This is because they are implementation details, that is they are only
 * relevant to how the model fulfills its requirements, they are not essentially to fulfilling the
 * requirements. This means a User has less reliance on specific interfaces or classes, and allows
 * for easier refactoring of the models internal code without changing its external methods. It also
 * allows for better error checking, preventing a user from inputting a shape that may be invalid
 * for this model's assumptions. For example, our IShape interface does not put constraints on what
 * positions are valid. We made the assumption that the upper left was (0,0), and all coordinates
 * are positive numbers (as is standard in computer graphics). By constructing the shapes and
 * motions internally, this prevents the user from passing in a shape or motion with negative
 * coordinates. Also the coordinates are not locked to what part of the shape they represent. That
 * is something the view needs to worry about, not the model, and so the model makes no assumptions
 * about what the coordinates of a shape represent. </p>
 *
 * <p>We also used Read-Only interfaces for both the model and shapes, which are extended to provide
 * mutable functions. This is due to the fact that only certain other classes should manipulate
 * shapes or the model, allowing someone to choose what classes are allowed to mutate the model. Our
 * model only returns the immutable shapes, as it does the calculations for the correct fields of
 * the shape using the given motions and tick, and so the shapes should not be changed outside of
 * the model. If they need to be a user can construct copies of the shapes, which further decouples
 * it from the model by allowing them more flexibility in class choice. Rather than throwing
 * exceptions for invalid shapes or motions, we simply return a boolean that describes the success
 * of the operation. This means the user does not have to use as many try-catch blocks (as throwing
 * exceptions is costly in performance) and allows a clear success or failure condition to proceed
 * on. </p>
 */
public class OurModel implements IModel {

  protected Map<String, IShape> shapesMap;
  protected Map<String, List<IMotion>> motionsMap;

  /**
   * Constructs a new model.
   */
  public OurModel() {
    shapesMap = new HashMap<>();
    motionsMap = new HashMap<>();
  }

  @Override
  public boolean addShape(String name, ShapeType type, Point2D pos, double width, double height,
      Color color) {
    if (name == null || type == null || pos == null || color == null || pos.getX() < 0
        || pos.getY() < 0 || width < 0 || height < 0) {
      return false;
    }

    IShape shape;
    switch (type) {
      case RECTANGLE:
        shape = new Rectangle(pos, color, width, height, true);
        break;
      case ELLIPSE:
        shape = new Ellipse(pos, color, width, height, true);
        break;
      default:
        return false;
    }

    if (this.shapesMap.containsKey(name)) {
      return false;
    }
    this.shapesMap.put(name, shape);
    return true;
  }

  @Override
  public boolean removeShape(String name) {
    if (shapesMap.containsKey(name)) {
      shapesMap.remove(name);
      motionsMap.remove(name);
      return true;
    }
    return false;
  }

  @Override
  public boolean addMotion(String name, int t0, int t1, Point2D startPos, Point2D endPos,
      double startWidth, double startHeight, double endWidth, double endHeight, Color startColor,
      Color endColor) {
    if (!this.shapesMap.containsKey(name)) {
      return false;
    }

    try {
      IMotion motion = new OurMotion(t0, t1, startPos, endPos, startWidth, startHeight, endWidth,
          endHeight, startColor, endColor);
      return this.addToMotionMap(name, motion);
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  /**
   * Adds the motion to the motion map, ensuring it does not overlap with any of the other motions
   * associated with the same shape. Keeps the lists of motions associated with each shape sorted
   * from lowest to highest by starting tick.
   *
   * @param name - the name of the shape the motion is associated with
   * @param motion - the motion to add to the map.
   * @return true if the motion is successfully added to the map, false otherwise.
   */
  protected boolean addToMotionMap(String name, IMotion motion) {
    // if there is no entry for this name, add one
    if (!this.motionsMap.containsKey(name)) {
      this.motionsMap.put(name, new ArrayList<>());
    }

    // insertion sort
    boolean insert = false;
    int index = 0;
    for (; index < this.motionsMap.get(name).size(); ++index) {
      if ((this.motionsMap.get(name).get(index).getStartTick() <= motion.getStartTick()
          && this.motionsMap.get(name).get(index).getEndTick() > motion.getStartTick())
          || (this.motionsMap.get(name).get(index).getStartTick() < motion.getEndTick()
          && this.motionsMap.get(name).get(index).getEndTick() >= motion.getEndTick())) {
        // overlap
        return false;
      } else if (motion.getStartTick() < this.motionsMap.get(name).get(index).getStartTick()) {
        if (index - 1 >= 0 && this.motionsMap.get(name).get(index - 1).getEndTick() == motion
            .getStartTick()) {
          // ensure if the endTick is the same as this startTick the parameters are the same
          IMotion otherMotion = this.motionsMap.get(name).get(index - 1);
          if (!motion.getInitialPos().equals(otherMotion.getFinalPos())
              || motion.getInitialWidth() != otherMotion.getFinalWidth()
              || motion.getInitialHeight() != otherMotion.getFinalHeight() || !motion
              .getInitialColor().equals(otherMotion.getFinalColor())) {
            return false;
          }
        }
        insert = true;
        break;
      }
    }

    if (insert) {
      this.motionsMap.get(name).add(index, motion);
    } else {
      this.motionsMap.get(name).add(motion);
    }
    return true;
  }

  @Override
  public List<IReadOnlyShape> animate(int tick) {
    return new ArrayList<>();
  }

  @Override
  public List<String> getShapeNames() {
    return new ArrayList<>(this.shapesMap.keySet());
  }

  @Override
  public String getDescription() {
    StringBuilder builder = new StringBuilder();
    for (Map.Entry<String, IShape> entry : this.shapesMap.entrySet()) {
      builder.append("shape ");
      builder.append(entry.getKey());
      builder.append(" ");
      builder.append(this.getTypeString(entry.getValue().getShapeType()));
      builder.append("\n");
      for (IMotion motion : this.motionsMap.get(entry.getKey())) {
        builder.append("motion ");
        builder.append(entry.getKey());

        builder.append(
            String.format(" %-3d %-3.0f %-3.0f %-3.0f %-3.0f %-3d %-3d %-3d", motion.getStartTick(),
                motion.getInitialPos().getX(),
                motion.getInitialPos().getY(), motion.getInitialWidth(), motion.getInitialHeight(),
                motion.getInitialColor().getRed(),
                motion.getInitialColor().getGreen(), motion.getInitialColor().getBlue()));

        builder.append("   ");

        builder.append(
            String.format(" %-3d %-3.0f %-3.0f %-3.0f %-3.0f %-3d %-3d %d", motion.getEndTick(),
                motion.getFinalPos().getX(),
                motion.getFinalPos().getY(), motion.getFinalWidth(), motion.getFinalHeight(),
                motion.getFinalColor().getRed(),
                motion.getFinalColor().getGreen(), motion.getFinalColor().getBlue()));

        builder.append("\n");
      }
      builder.append("\n\n");
    }
    // remove last newlines
    builder.delete(builder.length() - 3, builder.length());

    return builder.toString();
  }

  /**
   * Helper function to get the String value of the enum associated with a shape.
   *
   * @param type - the type of shape to get the String representation for.
   * @return the String representation of the given type.
   */
  protected String getTypeString(ShapeType type) {
    switch (type) {
      case ELLIPSE:
        return "ellipse";
      case RECTANGLE:
        return "rectangle";
      default:
        return "";
    }
  }
}