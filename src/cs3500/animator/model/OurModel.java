package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of {@link IModel}. Stores the shapes and motions for the animation, is the
 * model of the model-view-controller design scheme.
 */
public class OurModel implements IExtendedModel {

  protected Map<String, IShape> shapesMap;
  //protected Map<String, List<IMotion>> motionsMap;
  protected Map<String, List<IKeyFrame>> keyframeMap;
  protected int canvasWidth;
  protected int canvasHeight;
  protected int boundingX;
  protected int boundingY;

  /**
   * The builder class needed to allow {@link cs3500.animator.util.AnimationReader} to build a model
   * from a input file.
   */
  public static final class Builder implements AnimationBuilder<IModel> {

    private Map<String, String> shapes;
    private Map<String, List<IMotion>> motionMap;
    private Map<String, List<IKeyFrame>> kfMap;
    private int canvasWidth;
    private int canvasHeight;
    private int boundingX;
    private int boundingY;

    @Override
    public IModel build() {
      IExtendedModel model = new OurModel();
      if (this.shapes != null) {
        // add shapes
        for (String name : this.shapes.keySet()) {
          IKeyFrame kf = this.getStartKeyFrame(name);
          if (kf == null) {
            System.out.println("No KeyFrame");
          }
          if (!model
              .addShape(name, this.shapeStringToType(this.shapes.get(name)), kf.getPosition(),
                  kf.getWidth(), kf.getHeight(), kf.getColor())) {
            //System.out.println("Not Inserted");
            return null;
          }
        }
      }

      if(this.motionMap != null){
        // add motions
        for (Map.Entry<String, List<IMotion>> entry : this.motionMap.entrySet()) {
          for (IMotion motion : entry.getValue()) {
            model.addMotion(entry.getKey(), motion.getStartTick(), motion.getEndTick(),
                motion.getInitialPos(), motion.getFinalPos(), motion.getInitialWidth(),
                motion.getInitialHeight(), motion.getFinalWidth(), motion.getFinalHeight(),
                motion.getInitialColor(), motion.getFinalColor());
          }
        }
      }

      if(this.kfMap != null){
          for(Map.Entry<String, List<IKeyFrame>> entry : this.kfMap.entrySet()){
            for(IKeyFrame kf : entry.getValue()){
              //model.addKeyFrame(entry.getKey(), kf.getTick(), kf.getPosition(), kf.getColor(), kf.getWidth(), kf.getHeight());
              model.addKeyFrame(entry.getKey(), kf.getTick());
              model.editKeyFrame(entry.getKey(), kf.getTick(), kf.getPosition(), kf.getColor(), kf.getWidth(), kf.getHeight());
            }
          }
        }

      model.setBoundingX(this.boundingX);
      model.setBoundingY(this.boundingY);
      model.setCanvasHeight(this.canvasHeight);
      model.setCanvasWidth(this.canvasWidth);

      return model;
    }

    /**
     * Gets the keyframe with the lowest starting tick associated with a shape.
     *
     * @param shapeName - the name of the shape.
     * @return - the keyframe with the lowest starting tick associated with the given shape.
     */
    private IKeyFrame getStartKeyFrame(String shapeName) {
      IKeyFrame out = null;
      if(this.motionMap != null && !this.motionMap.get(shapeName).isEmpty()){
        List<IMotion> motionList = this.motionMap.get(shapeName);
        IMotion min = this.motionMap.get(shapeName).get(0);
        for (int i = 0; i < motionList.size(); ++i) {
          if (motionList.get(i).getStartTick() < min.getStartTick()) {
            min = motionList.get(i);
          }
        }
        out = new OurKeyFrame(min.getStartTick(), min.getInitialPos(), min.getInitialColor(), min.getInitialWidth(), min.getInitialHeight());
      }
      if(this.kfMap != null && !this.kfMap.get(shapeName).isEmpty()){
        List<IKeyFrame> kfList = this.kfMap.get(shapeName);
        for(IKeyFrame kf : kfList){
          if(kf.getTick() < out.getTick()){
            out = kf;
          }
        }
      }
      return out;
    }

    /**
     * Small helper to turn a shape string description to the appropriate enum.
     *
     * @param type - a string describing a certain shape.
     * @return - the enum that represents the same shape as the given shape. null if the shape is
     *     not supported.
     */
    private ShapeType shapeStringToType(String type) {
      switch (type) {
        case "ellipse":
          return ShapeType.ELLIPSE;
        case "rectangle":
          return ShapeType.RECTANGLE;
        default:
          return null;
      }
    }

    @Override
    public AnimationBuilder<IModel> setBounds(int x, int y, int width, int height) {
      this.boundingX = x;
      this.boundingY = y;
      this.canvasWidth = width;
      this.canvasHeight = height;
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
              h2, new Color(r1, g1, b1), new Color(r2, g2, b2)));
      return this;
    }

    @Override
    public AnimationBuilder<IModel> addKeyframe(String name, int t, int x, int y, int w, int h,
        int r, int g, int b) {
      if(this.kfMap == null){
        this.kfMap = new LinkedHashMap<>();
      }
      if(!this.kfMap.containsKey(name)){
        this.kfMap.put(name, new ArrayList<>());
      }
      this.kfMap.get(name).add(new OurKeyFrame(t, new Point2D.Double(x, y), new Color(r, g, b), w, h));
      return this;
    }
  }

  /**
   * Constructs a new model.
   */
  public OurModel() {
    shapesMap = new LinkedHashMap<>();
    //motionsMap = new LinkedHashMap<>();
    keyframeMap = new LinkedHashMap<>();
    this.canvasWidth = 1000;
    this.canvasHeight = 1000;
    this.boundingX = Integer.MAX_VALUE;
    this.boundingY = Integer.MAX_VALUE;
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
      //motionsMap.remove(name);
      keyframeMap.remove(name);
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

    /*
    try {
      IMotion motion = new OurMotion(t0, t1, startPos, endPos, startWidth, startHeight, endWidth,
          endHeight, startColor, endColor);
      return this.addToMotionMap(name, motion);
    } catch (IllegalArgumentException e) {
      return false;
    }
     */
    try{
      IKeyFrame startKeyFrame = new OurKeyFrame(t0, startPos, startColor, startWidth, startHeight);
      IKeyFrame endKeyFrame = new OurKeyFrame(t1, endPos, endColor, endWidth, endHeight);
      boolean success = this.addToKeyFrameMap(name, startKeyFrame);
      if(!success){
        return false;
      }
      success = success && this.addToKeyFrameMap(name, endKeyFrame);
      return success;
    }catch (IllegalArgumentException e){
      return false;
    }
  }

  /*
  @Override
  public boolean addKeyFrame(String name, int tick, Point2D pos, Color color, double width, double height){
    if(!this.shapesMap.containsKey(name)){
      return false;
    }
    try {
      IKeyFrame kf = new OurKeyFrame(tick, pos, color, width, height);
      return this.addToKeyFrameMap(name, kf);
    }catch (IllegalArgumentException e){
      return false;
    }
  }
   */

  @Override
  public boolean addKeyFrame(String name, int tick){
    if(!this.shapesMap.containsKey(name) || tick < 0){
      return false;
    }
    // get surrounding key frames
    IKeyFrame start = null;
    IKeyFrame end = null;
    if(this.keyframeMap.get(name) != null) {
      // list is sorted by tick
      for (int i = 0; i < this.keyframeMap.get(name).size(); ++i) {
        IKeyFrame kf = this.keyframeMap.get(name).get(i);
        if(kf.getTick() == tick){
          // already exists
          return true;
        } else if(kf.getTick() < tick){
          start = kf;
        }else if(kf.getTick() > tick){
          end = kf;
          break;
        }
      }
    }

    try {
      if (start == null && end == null) {
        // no other keyframes - new keyframe same as shape
        IShape shape = this.shapesMap.get(name);
        return this.addToKeyFrameMap(name,
            new OurKeyFrame(tick, new Point2D.Double(shape.getX(), shape.getY()),
                shape.getColor(), shape.getWidth(), shape.getHeight()));
      } else if (start == null) {
        IShape shape = this.shapesMap.get(name);
        Point2D tweenPos = new Point2D.Double(
            this.tween(shape.getX(), end.getPosition().getX(), 0, end.getTick(), tick),
            this.tween(shape.getY(), end.getPosition().getY(), 0, end.getTick(), tick));
        Color tweenColor = this
            .tweenColor(shape.getColor(), end.getColor(), 0, end.getTick(), tick);
        return this.addToKeyFrameMap(name, new OurKeyFrame(tick, tweenPos, tweenColor,
            this.tween(shape.getWidth(), end.getWidth(), 0, end.getTick(), tick),
            this.tween(shape.getHeight(), end.getHeight(), 0, end.getTick(), tick)));
      } else if (end == null) {
        // copy start at new tick
        return this.addToKeyFrameMap(name,
            new OurKeyFrame(tick, start.getPosition(), start.getColor(), start.getWidth(),
                start.getHeight()));
      } else {
        // neither are null
        Point2D tweenPos = new Point2D.Double(
            this.tween(start.getPosition().getX(), end.getPosition().getX(), start.getTick(),
                end.getTick(), tick),
            this.tween(start.getPosition().getY(), end.getPosition().getY(), start.getTick(),
                end.getTick(), tick));
        Color tweenColor = this
            .tweenColor(start.getColor(), end.getColor(), start.getTick(), end.getTick(), tick);
        return this.addToKeyFrameMap(name, new OurKeyFrame(tick, tweenPos, tweenColor,
            this.tween(start.getWidth(), end.getWidth(), start.getTick(), end.getTick(), tick),
            this.tween(start.getHeight(), end.getHeight(), start.getTick(), end.getTick(), tick)));
      }
    }catch (IllegalArgumentException e){
      return false;
    }
  }

  @Override
  public boolean removeKeyFrame(String name, int tick) {
    if(!this.shapesMap.containsKey(name)){
      return false;
    }

    int index = 0;
    boolean found = false;
    for(; index < this.keyframeMap.get(name).size(); ++index){
      if(this.keyframeMap.get(name).get(index).getTick() == tick){
        found = true;
        break;
      }
    }
    if(found){
      this.keyframeMap.get(name).remove(index);
      return true;
    }
    return false;
  }

  @Override
  public boolean editKeyFrame(String name, int tick, Point2D pos, Color color, double width,
      double height) {
    if(!this.shapesMap.containsKey(name)){
      return false;
    }
    for(int i = 0; i < this.keyframeMap.get(name).size(); ++i){
      if(this.keyframeMap.get(name).get(i).getTick() == tick) {
        IKeyFrame kf = this.keyframeMap.get(name).get(i);
        Point2D oldPos = null;
        Color oldColor = null;
        double oldWidth = -1;
        double oldHeight = -1;
        try {
          if (!kf.getPosition().equals(pos)) {
            oldPos = kf.getPosition();
            kf.setPosition(pos);
          }
          if (!kf.getColor().equals(color)) {
            oldColor = kf.getColor();
            kf.setColor(color);
          }
          if (Math.abs(kf.getWidth() - width) > 0.01) {
            oldWidth = kf.getWidth();
            kf.setWidth(width);
          }
          if (Math.abs(kf.getHeight() - height) > 0.01) {
            oldHeight = kf.getHeight();
            kf.setHeight(height);
          }
          return true;
        } catch (IllegalArgumentException e) {
          // prevent partial update
          if(oldPos != null){
            kf.setPosition(oldPos);
          }
          if(oldColor != null){
            kf.setColor(oldColor);
          }
          if(oldWidth > 0){
            kf.setWidth(oldWidth);
          }
          if(oldHeight > 0){
            kf.setHeight(oldHeight);
          }
          return false;
        }
      }
    }
    return false;
  }

  @Override
  public boolean editShape(String name, Point2D pos, Color color, double width,
               double height) {
    if(!this.shapesMap.containsKey(name)){
      return false;
    }
    IShape shape = this.shapesMap.get(name);
    shape.setColor(color);
    shape.setHeight(height);
    shape.setWidth(width);
    shape.setX(pos.getX());
    shape.setY(pos.getY());
    return true;
  }

  @Override
  public void setCanvasWidth(int width) {
    if (width > 0) {
      this.canvasWidth = width;
    }
  }

  @Override
  public void setCanvasHeight(int height) {
    if (height > 0) {
      this.canvasHeight = height;
    }
  }

  @Override
  public void setBoundingX(int x) {
    if (x >= 0) {
      this.boundingX = x;
    }
  }

  @Override
  public void setBoundingY(int y) {
    if (y >= 0) {
      this.boundingY = y;
    }
  }

  /*
   * Adds the motion to the motion map, ensuring it does not overlap with any of the other motions
   * associated with the same shape. Keeps the lists of motions associated with each shape sorted
   * from lowest to highest by starting tick.
   *
   * @param name - the name of the shape the motion is associated with
   * @param motion - the motion to add to the map.
   * @return true if the motion is successfully added to the map, false otherwise.
   */
  /*
  protected boolean addToMotionMap(String name, IMotion motion) {
    // if there is no entry for this name, add one
    if (!this.motionsMap.containsKey(name)) {
      this.motionsMap.put(name, new ArrayList<>());
      this.motionsMap.get(name).add(motion);
      return true;
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
          if (this.endStartLineUp(otherMotion, motion)) {
            return false;
          }
        }
        insert = true;
        break;
      }
    }

    if (insert) {
      this.motionsMap.get(name).add(index, motion);
    } else if (motion.getStartTick() == this.motionsMap.get(name)
        .get(this.motionsMap.get(name).size() - 1).getEndTick()) {
      IMotion otherMotion = this.motionsMap.get(name).get(this.motionsMap.get(name).size() - 1);
      if (this.endStartLineUp(otherMotion, motion)) {
        this.motionsMap.get(name).add(motion);
        return true;
      }
      return false;
    }
    return true;
  }
   */

  /**
   * Adds the given keyframe to the map associated with the shape identified by name. Uses
   * insertion sort to keep the list sorted from low to high by tick.
   * @param name - the name of the shape the keyframe is associated with.
   * @param kf - the keyframe to add to the map.
   * @return true if the keyframe was successfully added, false otherwise.
   */
  protected boolean addToKeyFrameMap(String name, IKeyFrame kf){
    if(!this.keyframeMap.containsKey(name)){
      this.keyframeMap.put(name, new ArrayList<>());
      this.keyframeMap.get(name).add(kf);
      return true;
    }

    // insertion sort
    int index = 0;
    for(; index < this.keyframeMap.get(name).size(); ++index){
      if(this.keyframeMap.get(name).get(index).getTick() == kf.getTick()){
        return kf.equals(this.keyframeMap.get(name).get(index));
      }else if(this.keyframeMap.get(name).get(index).getTick() > kf.getTick()){
        break;
      }
    }

    this.keyframeMap.get(name).add(index, kf);
    return true;
  }

  /*
   * Helper function that ensures that the end tick and parameters of the first motion are the same
   * as the starting tick and parameters of the second motion.
   *
   * @param first - the motion which comes directly before the second one.
   * @param second - the motion which comes directly after the first one.
   * @return - true if the first motion has the same ending tick, position, width, height, and color
   *     as the starting tick, position, width, height, and color as the second one. false
   *     otherwise.
   */
  /*
  private boolean endStartLineUp(IMotion first, IMotion second) {
    return first.getEndTick() == second.getStartTick()
        && Math.abs(first.getFinalWidth() - second.getInitialWidth()) < 0.01
        && Math.abs(first.getFinalHeight() - second.getInitialHeight()) < 0.01
        && first.getFinalPos().equals(second.getInitialPos())
        && first.getFinalColor().equals(second.getInitialColor());
  }
   */

  @Override
  public List<IReadOnlyShape> animate(int tick) {
    List<IReadOnlyShape> shapes = new ArrayList<IReadOnlyShape>();
    for (IReadOnlyShape shape : this.getAllShapes()) {
      IReadOnlyShape s = this.getShapeAtTick(shape, tick);
      if (s != null) {
        shapes.add(this.getShapeAtTick(shape, tick));
      }

    }
    return shapes;
  }

  /**
   * Given a shape and tick, responds the corresponding motion object.
   *
   * @param shape the shape whose associated motions need to be searched
   * @param tick - the tick to get the motion at.
   * @return the motion applying to the given shape at the given tick. null if no such motion
   *     exists.
   */
  private IMotion getMotionAtTick(IReadOnlyShape shape, int tick) {
    List<IMotion> motions = this.getShapeMotions(shape.getName());
    for (IMotion m : motions) {
      if (m.getStartTick() <= tick && m.getEndTick() > tick) {
        return m;
      }
    }
    return null;
  }


  /**
   * Given a shape and a tick, calculates what the shape will be at that tick and returns it.
   *
   * @param shape - the shape to calculate the given shape at a given tick at.
   * @param tick - the tick to caculate the given shape at.
   * @return The given shape at the given tick. null if the shape has no associated motions at this
   *     tick.
   */
  private IReadOnlyShape getShapeAtTick(IReadOnlyShape shape, int tick) {
    IMotion motion = this.getMotionAtTick(shape, tick);
    if (motion == null) {
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


  /**
   * tweening function to get the "inbetweens" in the animation.
   *
   * @param start - the start value.
   * @param end - the end value.
   * @param startTick - the start tick.
   * @param endTick - the end tick.
   * @param tick - the current tick.
   * @return the value at the given tick between the start and end values.
   */
  private double tween(double start, double end, double startTick, double endTick, double tick) {
    if (Math.abs(start - end) < 0.001) {
      return start;
    }
    double endResult = start * ((endTick - tick) / (endTick - startTick))
        + end * ((tick - startTick) / (endTick - startTick));
    return endResult;
  }


  /**
   * tweening function for colors.
   *
   * @param start - the starting color.
   * @param end - the ending color.
   * @param startTick - the start tick.
   * @param endTick - the end tick.
   * @param tick - the current tick.
   * @return - the color at the appropriate state of transition at the current tick.
   */
  private Color tweenColor(Color start, Color end, int startTick, int endTick, int tick) {
    if (start.equals(end)) {
      return start;
    }
    int red = start.getRed();
    int green = start.getGreen();
    int blue = start.getBlue();
    int endRed = end.getRed();
    int endGreen = end.getGreen();
    int endBlue = end.getBlue();
    return new Color((int) this.tween(red, endRed, startTick, endTick, tick),
        (int) this.tween(green, endGreen, startTick, endTick, tick),
        (int) this.tween(blue, endBlue, startTick, endTick, tick));
  }

  @Override
  public List<String> getShapeNames() {
    return new ArrayList<>(this.shapesMap.keySet());
  }

  @Override
  public List<IMotion> getShapeMotions(String name) {
    List<IMotion> copy = new ArrayList<>();
    if(!this.shapesMap.containsKey(name)){
      return copy;
    }
    List<IKeyFrame> kfList = this.keyframeMap.get(name);
    for(int i = 0; i < kfList.size() - 1; ++i){
      copy.add(new OurMotion(kfList.get(i), kfList.get(i+1)));
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


  @Override
  public int getCanvasWidth() {
    return this.canvasWidth;
  }

  @Override
  public int getCanvasHeight() {
    return this.canvasHeight;
  }

  @Override
  public int getBoundingX() {
    return this.boundingX;
  }

  @Override
  public int getBoundingY() {
    return this.boundingY;
  }

  @Override
  public int getMaximumTick() {
    int maxTick = 0;
    for(List<IKeyFrame> list : this.keyframeMap.values()){
      for(IKeyFrame kf : list){
        if(kf.getTick() > maxTick){
          maxTick = kf.getTick();
        }
      }
    }
    return maxTick;
  }

  @Override
  public List<IReadOnlyKeyFrame> getShapeKeyFrames(String name) {
    List<IReadOnlyKeyFrame> copy = new ArrayList<>();
    if(!this.shapesMap.containsKey(name)){
      return copy;
    }
    for(IKeyFrame kf : this.keyframeMap.get(name)){
      copy.add(kf);
    }
    return copy;
  }



}
