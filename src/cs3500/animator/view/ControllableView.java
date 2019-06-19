package cs3500.animator.view;

public interface ControllableView extends IView {
  void togglePause();
  void toggleRewind();
  void setSpeed(int ticksPerSecond);
  void setCurrentTick(int tick);
}
