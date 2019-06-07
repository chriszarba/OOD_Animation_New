import java.util.List;

/**
 * A read-only interface for the model of the animation project.
 * This is extended to be mutable with the {@link IModel} interface.
 * The model stores the shapes and motions for the animation project as
 * part of the model-view-controller design pattern.
 */
public interface IReadOnlyModel {

  /**
   * Returns a list of read-only shapes at a given tick. The shapes have the correct
   * color, dimensions, and position for the given tick.
   *
   * @param tick - the tick to get the shapes at.
   * @return The list of shapes in the correct position, with the correct dimensions, and
   * the correct color for the given tick.
   */
  List<IReadOnlyShape> animate(int tick);

  /**
   * Get the list of names associated with shapes in the model currently.
   *
   * @return A list of the names associaated with shapes in the mdoel.
   */
  List<String> getShapeNames();

  /**
   * Outputs a formatted description of all shapes, and the associated motions.
   * The number values in order are: starting tick, starting x position, the
   * starting y position, the starting width, the starting height, the starting
   * red value of the RGB color, the starting green value of the RGB color, the
   * starting blue value of the RGB color, the ending tick, the ending x position,
   * the ending y position, the ending width, the ending height, the ending red value
   * of the RGB color, the ending green value of the RGB color, and the ending blue
   * value of the RGB color.
   *
   * @return A string that describes each shape and its associated motions.
   */
  String getDescription();
}
