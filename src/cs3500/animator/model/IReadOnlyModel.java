package cs3500.animator.model;

import java.util.List;

/**
 * A read-only interface for the model of the animation project. This is extended to be mutable with
 * the {@link IModel} interface. The model stores the shapes and motions for the animation project
 * as part of the model-view-controller design pattern.
 */
public interface IReadOnlyModel {

  /**
   * Returns a list of read-only shapes at a given tick. The shapes have the correct color,
   * dimensions, and position for the given tick.
   *
   * @param tick - the tick to get the shapes at.
   * @return The list of shapes in the correct position, with the correct dimensions, and the
   *     correct color for the given tick.
   */
  List<IReadOnlyShape> animate(int tick);

  /**
   * Get the list of names associated with shapes in the model currently.
   *
   * @return A list of the names associaated with shapes in the mdoel.
   */
  List<String> getShapeNames();

  /**
   * Outputs a formatted description of all shapes, and the associated motions. The number values in
   * order are: starting tick, starting x position, the starting y position, the starting width, the
   * starting height, the starting red value of the RGB color, the starting green value of the RGB
   * color, the starting blue value of the RGB color, the ending tick, the ending x position, the
   * ending y position, the ending width, the ending height, the ending red value of the RGB color,
   * the ending green value of the RGB color, and the ending blue value of the RGB color.
   *
   * @return A string that describes each shape and its associated motions.
   */
  //String getDescription();

  /**
   * Get all the motions in a model associated with a shape.
   *
   * @param name - the name of the shape.
   * @return - All motions associated with the given shape.
   */
  List<IMotion> getShapeMotions(String name);

  /**
   * Get a list of all the shapes currently declared in the model.
   *
   * @return - a list of all shapes declared in the model.
   */
  List<IReadOnlyShape> getAllShapes();

  /**
   * Get the width of the canvas.
   *
   * @return - the width of the canvas.
   */
  int getCanvasWidth();

  /**
   * Get the height of the canvas.
   *
   * @return - the height of the canvas.
   */
  int getCanvasHeight();

  /**
   * Get the leftmost X value.
   *
   * @return - the bounding leftmost X value.
   */
  int getBoundingX();

  /**
   * Get the topmost Y value.
   *
   * @return - the bounding topmost Y value.
   */
  int getBoundingY();
}
