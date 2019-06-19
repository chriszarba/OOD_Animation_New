package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

public class OurKeyFrame implements IKeyFrame {

  private int tick;
  private Point2D pos;
  private Color color;
  private double width;
  private double height;

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
    Point2D copyPos = new Point2D.Double(pos.getX(), pos.getY());
    this.pos = copyPos;
    Color copyColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    this.color = copyColor;
    this.width = width;
    this.height = height;
  }

  @Override
  public int getTick() {
    return this.tick;
  }

  @Override
  public Point2D getPosition() {
    Point2D copyPos = new Point2D.Double(this.pos.getX(), this.pos.getY());
    return copyPos;
  }

  @Override
  public Color getColor() {
    Color copyColor = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), this.color.getAlpha());
    return copyColor;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public boolean equals(Object other){
    if(!(other instanceof OurKeyFrame)){
      return false;
    }
    OurKeyFrame otherKF = (OurKeyFrame) other;
    return this.tick == otherKF.tick
        && this.color.equals(otherKF.color)
        && this.pos.equals(otherKF.pos)
        && Math.abs(this.width - otherKF.width) < 0.01
        && Math.abs(this.height - ((OurKeyFrame) other).height) < 0.01;
  }

  /*
  public void setTick(int tick) throws IllegalArgumentException {
    if(tick < 0){
      throw new IllegalArgumentException("Invalid Tick");
    }
    this.tick = tick;
  }
   */

  @Override
  public void setHeight(double height) throws IllegalArgumentException {
    if(height <= 0){
      throw new IllegalArgumentException("invalid dimensions");
    }
    this.height = height;
  }

  @Override
  public void setWidth(double width) throws IllegalArgumentException {
    if(width <= 0){
      throw new IllegalArgumentException("invalid dimensions");
    }
    this.width = width;

  }

  @Override
  public void setPos(Point2D pos) throws IllegalArgumentException {
    if(pos == null){
      throw new IllegalArgumentException("null position");
    }
    Point2D copyPos = new Point2D.Double(pos.getX(), pos.getY());
    this.pos = copyPos;
  }

  @Override
  public void setColor(Color color) throws IllegalArgumentException {
    if(color == null){
      throw new IllegalArgumentException("null color");
    }
    Color copyColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    this.color = copyColor;
  }
}
