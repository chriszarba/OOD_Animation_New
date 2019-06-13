package cs3500.animator.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IReadOnlyShape;
import cs3500.animator.model.IShape;

public class GUIView extends JPanel implements IView {

  //private JButton quitbutton;
  private JLabel display;
  private List<IReadOnlyShape> shapes = new ArrayList<IReadOnlyShape>();
  private Timer timer;
  private int ticksPerSecond;

  public GUIView(int ticksPerSecond){
    this.ticksPerSecond = ticksPerSecond;
    JFrame window = new JFrame("Animator");
    window.add(this);
    window.setSize(500, 500);
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void render(IReadOnlyModel model) throws IllegalArgumentException {
    if(model == null){
      throw new IllegalArgumentException("null model");
    }
    this.shapes = model.getAllShapes();
    int ms = (int) (((1/ ((double) ticksPerSecond)) * 1000) + 0.5);
    this.timer = new Timer(ms, new ActionListener() {
      int tick = 1;
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        renderByTick(tick++, model);
      }
    });
    timer.setRepeats(true);
    timer.start();
  }

  private void renderByTick(int tick, IReadOnlyModel model){
    this.shapes = model.animate(tick);
    repaint();
  }

  // this much casting can't be good
  @Override
  public void paint(Graphics g){
    if(shapes.isEmpty()){
      this.timer.stop();
      return;
    }
    super.paint(g);

    for(IReadOnlyShape s : this.shapes) {
      switch (s.getShapeType()) {
        case ELLIPSE:
          g.setColor(s.getColor());
          g.fillOval((int) s.getX(), (int) s.getY(), (int) s.getWidth(), (int) s.getHeight());
          break;
        case RECTANGLE:
          g.setColor(s.getColor());
          g.fillRect((int) s.getX(), (int) s.getY(), (int) s.getWidth(), (int) s.getHeight());
          System.out.println("red " + s.getColor().getRed()
                  + " green " + s.getColor().getGreen() + " blue " + s.getColor().getBlue());
          break;
        default:
          break;
      }
    }
  }


}
