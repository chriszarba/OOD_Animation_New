package cs3500.animator.controller;

import cs3500.animator.model.IModel;
import cs3500.animator.view.IView;

public class OurController implements IController {

  private IView view;
  private IModel model;

  public OurController(IView view, IModel model) throws IllegalArgumentException {
    if(view == null || model == null){
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
