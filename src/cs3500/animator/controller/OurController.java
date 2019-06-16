package cs3500.animator.controller;

import cs3500.animator.model.IModel;
import cs3500.animator.view.IView;

/**
 * A concrete implementation of {@link IController}.
 */
public class OurController implements IController {

  private final IView view;
  private final IModel model;

  /**
   * Constructs a controller for the given view and model.
   *
   * @param view - the view to use.
   * @param model - the model to use.
   * @throws IllegalArgumentException - if either the view or model is null.
   */
  public OurController(IView view, IModel model) throws IllegalArgumentException {
    if (view == null || model == null) {
      throw new IllegalArgumentException("null argument");
    }
    this.view = view;
    this.model = model;
  }

  @Override
  public void run() {
    this.view.render(model);
  }
}
