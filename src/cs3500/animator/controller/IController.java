package cs3500.animator.controller;

/**
 * Controller interface for a controller that uses {@link cs3500.animator.model.IModel} as the model
 * and {@link cs3500.animator.view.IView} as the view.
 */
public interface IController {

  /**
   * Runs the animation.
   */
  void run();
}
