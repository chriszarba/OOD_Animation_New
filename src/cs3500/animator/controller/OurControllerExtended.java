package cs3500.animator.controller;

import cs3500.animator.model.IExtendedModel;

public class OurControllerExtended extends OurController {

  protected IControllableView controllableView;
  protected IExtendedModel extendedModel;

  public OurControllerExtended(IControllableView view, IExtendedModel model){
    super(view, model);
    this.controllableView = view;
    this.extendedModel = model;

    this.controllableView.addActionListener(new PauseActionListener(this.controllableView));
    this.controllableView.addActionListener(new LoopActionListener(this.controllableView));
    this.controllableView.addActionListener(new RewindActionListener(this.controllableView));
  }


}
