package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

public interface IExtendedModel extends IReadOnlyExtendedModel, IModel {

  /**
   * Adds a new keyframe to the model.
   *
   * @param name - the shape the keyframe is associated with.
   * @param tick - the tick the keyframe is on.
   * @param pos - the position of the associated shape at the keyframe.
   * @param color - the color of the associated shape at the keyframe.
   * @param width - the width of the associated shape at the keyframe.
   * @param height - the height of the associated shape at the keyframe.
   * @return true if the keyframe was added, false if it failed for any reason.
   */
  boolean addKeyFrame(String name, int tick, Point2D pos, Color color, double width, double height);

  boolean removeKeyFrame(String name, int tick);

  boolean editKeyFrame(String name, int tick, Point2D pos, Color color, double width, double height);
}
