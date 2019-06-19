package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

public interface IKeyFrame extends IReadOnlyKeyFrame {
  //void setTick(int tick) throws IllegalArgumentException;
  void setHeight(double height) throws IllegalArgumentException;
  void setWidth(double width) throws IllegalArgumentException;
  void setPos(Point2D pos) throws IllegalArgumentException;
  void setColor(Color color) throws IllegalArgumentException;
}
