import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IShape;
import cs3500.animator.model.ShapeType;
import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;

// add tests for name
import static org.junit.Assert.assertEquals;

/**
 * Class to test public methods of the cs3500.animator.model.Rectangle class.
 */
public class TestEllipse {

  IShape ellipse1 = new Ellipse("ellipse1", 0, 0, Color.BLACK, 0, 0, true);
  IShape ellipse2 = new Ellipse("ellipse2", 1, 1, Color.BLUE, 1, 1, false);

  @Test
  /**
   * Test that getName() returns the right name.
   */
  public void testGetName(){
    assertEquals("ellipse1", ellipse1.getName());
    assertEquals("ellipse2", ellipse2.getName());
  }

  @Test
  /**
   * Tests that getShapeType returns a ELLIPSE enum for Ellipses.
   */
  public void testGetShapeType() {
    assertEquals(ShapeType.ELLIPSE, ellipse1.getShapeType());
    assertEquals(ShapeType.ELLIPSE, ellipse2.getShapeType());
  }


  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid Point2D throws
   * IllegalArgumentException.
   */
  public void testNullPos() {
    IShape ellipse3 = new Ellipse("ellipse3", null, Color.BLACK, 0, 0, true);
  }


  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid Point2D coordinates throws
   * IllegalArgumentException.
   */

  public void testNegativePos() {
    IShape ellipse3 = new Ellipse("ellipse3",new Point2D.Double(-1, -5), Color.BLACK, 0, 0, true);
  }


  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid Point2D coordinates throws
   * IllegalArgumentException.
   */
  public void testNegativePosX() {
    IShape ellipse3 = new Ellipse("ellipse", new Point2D.Double(-1, 0), Color.BLACK, 0, 0, true);
  }


  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid Point2D coordinates throws
   * IllegalArgumentException.
   */

  public void testNegativePosY() {
    IShape ellipse3 = new Ellipse("ellipse", new Point2D.Double(0, -5), Color.BLACK, 0, 0, true);
  }


  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with null color throws IllegalArgumentException.
   */

  public void testNullColor() {
    IShape ellipse3 = new Ellipse("ellipse", new Point2D.Double(0, 0), null, 0, 0, true);
  }


  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid width throws IllegalArgumentException.
   */
  public void testNegativeWidth() {
    IShape ellipse3 = new Ellipse("ellipse", new Point2D.Double(0, 0), Color.BLACK, -1, 0, true);
  }


  @Test(expected = IllegalArgumentException.class)
  /**
   * Tests that attempt to construct a shape with invalid height throws IllegalArgumentException.
   */
  public void testNegativeHeight() {
    IShape ellipse3 = new Ellipse("ellipse", new Point2D.Double(0, 0), Color.BLACK, 0, -1, true);
  }


  @Test
  /**
   * Tests that getX returns the correct x coordinate.
   */
  public void testGetX() {
    assertEquals(0, ellipse1.getX(), 0.1);
    assertEquals(1, ellipse2.getX(), 0.1);
  }

  @Test
  /**
   * Tests that getY returns the correct y coordinate.
   */
  public void testGetY() {
    assertEquals(0, ellipse1.getY(), 0.1);
    assertEquals(1, ellipse2.getY(), 0.1);
  }


  @Test
  /**
   * Tests that getVisible returns the correct boolean visibility.
   */
  public void testGetVisible() {
    assertEquals(false, ellipse2.getVisible());
    assertEquals(true, ellipse1.getVisible());
  }

  @Test
  /**
   * Tests that getColor returns the correct Color enum for this shape.
   */
  public void testGetColor() {
    assertEquals(Color.BLACK, ellipse1.getColor());
    assertEquals(Color.BLUE, ellipse2.getColor());
  }

  @Test
  /**
   * Tests that getWidth returns the correct width for this shape.
   */
  public void testGetWidth() {
    assertEquals(0, ellipse1.getWidth(), 0.1);
    assertEquals(1, ellipse2.getWidth(), 0.1);
  }

  @Test
  /**
   * Tests that getHeight returns the correct height for this shape.
   */

  public void testGetHeight() {
    assertEquals(0, ellipse1.getHeight(), 0.1);
    assertEquals(1, ellipse2.getHeight(), 0.1);
  }


  @Test
  /**
   * Tests that setX sets the X coordinate of this shape properly.
   */
  public void testSetX() {
    assertEquals(false, ellipse1.setX(-1));
    assertEquals(true, ellipse1.setX(25));
    assertEquals(false, ellipse2.setX(-10));
    assertEquals(true, ellipse2.setX(4));
    assertEquals(25, ellipse1.getX(), 0.1);
    assertEquals(4, ellipse2.getX(), 0.1);
  }


  @Test
  /**
   * Tests that setY sets the Y coordinate of this shape properly.
   */
  public void testSetY() {
    assertEquals(false, ellipse1.setY(-1));
    assertEquals(true, ellipse1.setY(25));
    assertEquals(false, ellipse2.setY(-10));
    assertEquals(true, ellipse2.setY(4));
    assertEquals(25, ellipse1.getY(), 0.1);
    assertEquals(4, ellipse2.getY(), 0.1);
  }

  @Test
  /**
   * Tests that setVisible sets the visibility of this shape properly.
   */
  public void testSetVisible() {
    ellipse1.setVisible(false);
    ellipse2.setVisible(true);
    assertEquals(false, ellipse1.getVisible());
    assertEquals(true, ellipse2.getVisible());
  }


  @Test
  /**
   * Tests that setColor sets the correct color of this shape properly.
   */
  public void testSetColor() {
    ellipse1.setColor(Color.pink);
    ellipse2.setColor(Color.white);
    assertEquals(Color.pink, ellipse1.getColor());
    assertEquals(Color.white, ellipse2.getColor());
  }


  @Test
  /**
   * Tests that setWidth sets the correct width of this shape properly.
   */
  public void testSetWidth() {
    assertEquals(false, ellipse1.setWidth(-1));
    assertEquals(true, ellipse1.setWidth(25));
    assertEquals(false, ellipse2.setWidth(-10));
    assertEquals(true, ellipse2.setWidth(4));
    assertEquals(25, ellipse1.getWidth(), 0.1);
    assertEquals(4, ellipse2.getWidth(), 0.1);
  }

  @Test
  /**
   * Tests that setHeight sets the correct height of this shape properly.
   */
  public void testSetHeight() {
    assertEquals(false, ellipse1.setHeight(-1));
    assertEquals(true, ellipse1.setHeight(25));
    assertEquals(false, ellipse2.setHeight(-10));
    assertEquals(true, ellipse2.setHeight(4));
    assertEquals(25, ellipse1.getHeight(), 0.1);
    assertEquals(4, ellipse2.getHeight(), 0.1);
  }

}
