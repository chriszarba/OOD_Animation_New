package cs3500.animator.controller;

import cs3500.animator.view.ControllableView;
import java.awt.event.ActionListener;

public abstract class AbstractActionListener implements ActionListener {
  protected ControllableView view;

  protected AbstractActionListener(ControllableView view) throws IllegalArgumentException {
    if(view == null){
      throw new IllegalArgumentException("null view");
    }
    this.view = view;
  }
}
