import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OurModel implements IModel {

  protected Map<String, IShape> shapesMap;
  protected Map<String, List<IMotion>> motionsMap;

  public OurModel() {
    shapesMap = new HashMap<>();
    motionsMap = new HashMap<>();
  }

  @Override
  public boolean addShape(String name, ShapeType type, Point2D pos, int width, int height,
      Color color) {
    if (name == null || type == null || pos == null || pos.getX() < 0 || pos.getY() < 0 || width < 0
        || height < 0) {
      return false;
    }

    IShape shape;
    switch (type) {
      case RECTANGLE:
        break;
      case OVAL:
        break;
      default:
        return false;
    }

    this.shapesMap.put(name, shape);
    return true;
  }

  @Override
  public boolean removeShape(String name) {
    if (shapesMap.containsKey(name)) {
      shapesMap.remove(name);
      motionsMap.remove(name);
      return true;
    }
    return false;
  }

  @Override
  public boolean addMotion(String name, int t0, int t1, Point2D startPos, Point2D endPos,
      int startWidth, int startHeight, int endWidth, int endHeight, Color startColor,
      Color endColor) {
    try {
      IMotion motion = new OurMotion(t0, t1, startPos, endPos, startWidth, startHeight, endWidth,
          endHeight, startColor, endColor);
      return this.addToMotionMap(name, motion);
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  protected boolean addToMotionMap(String name, IMotion motion) {
    // if there is no entry for this name, add one
    if (!this.motionsMap.containsKey(name)) {
      this.motionsMap.put(name, new ArrayList<>());
    }

    // insertion sort
    boolean insert = false;
    int index = 0;
    for (; index < this.motionsMap.get(name).size(); ++index) {
      if ((this.motionsMap.get(name).get(index).getStartTick() <= motion.getStartTick() &&
          this.motionsMap.get(name).get(index).getEndTick() >= motion.getStartTick()) ||
          (this.motionsMap.get(name).get(index).getStartTick() <= motion.getEndTick() &&
              this.motionsMap.get(name).get(index).getEndTick() >= motion.getEndTick())) {
        // overlap
        return false;
      } else if (motion.getStartTick() < this.motionsMap.get(name).get(index).getStartTick()) {
        insert = true;
        break;
      }
    }

    if (insert) {
      this.motionsMap.get(name).add(index, motion);
    } else {
      this.motionsMap.get(name).add(motion);
    }
    return true;
  }

  protected IShape computeShapeAtTick(String id, int tick) {
  }


  @Override
  public List<IShape> animate(int tick) {
    return new ArrayList<>();
  }

  @Override
  public List<String> getShapeNames() {
    return new ArrayList<>(this.shapesMap.keySet());
  }

  @Override
  public String getDescription() {
    StringBuilder builder = new StringBuilder();
    for (Map.Entry<String, IShape> entry : this.shapesMap.entrySet()) {
      builder.append("shape ");
      builder.append(entry.getKey());
      builder.append(" ");
      builder.append(this.getTypeString());
      builder.append("\n");
      for (IMotion motion : this.motionsMap.get(entry.getKey())) {
        builder.append("motion ");
        builder.append(entry.getKey());

        builder.append(motion.getStartTick());
        builder.append(" ");
        builder.append(motion.getInitialPos().getX());
        builder.append(" ");
        builder.append(motion.getInitialPos().getY());
        builder.append(" ");
        builder.append(motion.getInitialWidth());
        builder.append(" ");
        builder.append(motion.getInitialHeight());
        builder.append(" ");
        builder.append(motion.getInitialColor().getRed());
        builder.append(" ");
        builder.append(motion.getInitialColor().getGreen());
        builder.append(" ");
        builder.append(motion.getInitialColor().getBlue());

        builder.append("  ");

        builder.append(motion.getEndTick());
        builder.append(" ");
        builder.append(motion.getFinalPos().getX());
        builder.append(" ");
        builder.append(motion.getFinalPos().getY());
        builder.append(" ");
        builder.append(motion.getFinalWidth());
        builder.append(" ");
        builder.append(motion.getFinalHeight());
        builder.append(" ");
        builder.append(motion.getFinalColor().getRed());
        builder.append(" ");
        builder.append(motion.getFinalColor().getGreen());
        builder.append(" ");
        builder.append(motion.getFinalColor().getBlue());
        builder.append("\n");
      }
      builder.append("\n\n");
    }

    return builder.toString();
  }

  protected String getTypeString(ShapeType type) {
    switch (type) {
      case OVAL:
        return "Oval";
      case RECTANGLE:
        return "Rectangle";
      default:
        return "";
    }
  }
}
