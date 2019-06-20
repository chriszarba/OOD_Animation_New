package cs3500.animator.controller;

import cs3500.animator.model.IExtendedModel;
import cs3500.animator.view.IView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseActionListener extends AbstractActionListener {

  protected PauseActionListener(IControllableView view) throws IllegalArgumentException {
    super(view);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if(actionEvent.equals("PauseEvent")){
      this.view.togglePause();
    }
  }
}
