package cs3500.animator.view;

public interface IExtendedGUIView extends IView{
  void togglePause();
  void toggleRewind();
  void setSpeed(int ticksPerSecond);
  void setCurrentTick(int tick);
  void toggleLooping();
}
