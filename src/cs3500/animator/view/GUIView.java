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
public class GUIView extends JPanel implements IView {

  //private JButton quitbutton;
  private List<IReadOnlyShape> shapes = new ArrayList<IReadOnlyShape>();
  private Timer timer;
  private int ticksPerSecond;
  private JFrame window = new JFrame("Animator");

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
  public void render(IReadOnlyModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("null model");
    }
    window.setSize(model.getCanvasWidth(), model.getCanvasHeight());
    this.setBounds(model.getBoundingX(), model.getBoundingY(), model.getCanvasWidth(),
        model.getCanvasHeight());

    this.shapes = model.getAllShapes();
    int ms = (int) (((1 / ((double) ticksPerSecond)) * 1000) + 0.5);
    this.timer = new Timer(ms, new ActionListener() {
      int tick = 1;

      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        renderByTick(tick++, model);
      }
    });
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
