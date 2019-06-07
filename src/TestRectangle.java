import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

/**
 * Class to test public methods of the Rectangle class.
 */
public class TestRectangle {
  IShape rect1 = new Rectangle(0, 0, Color.BLACK, 0, 0, true);
  IShape rect2 = new Rectangle(1, 1, Color.BLUE, 1, 1, false);

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid Point2D throws
   * IllegalArgumentException.
   */
  public void testNullPos() {
    IShape rect3 = new Rectangle(null, Color.BLACK, 0, 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid Point2D coordinates throws
   * IllegalArgumentException.
   */
  public void testNegativePos() {
    IShape rect3 = new Rectangle(new Point2D.Double(-1, -5), Color.BLACK, 0, 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid Point2D coordinates throws
   * IllegalArgumentException.
   */
  public void testNegativePosX() {
    IShape rect3 = new Rectangle(new Point2D.Double(-1, 0), Color.BLACK, 0, 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid Point2D coordinates throws
   * IllegalArgumentException.
   */
  public void testNegativePosY() {
    IShape rect3 = new Rectangle(new Point2D.Double(0, -5), Color.BLACK, 0, 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with null color throws IllegalArgumentException.
   */
  public void testNullColor() {
    IShape rect3 = new Rectangle(new Point2D.Double(0, 0), null, 0, 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid width throws IllegalArgumentException.
   */
  public void testNegativeWidth() {
    IShape rect3 = new Rectangle(new Point2D.Double(0, 0), Color.BLACK, -1, 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid height throws IllegalArgumentException.
   */
  public void testNegativeHeight() {
    IShape rect3 = new Rectangle(new Point2D.Double(0, 0), Color.BLACK, 0, -1, true);
  }

  @Test
  /**
   * Tests that getShapeType returns a RECTANGLE enum for rectangles.
   */
  public void testGetShapeType() {
    assertEquals(ShapeType.RECTANGLE, rect1.getShapeType());
    assertEquals(ShapeType.RECTANGLE, rect2.getShapeType());
  }

  @Test
  /**
   * Tests that getX returns the correct x coordinate.
   */
  public void testGetX() {
    assertEquals(0, rect1.getX(), 0.1);
    assertEquals(1, rect2.getX(), 0.1);
  }

  @Test
  /**
   * Tests that getY returns the correct y coordinate.
   */
  public void testGetY() {
    assertEquals(0, rect1.getY(), 0.1);
    assertEquals(1, rect2.getY(), 0.1);
  }

  @Test
  /**
   * Tests that getVisible returns the correct boolean visibility.
   */
  public void testGetVisible() {
    assertEquals(false, rect2.getVisible());
    assertEquals(true, rect1.getVisible());
  }

  @Test
  /**
   * Tests that getColor returns the correct Color enum for this shape.
   */
  public void testGetColor() {
    assertEquals(Color.BLACK, rect1.getColor());
    assertEquals(Color.BLUE, rect2.getColor());
  }

  @Test
  /**
   * Tests that getWidth returns the correct width for this shape.
   */
  public void testGetWidth() {
    assertEquals(0, rect1.getWidth(), 0.1);
    assertEquals(1, rect2.getWidth(), 0.1);
  }

  @Test
  /**
   * Tests that getHeight returns the correct height for this shape.
   */
  public void testGetHeight() {
    assertEquals(0, rect1.getHeight(), 0.1);
    assertEquals(1, rect2.getHeight(), 0.1);
  }

  @Test
  /**
   * Tests that setX sets the X coordinate of this shape properly.
   */
  public void testSetX() {
    assertEquals(false, rect1.setX(-1));
    assertEquals(true, rect1.setX(25));
    assertEquals(false, rect2.setX(-10));
    assertEquals(true, rect2.setX(4));
    assertEquals(25, rect1.getX(), 0.1);
    assertEquals(4, rect2.getX(), 0.1);
  }

  @Test
  /**
   * Tests that setY sets the Y coordinate of this shape properly.
   */
  public void testSetY() {
    assertEquals(false, rect1.setY(-1));
    assertEquals(true, rect1.setY(25));
    assertEquals(false, rect2.setY(-10));
    assertEquals(true, rect2.setY(4));
    assertEquals(25, rect1.getY(), 0.1);
    assertEquals(4, rect2.getY(), 0.1);
  }

  @Test
  /**
   * Tests that setVisible sets the visibility of this shape properly.
   */
  public void testSetVisible() {
    rect1.setVisible(false);
    rect2.setVisible(true);
    assertEquals(false, rect1.getVisible());
    assertEquals(true, rect2.getVisible());
  }

  @Test
  /**
   * Tests that setColor sets the correct color of this shape properly.
   */
  public void testSetColor() {
    rect1.setColor(Color.pink);
    rect2.setColor(Color.white);
    assertEquals(Color.pink, rect1.getColor());
    assertEquals(Color.white, rect2.getColor());
  }

  @Test
  /**
   * Tests that setWidth sets the correct width of this shape properly.
   */
  public void testSetWidth() {
    assertEquals(false, rect1.setWidth(-1));
    assertEquals(true, rect1.setWidth(25));
    assertEquals(false, rect2.setWidth(-10));
    assertEquals(true, rect2.setWidth(4));
    assertEquals(25, rect1.getWidth(), 0.1);
    assertEquals(4, rect2.getWidth(), 0.1);
  }

  @Test
  /**
   * Tests that setHeight sets the correct height of this shape properly.
   */
  public void testSetHeight() {
    assertEquals(false, rect1.setHeight(-1));
    assertEquals(true, rect1.setHeight(25));
    assertEquals(false, rect2.setHeight(-10));
    assertEquals(true, rect2.setHeight(4));
    assertEquals(25, rect1.getHeight(), 0.1);
    assertEquals(4, rect2.getHeight(), 0.1);
  }

}
