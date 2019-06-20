package cs3500.animator.controller;

import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;

public class RewindActionListener extends AbstractActionListener {

  public RewindActionListener(ControllableView view) throws IllegalArgumentException {
    super(view);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if(actionEvent.getActionCommand().equals("RewindEvent")){
      this.view.toggleRewind();
    }
  }
}
