import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.OurMotion;
import java.awt.Color;
import java.awt.geom.Point2D;

import org.junit.Test;

/**
 * Test all public methods in {@link OurMotion}.
 */
public class OurMotionTest {

  @Test(expected = IllegalArgumentException.class)
  /**
   * Test that the constructor throws an exception if the
   * endTick is before the startTick.
   */
  public void constructorTest1() {
    IMotion motion = new OurMotion(10, 0, new Point2D.Double(0, 0), new Point2D.Double(0, 0),
        10, 10, 10, 10, Color.RED, Color.RED);
  }

  @Test
  /**
   * Test that the constructor works if the
   * endTick is the same as the startTick.
   */
  public void constructorTest2() {
    IMotion motion = new OurMotion(0, 0, new Point2D.Double(0, 0), new Point2D.Double(0, 0),
        10, 10, 10, 10, Color.RED, Color.RED);
    assertNotEquals(null, motion);
  }

  @Test
  /**
   * Test that the constructor works if it has a negative starting position.
   */
  public void constructorTest3() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, -10), new Point2D.Double(0, 0),
        10, 10, 10, 10, Color.RED, Color.RED);
    assertNotEquals(null, motion);
  }

  @Test
  /**
   * Test that the constructor works if it has a negative starting position.
   */
  public void constructorTest4() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(-10, 0), new Point2D.Double(0, 0),
        10, 10, 10, 10, Color.RED, Color.RED);
    assertNotEquals(null, motion);
  }

  @Test
  /**
   * Test that the constructor works if it has a negative ending position.
   */
  public void constructorTest5() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(-5, 0),
        10, 10, 10, 10, Color.RED, Color.RED);
    assertNotEquals(null, motion);
  }

  @Test
  /**
   * Test that the constructor works if it has a negative ending position.
   */
  public void constructorTest6() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(0, -6),
        10, 10, 10, 10, Color.RED, Color.RED);
    assertNotEquals(null, motion);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Test that the constructor throws an exception if it has a null starting position.
   */
  public void constructorTest7() {
    IMotion motion = new OurMotion(0, 10, null, new Point2D.Double(0, -6),
        10, 10, 10, 10, Color.RED, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Test that the constructor throws an exception if it has a null ending position.
   */
  public void constructorTest8() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), null,
        10, 10, 10, 10, Color.RED, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Test that the constructor throws an exception if it has a negative starting width.
   */
  public void constructorTest9() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(0, 0),
        -10, 10, 10, 10, Color.RED, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Test that the constructor throws an exception if it has a negative starting height.
   */
  public void constructorTest10() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(0, 0),
        10, -10, 10, 10, Color.RED, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Test that the constructor throws an exception if it has a negative ending width.
   */
  public void constructorTest11() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(0, 0),
        10, 10, -10, 10, Color.RED, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Test that the constructor throws an exception if it has a negative ending height.
   */
  public void constructorTest12() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(0, 0),
        10, 10, 10, -10, Color.RED, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Test that the constructor throws an exception if it has a null starting color.
   */
  public void constructorTest13() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(0, 0),
        10, 10, 10, 10, null, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Test that the constructor throws an exception if it has a null starting color.
   */
  public void constructorTest14() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(0, 0),
        10, 10, 10, 10, Color.RED, null);
  }

  @Test
  /**
   * Test the constructor succeeds with correct arguments.
   */
  public void constructorTest15() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(0, 0),
        10, 10, 10, 10, Color.RED, Color.RED);
    assertNotEquals(null, motion);
  }

  @Test
  /**
   * Test getStartTick() works.
   */
  public void getStartTickTest() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(100, 20),
        10, 15, 20, 25, Color.RED, Color.BLUE);
    assertEquals(0, motion.getStartTick());
  }

  @Test
  /**
   * Test getEndTick() works.
   */
  public void getEndTickTest() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(100, 20),
        10, 15, 20, 25, Color.RED, Color.BLUE);
    assertEquals(10, motion.getEndTick());
  }

  @Test
  /**
   * Test getInitialPos() works.
   */
  public void getInitialPosTest() {
    Point2D.Double input = new Point2D.Double(0, 0);
    IMotion motion = new OurMotion(0, 10, input, new Point2D.Double(100, 20),
        10, 15, 20, 25, Color.RED, Color.BLUE);
    Point2D actual = motion.getInitialPos();
    assertEquals(new Point2D.Double(0, 0), actual);
    assertEquals(false, input == actual);
  }

  @Test
  /**
   * Test getFinalPos() works.
   */
  public void getFinalPosTest() {
    Point2D.Double input = new Point2D.Double(100, 20);
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), input,
        10, 15, 20, 25, Color.RED, Color.BLUE);
    Point2D actual = motion.getFinalPos();
    assertEquals(new Point2D.Double(100, 20), actual);
    assertEquals(false, input == actual);
  }

  @Test
  /**
   * Test getInitialWidth() works.
   */
  public void getInitialWidthTest() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(100, 20),
        10, 15, 20, 25, Color.RED, Color.BLUE);
    assertEquals(10, motion.getInitialWidth(), 0.01);
  }

  @Test
  /**
   * Test getInitialHeight() works.
   */
  public void getInitialHeightTest() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(100, 20),
        10, 15, 20, 25, Color.RED, Color.BLUE);
    assertEquals(15, motion.getInitialHeight(), 0.01);
  }

  @Test
  /**
   * Test getFinalWidth() works.
   */
  public void getFinalWidthTest() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(100, 20),
        10, 15, 20, 25, Color.RED, Color.BLUE);
    assertEquals(20, motion.getFinalWidth(), 0.01);
  }

  @Test
  /**
   * Test getFinalHeight() works.
   */
  public void getFinalHeightTest() {
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(100, 20),
        10, 15, 20, 25, Color.RED, Color.BLUE);
    assertEquals(25, motion.getFinalHeight(), 0.01);
  }

  @Test
  /**
   * Test getInitialColor() works.
   */
  public void getInitialColorTest() {
    Color color = Color.RED;
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(100, 20),
        10, 15, 20, 25, color, Color.BLUE);
    assertEquals(Color.RED, motion.getInitialColor());
    assertEquals(false, color == motion.getInitialColor());
  }

  @Test
  /**
   * Test getFinalColor() works.
   */
  public void getFinalColorTest() {
    Color color = Color.BLUE;
    IMotion motion = new OurMotion(0, 10, new Point2D.Double(0, 0), new Point2D.Double(100, 20),
        10, 15, 20, 25, Color.RED, color);
    assertEquals(Color.BLUE, motion.getFinalColor());
    assertEquals(false, color == motion.getFinalColor());
  }

}
