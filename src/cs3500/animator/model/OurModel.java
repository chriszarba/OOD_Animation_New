package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of {@link IModel}. Stores the shapes and motions for the animation, is the
 * model of the model-view-controller design scheme.
 */
public class OurModel implements IModel {

  protected Map<String, IShape> shapesMap;
  protected Map<String, List<IMotion>> motionsMap;

  public static final class Builder implements AnimationBuilder<IModel> {

    private Map<String, String> shapes;
    private Map<String, List<IMotion>> motionMap;

    @Override
    public IModel build() {
      IModel model = new OurModel();
      // add shapes
      for (String name : this.shapes.keySet()) {
        boolean inserted = false;
        for (IMotion motion : this.motionMap.get(name)) {
          if (motion.getStartTick() == 0) {
            model.addShape(name, this.shapeStringToType(this.shapes.get(name)),
                motion.getInitialPos(), motion.getInitialWidth(), motion.getInitialHeight(),
                motion.getInitialColor());
            break;
          }
        }
        if (!inserted) {
          // TODO
        }
      }

      // add motions
      for (Map.Entry<String, List<IMotion>> entry : this.motionMap.entrySet()) {
        for (IMotion motion : entry.getValue()) {
          model.addMotion(entry.getKey(), motion.getStartTick(), motion.getEndTick(),
              motion.getInitialPos(), motion.getFinalPos(), motion.getInitialWidth(),
              motion.getInitialHeight(), motion.getFinalWidth(), motion.getFinalHeight(),
              motion.getInitialColor(), motion.getFinalColor());
        }
      }

      return model;
    }

    private ShapeType shapeStringToType(String type) {
      switch (type) {
        case "ellipse":
          return ShapeType.ELLIPSE;
        case "rectangle":
          return ShapeType.RECTANGLE;
      }
      // TODO
      return null;
    }

    @Override
    public AnimationBuilder<IModel> setBounds(int x, int y, int width, int height) {
      return this;
    }

    @Override
    public AnimationBuilder<IModel> declareShape(String name, String type) {
      if (this.shapes == null) {
        this.shapes = new HashMap<>();
      }
      this.shapes.put(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<IModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
        int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
      if (this.motionMap == null) {
        this.motionMap = new HashMap<>();
      }
      if (!this.motionMap.containsKey(name)) {
        this.motionMap.put(name, new ArrayList<>());
      }
      this.motionMap.get(name).add(
          new OurMotion(t1, t2, new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), w1, h1, w2,
              h2,
              new Color(r1, g1, b1), new Color(r2, g2, b2)));
      return this;
    }

    @Override
    public AnimationBuilder<IModel> addKeyframe(String name, int t, int x, int y, int w, int h,
        int r, int g, int b) {
      // TODO
      return this;
    }
  }

  /**
   * Constructs a new model.
   */
  public OurModel() {
    shapesMap = new LinkedHashMap<>();
    motionsMap = new LinkedHashMap<>();
  }

  @Override
  public boolean addShape(String name, ShapeType type, Point2D pos, double width, double height,
      Color color) {
    if (name == null || type == null || pos == null || color == null || pos.getX() < 0
        || pos.getY() < 0 || width < 0 || height < 0) {
      return false;
    }

    IShape shape;
    switch (type) {
      case RECTANGLE:
        shape = new Rectangle(name, pos, color, width, height, true);
        break;
      case ELLIPSE:
        shape = new Ellipse(name, pos, color, width, height, true);
        break;
      default:
        return false;
    }

    if (this.shapesMap.containsKey(name)) {
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
      double startWidth, double startHeight, double endWidth, double endHeight, Color startColor,
      Color endColor) {
    if (!this.shapesMap.containsKey(name)) {
      return false;
    }

    try {
      IMotion motion = new OurMotion(t0, t1, startPos, endPos, startWidth, startHeight, endWidth,
          endHeight, startColor, endColor);
      return this.addToMotionMap(name, motion);
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  /**
   * Adds the motion to the motion map, ensuring it does not overlap with any of the other motions
   * associated with the same shape. Keeps the lists of motions associated with each shape sorted
   * from lowest to highest by starting tick.
   *
   * @param name - the name of the shape the motion is associated with
   * @param motion - the motion to add to the map.
   * @return true if the motion is successfully added to the map, false otherwise.
   */
  protected boolean addToMotionMap(String name, IMotion motion) {
    // if there is no entry for this name, add one
    if (!this.motionsMap.containsKey(name)) {
      this.motionsMap.put(name, new ArrayList<>());
    }

    // insertion sort
    boolean insert = false;
    int index = 0;
    for (; index < this.motionsMap.get(name).size(); ++index) {
      if ((this.motionsMap.get(name).get(index).getStartTick() <= motion.getStartTick()
          && this.motionsMap.get(name).get(index).getEndTick() > motion.getStartTick())
          || (this.motionsMap.get(name).get(index).getStartTick() < motion.getEndTick()
          && this.motionsMap.get(name).get(index).getEndTick() >= motion.getEndTick())) {
        // overlap
        return false;
      } else if (motion.getStartTick() < this.motionsMap.get(name).get(index).getStartTick()) {
        if (index - 1 >= 0 && this.motionsMap.get(name).get(index - 1).getEndTick() == motion
            .getStartTick()) {
          // ensure if the endTick is the same as this startTick the parameters are the same
          IMotion otherMotion = this.motionsMap.get(name).get(index - 1);
          if (!motion.getInitialPos().equals(otherMotion.getFinalPos())
              || motion.getInitialWidth() != otherMotion.getFinalWidth()
              || motion.getInitialHeight() != otherMotion.getFinalHeight() || !motion
              .getInitialColor().equals(otherMotion.getFinalColor())) {
            return false;
          }
        }
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

  @Override
  public List<IReadOnlyShape> animate(int tick) {
    List<IReadOnlyShape> shapes = new ArrayList<IReadOnlyShape>();
    for (IReadOnlyShape shape : this.getAllShapes()) {
      IReadOnlyShape s = this.getShapeAtTick(shape, tick);
      if(s != null) {
        shapes.add(this.getShapeAtTick(shape, tick));
      }

    }
    return shapes;
  }

  // Changes below need to be documented
  private IMotion getMotionAtTick(IReadOnlyShape shape, int tick) {
    List<IMotion> motions = this.getShapeMotions(shape.getName());
    for (IMotion m : motions) {
      if (m.getStartTick() <= tick && m.getEndTick() > tick) {
        return m;
      }
    }
    return null; // FIGURE OUT WHAT TO DO ABOUT THIS
  }

  private IReadOnlyShape getShapeAtTick(IReadOnlyShape shape, int tick) {
    IMotion motion = this.getMotionAtTick(shape, tick);
    if(motion == null){
      return null;
    }
    int start = motion.getStartTick();
    int end = motion.getEndTick();
    IReadOnlyShape newShape;
    double x = this.tween(motion.getInitialPos().getX(), motion.getFinalPos().getX(),
        start, end, tick);
    double y = this.tween(motion.getInitialPos().getY(), motion.getFinalPos().getY(),
        start, end, tick);
    double width = this.tween(motion.getInitialWidth(), motion.getFinalWidth(),
        start, end, tick);
    double height = this.tween(motion.getInitialHeight(), motion.getFinalHeight(),
        start, end, tick);
    Color c = this.tweenColor(motion.getInitialColor(), motion.getFinalColor(),
        start, motion.getEndTick(), tick);
    boolean visible = true; // need to do something about this
    switch (shape.getShapeType()) {
      case ELLIPSE:
        return new Ellipse(shape.getName(), x, y, c, width, height, visible);
      case RECTANGLE:
        return new Rectangle(shape.getName(), x, y, c, width, height, visible);
      default:
    }
    return null;
  }

  private double tween(double start, double end, double startTick, double endTick, double tick) {
    double endResult = start * ((endTick - tick) / (endTick - startTick))
        + end * ((tick - startTick) / (endTick - startTick));
    return endResult;
  }


  private Color tweenColor(Color start, Color end, int startTick, int endTick, int tick) {
    int red = start.getRed();
    int green = start.getGreen();
    int blue = start.getBlue();
    int endRed = end.getRed();
    int endGreen = end.getGreen();
    int endBlue = end.getBlue();
    return new Color((int)this.tween(red, endRed, startTick, endTick, tick),
            (int)this.tween(green, endGreen, startTick, endTick, tick),
            (int)this.tween(blue, endBlue, startTick, endTick, tick));
  }

  @Override
  public List<String> getShapeNames() {
    return new ArrayList<>(this.shapesMap.keySet());
  }

  @Override
  public List<IMotion> getShapeMotions(String name) {
    List<IMotion> copy = new ArrayList<>();
    for (IMotion motion : this.motionsMap.get(name)) {
      // IMotions are immutable
      copy.add(motion);
    }
    return copy;
  }

  @Override
  public List<IReadOnlyShape> getAllShapes() {
    List<IReadOnlyShape> copy = new ArrayList<>();
    for (IShape shape : this.shapesMap.values()) {
      // IReadOnlyShape is immutable
      copy.add(shape);
    }
    return copy;
  }

}
