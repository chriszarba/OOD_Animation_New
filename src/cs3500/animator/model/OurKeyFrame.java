package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

public class OurKeyFrame implements IKeyFrame{

  private final int tick;
  private final Point2D pos;
  private final Color color;
  private final double width;
  private final double height;

  public OurKeyFrame(int tick, Point2D pos, Color color, double width, double height) throws IllegalArgumentException {
    if(color == null){
      throw new IllegalArgumentException("null color");
    }
    if(pos == null){
      throw new IllegalArgumentException("null pos");
    }
    if(tick < 0){
      throw new IllegalArgumentException("invalid tick");
    }
    if(width <= 0 || height <= 0){
      throw new IllegalArgumentException("invalid dimensions");
    }
    this.tick = tick;
    this.pos = pos;
    this.color = color;
    this.width = width;
    this.height = height;
  }

  @Override
  public int getTick() {
    return this.tick;
  }

  @Override
  public Point2D getPosition() {
    return this.pos;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }
}
