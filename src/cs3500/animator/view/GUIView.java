package cs3500.animator.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IReadOnlyShape;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 * An implementation of {@link IView} that displays the animation
 * using java swing.
 */
public class GUIView extends JPanel implements ControllableView {

  private List<IReadOnlyShape> shapes = new ArrayList<IReadOnlyShape>();
  private Timer timer;
  private int ticksPerSecond;
  private GUIActionListener actionListener;
  private boolean looping;
  private JFrame window;


  private class GUIActionListener implements ActionListener {
    private int tick;
    private IReadOnlyModel model;
    private int direction;
    private final int startTick;

    public GUIActionListener(int startTick, IReadOnlyModel model){
      this.tick = startTick;
      this.model = model;
      this.direction = 1;
      this.startTick = startTick;
    }

    public void reset() {
      if(direction > 0){
        this.tick = startTick;
      }else{
        this.tick = model.getMaximumTick();
      }
    }

    public void setTick(int tick) {
      this.tick = tick;
    }

    public void toggleDirection(){
      direction =  direction < 0 ? 1 : -1;
    }

    public boolean isOver() {
      return (tick == this.startTick && direction < 0) || (tick == model.getMaximumTick() && direction > 0);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      renderByTick(tick, model);
      tick += direction;
      if(tick < this.startTick){
        this.tick = this.startTick;
      }
    }
  }

  /**
   * Constructs a new GUIView.
   *
   * @param ticksPerSecond - the speed of the animation.
   * @throws IllegalArgumentException if the given speed is 0 or less.
   */
  public GUIView(int ticksPerSecond) throws IllegalArgumentException {
    if (ticksPerSecond <= 0) {
      throw new IllegalArgumentException("ticks per second must be > 0");
    }
    this.ticksPerSecond = ticksPerSecond;
    this.looping = false;
  }

  public GUIView(int ticksPerSecond, boolean constructor){
    this(ticksPerSecond);
    if(constructor){
      this.window = new JFrame("Animator");
      this.window.setVisible(true);
      this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JScrollPane pane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      pane.setVisible(true);
      this.window.add(pane);
    }
  }

  public void setWindowSize(int width, int height){
    this.window.setSize(width, height);
  }

  public void setWindowBounds(int x, int y, int width, int height){
    this.window.setBounds(x, y, width, height);
  }

  @Override
  public void addActionListener(ActionListener actionListener){}

  @Override
  public void toggleRewind(){
    if(!this.timer.isRunning()){
      this.timer.start();
    }
    this.actionListener.toggleDirection();
  }

  @Override
  public void setCurrentTick(int tick) {
    this.actionListener.setTick(tick);
  }


  @Override
  public void toggleLooping() {
    if(!this.timer.isRunning()){
      this.timer.start();
    }
    this.looping = !this.looping;
  }


  @Override
  public void setSpeed(int ticksPerSecond) {
    this.ticksPerSecond = ticksPerSecond;
    int ms = (int) (((1 / ((double) ticksPerSecond)) * 1000) + 0.5);
    this.timer.setDelay(ms);
  }

  @Override
  public void togglePause() {
    if(timer.isRunning()){
      timer.stop();
    }else{
      timer.start();
    }
  }

  @Override
  public void render(IReadOnlyModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("null model");
    }
    this.setBounds(model.getBoundingX(), model.getBoundingY(), model.getCanvasWidth(),
        model.getCanvasHeight());

    this.shapes = model.getAllShapes();
    int ms = (int) (((1 / ((double) ticksPerSecond)) * 1000) + 0.5);
    this.actionListener = new GUIActionListener(1, model);
    this.timer = new Timer(ms, this.actionListener);
    this.timer.setRepeats(true);
    this.timer.start();
  }

  /**
   * Updates shapes to be the list at the given tick, then repaints.
   *
   * @param tick - the current tick.
   * @param model - the model to get the information from.
   */
  private void renderByTick(int tick, IReadOnlyModel model) {
    if(this.actionListener.isOver() && this.timer.isRunning()){
      this.timer.stop();
    }
    this.shapes = model.animate(tick);
    repaint();
    if(this.looping && this.actionListener.isOver()){
      //this.setCurrentTick(1);
      this.actionListener.reset();
      this.timer.start();
    }
  }

  @Override
  public void paint(Graphics g) {
    if (this.shapes.isEmpty()) {
      if (this.timer != null) {
        //this.timer.stop();
      }
      return;
    }
    super.paint(g);
    for (IReadOnlyShape s : this.shapes) {
      switch (s.getShapeType()) {
        case ELLIPSE:
          g.setColor(s.getColor());
          g.fillOval((int) s.getX(), (int) s.getY(), (int) s.getWidth(), (int) s.getHeight());
          break;
        case RECTANGLE:
          g.setColor(s.getColor());
          g.fillRect((int) s.getX(), (int) s.getY(), (int) s.getWidth(), (int) s.getHeight());
          break;
        default:
          break;
      }
    }
  }


}
