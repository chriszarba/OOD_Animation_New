package cs3500.animator.view;

import java.awt.Graphics;
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

  public GUIView(){
  }

  @Override
  public void render(IReadOnlyModel model) {
    // not sure how this works with the ticks
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
