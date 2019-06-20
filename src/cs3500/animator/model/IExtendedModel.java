package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * An interface to support extending a model to support keyframes.
 * Extends {@link IReadOnlyExtendedModel} and {@link IModel}.
 */
public interface IExtendedModel extends IReadOnlyExtendedModel, IModel {

  /**
   * Adds a new keyframe to the model.
   *
   * @param name - the shape the keyframe is associated with.
   * @param tick - the tick the keyframe is on.
   * @return true if the keyframe was added, false if it failed for any reason.
   */
  boolean addKeyFrame(String name, int tick);
  /*
   * @param pos - the position of the associated shape at the keyframe.
   * @param color - the color of the associated shape at the keyframe.
   * @param width - the width of the associated shape at the keyframe.
   * @param height - the height of the associated shape at the keyframe.
   */
  //boolean addKeyFrame(String name, int tick, Point2D pos, Color color, double width, double height);

  /**
   * Removes the keyframe associated with the given shape at the given tick.
   * @param name - the name of the shape the keyframe is associated with.
   * @param tick - the tick of the keyframe to remove.
   * @return true if the keyframe is successfully removed, false otherwise (for example if it
   *     doesn't exist).
   */
  boolean removeKeyFrame(String name, int tick);

  /**
   * Edits the keyframe associated with the shape identified by name at the given tick. You cannot
   * edit the tick.
   * @param name - the name of the shape the kyeframe is associated with.
   * @param tick - the tick the keyframe is at (non-editable).
   * @param pos - the new position for the keyframe.
   * @param color - the new color for the keyframe.
   * @param width - the new width for the keyframe.
   * @param height - the new height for the keyframe.
   * @return true if the keyframe is successfully edited, false otherwise (such as if it doesn't
   *     exist or one of the new values is invalid.
   */
  boolean editKeyFrame(String name, int tick, Point2D pos, Color color, double width, double height);
}
