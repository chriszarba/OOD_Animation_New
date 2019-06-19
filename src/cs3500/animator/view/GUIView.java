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

  //private JButton quitbutton;
  private List<IReadOnlyShape> shapes = new ArrayList<IReadOnlyShape>();
  private Timer timer;
  private int ticksPerSecond;
  private JFrame window = new JFrame("Animator");
  private GUIActionListener actionListener;

  private class GUIActionListener implements ActionListener {
    private int tick;
    private IReadOnlyModel model;
    private int direction;

    public GUIActionListener(int startTick, IReadOnlyModel model){
      this.tick = startTick;
      this.model = model;
      this.direction = 1;
    }

    public void setTick(int tick) {
      this.tick = tick;
    }

    public void toggleDirection(){
      direction =  direction < 0 ? 1 : -1;
    }

    public boolean isOver() {
      return (tick == 0 && direction < 0) || (tick == && direction > 0);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      renderByTick(tick, model);
      tick += direction;
      if(tick < 0){
        tick = 0;
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
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JScrollPane pane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    pane.setVisible(true);
    /*JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL);
    JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL);
    pane.add(hbar);
    pane.add(vbar);
    pane.add(this);
     */

    window.add(pane);
  }

  @Override
  public void toggleRewind(){
    this.actionListener.toggleDirection();
  }

  @Override
  public void setCurrentTick(int tick) {
    this.actionListener.setTick(tick);
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
    window.setSize(model.getCanvasWidth(), model.getCanvasHeight());
    this.setBounds(model.getBoundingX(), model.getBoundingY(), model.getCanvasWidth(),
        model.getCanvasHeight());

    this.shapes = model.getAllShapes();
    int ms = (int) (((1 / ((double) ticksPerSecond)) * 1000) + 0.5);
    this.actionListener = new GUIActionListener(0, model);
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
  }

  @Override
  public void paint(Graphics g) {
    if (this.shapes.isEmpty()) {
      if (this.timer != null) {
        this.timer.stop();
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
