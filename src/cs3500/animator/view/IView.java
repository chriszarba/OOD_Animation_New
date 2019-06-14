package cs3500.animator.view;

import cs3500.animator.model.IReadOnlyModel;

/**
 * The view interface to display an animation.
 */
public interface IView {

  /**
   * Display an animation using the information in the given model.
   *
   * @param model - the model which contains the animation information.
   * @throws IllegalArgumentException - if the given model is null.
   */
  void render(IReadOnlyModel model) throws IllegalArgumentException;
}
