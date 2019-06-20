package cs3500.animator.controller;

import java.awt.event.ActionEvent;

public class RewindActionListener extends AbstractActionListener {

  protected RewindActionListener(IControllableView view) throws IllegalArgumentException {
    super(view);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if(actionEvent.getActionCommand().equals("RewindEvent")){
      this.view.toggleRewind();
    }
  }
}
