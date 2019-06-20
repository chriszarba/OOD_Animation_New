package cs3500.animator.controller;

import java.awt.event.ActionListener;

public abstract class AbstractActionListener implements ActionListener {
  protected IControllableView view;

  protected AbstractActionListener(IControllableView view) throws IllegalArgumentException {
    if(view == null){
      throw new IllegalArgumentException("null view");
    }
    this.view = view;
  }
}
