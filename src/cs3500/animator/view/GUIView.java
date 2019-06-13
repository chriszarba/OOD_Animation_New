package cs3500.animator.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IReadOnlyShape;
import cs3500.animator.model.IShape;

public class GUIView extends JPanel implements IView {

  //private JButton quitbutton;
  private JFrame frame = new JFrame("Animator");
  private JLabel display;
  private List<IReadOnlyShape> shapes;
  private int ticksPerSecond;

  public GUIView(int ticksPerSecond){
    this.ticksPerSecond = ticksPerSecond;
  }

  @Override
  public void render(IReadOnlyModel model) {
    int ms = (int) (((1/ ((double) ticksPerSecond)) * 1000) + 0.5);
    Timer timer = new Timer(ms, new ActionListener() {
      int tick = 0;
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        renderByTick(tick++, model);
      }
    });
  }

  private void renderByTick(int tick, IReadOnlyModel model){
    this.shapes = model.animate(tick);
    repaint();
  }

  // this much casting can't be good
  @Override
  public void paint(Graphics g){
    for(IReadOnlyShape s : this.shapes) {
      switch (s.getShapeType()) {
        case ELLIPSE:
          g.drawOval((int) s.getX(), (int) s.getY(), (int) s.getWidth(), (int) s.getHeight());
          break;
        case RECTANGLE:
          g.drawRect((int) s.getX(), (int) s.getY(), (int) s.getWidth(), (int) s.getHeight());
          break;
        default:
          break;
      }
    }
  }


}
