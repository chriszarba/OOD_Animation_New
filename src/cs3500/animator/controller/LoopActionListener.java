package cs3500.animator.controller;

import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;

public class LoopActionListener extends AbstractActionListener{

  public LoopActionListener(ControllableView view) throws IllegalArgumentException {
    super(view);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if(actionEvent.getActionCommand().equals("LoopEvent")){
      this.view.toggleLooping();
    }
  }
}
