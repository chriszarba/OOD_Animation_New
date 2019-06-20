package cs3500.animator.view;

import java.awt.event.ActionListener;

public interface ControllableView extends IView {
  void togglePause();
  void toggleRewind();
  void setSpeed(int ticksPerSecond);
  void setCurrentTick(int tick);
  void toggleLooping();
  void addActionListener(ActionListener actionListener);
}
