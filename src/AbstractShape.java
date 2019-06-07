import java.awt.*;
import java.awt.geom.Point2D;

public class AbstractShape implements IShape {
  private Point2D position;
  private Color color;
  private double width;
  private double height;
  private boolean visible;


  protected AbstractShape(double x, double y, Color color, double width, double height,
                          boolean visible){
    this.position = new Point2D.Double(x, y);
    this.color = color;
    this.width = width;
    this.height = height;
    this.visible = visible;
  }

  protected AbstractShape(Point2D pos, Color color, double width, double height, boolean visible){
    this.position = pos;
    this.color = color;
    this.width = width;
    this.height = height;
    this.visible = visible;
  }

  @Override
  public void setX(double x) {
    this.position.setLocation(x, this.position.getY());
  }

  @Override
  public void setY(double y) {
    this.position.setLocation(this.position.getX(), y);
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
  public void setColor(Color c) {
    this.color = c;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void setWidth(double width) {
    this.width = width;
  }

  @Override
  public void setHeight(double height) {
    this.height = height;
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
  public ShapeType getShapeType() {
    return null;
  }
}
