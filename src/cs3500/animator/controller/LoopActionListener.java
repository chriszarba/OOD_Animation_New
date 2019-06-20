package cs3500.animator.controller;

import java.awt.event.ActionEvent;

public class LoopActionListener extends AbstractActionListener{

  protected LoopActionListener(IControllableView view) throws IllegalArgumentException {
    super(view);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if(actionEvent.getActionCommand().equals("LoopEvent")){
      this.view.toggleLoop();
    }
  }
}
