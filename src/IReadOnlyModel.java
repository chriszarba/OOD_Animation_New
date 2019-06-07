import java.util.List;

public interface IReadOnlyModel {
  /**
   * Returns a list of all the shapes at a given tick.
   * @param tick the tick that the model is at in the animation.
   * @return List of all shapes at that tick.
   */
  List<IReadOnlyShape> getShapesAtTick(int tick);

  /**
   * Returns the names of all shapes.
   * @return List of String representing names of all shapes.
   */
  List<String> getShapeNames();

  /**
   * Returns a description of all the motions in the model animation.
   * @return A String with all descriptions.
   */
  String getDescription();
}
