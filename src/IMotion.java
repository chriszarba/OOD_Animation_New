import java.awt.Color;
import java.awt.geom.Point2D;

public interface IMotion {
  int getStartTick();
  int getEndTick();
  Point2D getInitialPos();
  Point2D getFinalPos();
  int getInitialWidth();
  int getFinalWidth();
  int getInitialHeight();
  int getFinalHeight();
  Color getInitialColor();
  Color getFinalColor();
}
