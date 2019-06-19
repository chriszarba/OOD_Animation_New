package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

public interface IKeyFrame {
  int getTick();
  Point2D getPosition();
  Color getColor();
  double getWidth();
  double getHeight();
}
