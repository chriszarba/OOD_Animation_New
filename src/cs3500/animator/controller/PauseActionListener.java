package cs3500.animator.controller;

import cs3500.animator.model.IExtendedModel;
import cs3500.animator.view.ControllableView;
import cs3500.animator.view.IView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseActionListener extends AbstractActionListener {

  public PauseActionListener(ControllableView view) throws IllegalArgumentException {
    super(view);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if(actionEvent.getActionCommand().equals("PauseEvent")){
      this.view.togglePause();
    }
  }
}
