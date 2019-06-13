package cs3500.animator.view;

import cs3500.animator.model.IReadOnlyModel;

public interface IView {
  void render(IReadOnlyModel model) throws IllegalArgumentException;
}
