package cs3500.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Abstract class to represent a shape.
 */
public abstract class AbstractShape implements IShape {

  private String name;
  private Point2D position;
  private Color color;
  private double width;
  private double height;
  private boolean visible;


  protected AbstractShape(String name, double x, double y, Color color, double width, double height,
      boolean visible) {
    if (width < 0 || height < 0 || color == null || name == null) {
      throw new IllegalArgumentException("Invalid argument(s) to create shape.");
    }
    this.name = name;
    this.position = new Point2D.Double(x, y);
    this.color = color;
    this.width = width;
    this.height = height;
    this.visible = visible;
  }

  protected AbstractShape(String name, Point2D pos, Color color, double width, double height,
      boolean visible) {
    if (pos == null || pos.getX() < 0 || pos.getY() < 0 || width < 0 || height < 0
        || color == null || name == null) {
      throw new IllegalArgumentException("Invalid argument(s) to create shape.");
    }
    this.name = name;
    this.position = pos;
    this.color = color;
    this.width = width;
    this.height = height;
    this.visible = visible;
  }

  @Override
  public boolean setX(double x) {
    if (x < 0) {
      return false;
    }
    this.position.setLocation(x, this.position.getY());
    return true;
  }

  @Override
  public boolean setY(double y) {
    if (y < 0) {
      return false;
    }
    this.position.setLocation(this.position.getX(), y);
    return true;
  }

  @Override
  public double getX() {
    return this.position.getX();
  }

  @Override
  public double getY() {
    return this.position.getY();
  }

  @Override
  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  @Override
  public boolean getVisible() {
    return this.visible;
  }

  @Override
  public boolean setColor(Color c) {
    if (c == null) {
      return false;
    }
    this.color = c;
    return true;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public boolean setWidth(double width) {
    if (width < 0) {
      return false;
    }
    this.width = width;
    return true;
  }

  @Override
  public boolean setHeight(double height) {
    if (height < 0) {
      return false;
    }
    this.height = height;
    return true;
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
  public abstract ShapeType getShapeType();

  @Override
  public String getName() {
    return this.name;
  }
}
