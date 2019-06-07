import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

/**
 * Class to test public methods of the Rectangle class.
 */
public class TestEllipse {
  IShape oval1 = new Ellipse(0, 0, Color.BLACK, 0, 0, true);
  IShape oval2 = new Ellipse(1, 1, Color.BLUE, 1, 1, false);

  @Test
  /**
   * Tests that getShapeType returns a OVAL enum for Ellipses.
   */
  public void testGetShapeType() {
    assertEquals(ShapeType.OVAL, oval1.getShapeType());
    assertEquals(ShapeType.OVAL, oval2.getShapeType());
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid Point2D throws
   * IllegalArgumentException.
   */
  public void testNullPos() {
    IShape oval3 = new Ellipse(null, Color.BLACK, 0, 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid Point2D coordinates throws
   * IllegalArgumentException.
   */
  public void testNegativePos() {
    IShape oval3 = new Ellipse(new Point2D.Double(-1, -5), Color.BLACK, 0, 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid Point2D coordinates throws
   * IllegalArgumentException.
   */
  public void testNegativePosX() {
    IShape oval3 = new Ellipse(new Point2D.Double(-1, 0), Color.BLACK, 0, 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid Point2D coordinates throws
   * IllegalArgumentException.
   */
  public void testNegativePosY() {
    IShape oval3 = new Ellipse(new Point2D.Double(0, -5), Color.BLACK, 0, 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with null color throws IllegalArgumentException.
   */
  public void testNullColor() {
    IShape oval3 = new Ellipse(new Point2D.Double(0, 0), null, 0, 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid width throws IllegalArgumentException.
   */
  public void testNegativeWidth() {
    IShape oval3 = new Ellipse(new Point2D.Double(0, 0), Color.BLACK, -1, 0, true);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid height throws IllegalArgumentException.
   */
  public void testNegativeHeight() {
    IShape oval3 = new Ellipse(new Point2D.Double(0, 0), Color.BLACK, 0, -1, true);
  }

  @Test
  /**
   * Tests that getX returns the correct x coordinate.
   */
  public void testGetX() {
    assertEquals(0, oval1.getX(), 0.1);
    assertEquals(1, oval2.getX(), 0.1);
  }

  @Test
  /**
   * Tests that getY returns the correct y coordinate.
   */
  public void testGetY() {
    assertEquals(0, oval1.getY(), 0.1);
    assertEquals(1, oval2.getY(), 0.1);
  }

  @Test
  /**
   * Tests that getVisible returns the correct boolean visibility.
   */
  public void testGetVisible() {
    assertEquals(false, oval2.getVisible());
    assertEquals(true, oval1.getVisible());
  }

  @Test
  /**
   * Tests that getColor returns the correct Color enum for this shape.
   */
  public void testGetColor() {
    assertEquals(Color.BLACK, oval1.getColor());
    assertEquals(Color.BLUE, oval2.getColor());
  }

  @Test
  /**
   * Tests that getWidth returns the correct width for this shape.
   */
  public void testGetWidth() {
    assertEquals(0, oval1.getWidth(), 0.1);
    assertEquals(1, oval2.getWidth(), 0.1);
  }

  @Test
  /**
   * Tests that getHeight returns the correct height for this shape.
   */
  public void testGetHeight() {
    assertEquals(0, oval1.getHeight(), 0.1);
    assertEquals(1, oval2.getHeight(), 0.1);
  }

  @Test
  /**
   * Tests that setX sets the X coordinate of this shape properly.
   */
  public void testSetX() {
    assertEquals(false, oval1.setX(-1));
    assertEquals(true, oval1.setX(25));
    assertEquals(false, oval2.setX(-10));
    assertEquals(true, oval2.setX(4));
    assertEquals(25, oval1.getX(), 0.1);
    assertEquals(4, oval2.getX(), 0.1);
  }

  @Test
  /**
   * Tests that setY sets the Y coordinate of this shape properly.
   */
  public void testSetY() {
    assertEquals(false, oval1.setY(-1));
    assertEquals(true, oval1.setY(25));
    assertEquals(false, oval2.setY(-10));
    assertEquals(true, oval2.setY(4));
    assertEquals(25, oval1.getY(), 0.1);
    assertEquals(4, oval2.getY(), 0.1);
  }

  @Test
  /**
   * Tests that setVisible sets the visibility of this shape properly.
   */
  public void testSetVisible() {
    oval1.setVisible(false);
    oval2.setVisible(true);
    assertEquals(false, oval1.getVisible());
    assertEquals(true, oval2.getVisible());
  }

  @Test
  /**
   * Tests that setColor sets the correct color of this shape properly.
   */
  public void testSetColor() {
    oval1.setColor(Color.pink);
    oval2.setColor(Color.white);
    assertEquals(Color.pink, oval1.getColor());
    assertEquals(Color.white, oval2.getColor());
  }

  @Test
  /**
   * Tests that setWidth sets the correct width of this shape properly.
   */
  public void testSetWidth() {
    assertEquals(false, oval1.setWidth(-1));
    assertEquals(true, oval1.setWidth(25));
    assertEquals(false, oval2.setWidth(-10));
    assertEquals(true, oval2.setWidth(4));
    assertEquals(25, oval1.getWidth(), 0.1);
    assertEquals(4, oval2.getWidth(), 0.1);
  }

  @Test
  /**
   * Tests that setHeight sets the correct height of this shape properly.
   */
  public void testSetHeight() {
    assertEquals(false, oval1.setHeight(-1));
    assertEquals(true, oval1.setHeight(25));
    assertEquals(false, oval2.setHeight(-10));
    assertEquals(true, oval2.setHeight(4));
    assertEquals(25, oval1.getHeight(), 0.1);
    assertEquals(4, oval2.getHeight(), 0.1);
  }

}
