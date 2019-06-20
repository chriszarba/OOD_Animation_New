package cs3500.animator.model;

import java.util.List;

/**
 * A read-only interface for models extended to support key frames,
 * and allow editing.
 */
public interface IReadOnlyExtendedModel extends IReadOnlyModel {

  /**
   * Gets the maximum tick of all motions/keyframes in the model.
   * @return the highest tick where the model has the appearance of a shape defined.
   */
  int getMaximumTick();


  /**
   * Get a list of all the read-only keyframes associated with a shape.
   * @param name - the name of the shape.
   * @return a list of all the keyframes associated with a shape, an empty list
   *     if the shape doesn't exist.
   */
  List<IReadOnlyKeyFrame> getShapeKeyFrames(String name);
}
