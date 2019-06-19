package cs3500.animator.model;

import java.util.List;

public interface IReadOnlyExtendedModel extends IReadOnlyModel {
  int getMaximumTick();

  List<IReadOnlyKeyFrame> getShapeKeyFrames(String name);
}
