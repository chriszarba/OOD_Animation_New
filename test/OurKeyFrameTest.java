
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.IKeyFrame;
import cs3500.animator.model.IShape;
import cs3500.animator.model.OurKeyFrame;
import cs3500.animator.model.Rectangle;
import java.awt.Color;
import java.awt.geom.Point2D;
import org.junit.Test;

/** Tests the public methods of {@link cs3500.animator.model.OurKeyFrame}. */
public class OurKeyFrameTest {

  @Test(expected = IllegalArgumentException.class)
  /** Ensure constructor fails if negative tick is given */
  public void constructorTest1() {
    IKeyFrame kf = new OurKeyFrame(-10, new Point2D.Double(0, 0), Color.RED, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  /** Ensure constructor fails if null position is given */
  public void constructorTest2() {
    IKeyFrame kf = new OurKeyFrame(10, null, Color.RED, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  /** Ensure constructor fails if null color is given */
  public void constructorTest3() {
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), null, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  /** Ensure constructor fails if negative width is given */
  public void constructorTest4() {
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, -10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  /** Ensure constructor fails if negative height is given */
  public void constructorTest5() {
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, -10);
  }

  @Test
  /** Ensure constructor succeeds on valid arguments */
  public void constructorTest6() {
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 10);
    assertNotEquals(null, kf);
  }

  @Test
  /** Test get tick works as expected */
  public void getTickTest() {
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 10);
    assertEquals(10, kf.getTick());
  }

  @Test
  /** Test get position works as expected and returns a different Point2D object than constructed with */
  public void getPositionTest() {
    Point2D pos = new Point2D.Double(0, 0);
    IKeyFrame kf = new OurKeyFrame(10, pos, Color.RED, 10, 10);
    assertEquals(pos, kf.getPosition());
    assertFalse(pos == kf.getPosition());
    Point2D returned = kf.getPosition();
    returned.setLocation(2, 2);
    assertEquals(pos, kf.getPosition());
    assertFalse(kf.getPosition() == kf.getPosition());
  }

  @Test
  /** Test get position works as expected and returns a different Color object than constructed with */
  public void getColorTest() {
    Color color = new Color(255, 0, 0);
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), color, 10, 10);
    assertEquals(color, kf.getColor());
    assertFalse(color == kf.getColor());
    Color returned = kf.getColor();
    returned = new Color(0, 0, 255);
    assertEquals(color, kf.getColor());
    assertFalse(kf.getColor() == kf.getColor());
  }

  @Test
  /** Test get width works as expected */
  public void getWidth() {
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    assertEquals(10, kf.getWidth(), 0.01);
  }

  @Test
  /** Test getHeight works as expected */
  public void getHeight() {
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    assertEquals(20, kf.getHeight(), 0.01);
  }

  @Test
  /** Test equals works */
  public void equalsTest1() {
    IKeyFrame kf1 = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    IKeyFrame kf2 = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    assertTrue(kf1.equals(kf2));
  }

  @Test
  /** Test equals works */
  public void equalsTest2() {
    IKeyFrame kf1 = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    IKeyFrame kf2 = new OurKeyFrame(20, new Point2D.Double(0, 0), Color.RED, 10, 20);
    assertFalse(kf1.equals(kf2));
  }

  @Test
  /** Test equals works */
  public void equalsTest3() {
    IKeyFrame kf1 = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    IKeyFrame kf2 = new OurKeyFrame(10, new Point2D.Double(10, 0), Color.RED, 10, 20);
    assertFalse(kf1.equals(kf2));
  }

  @Test
  /** Test equals works */
  public void equalsTest4() {
    IKeyFrame kf1 = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    IKeyFrame kf2 = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.BLUE, 10, 20);
    assertFalse(kf1.equals(kf2));
  }

  @Test
  /** Test equals works */
  public void equalsTest5() {
    IKeyFrame kf1 = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    IKeyFrame kf2 = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 20, 20);
    assertFalse(kf1.equals(kf2));
  }

  @Test
  /** Test equals works */
  public void equalsTest6() {
    IKeyFrame kf1 = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    IKeyFrame kf2 = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 30);
    assertFalse(kf1.equals(kf2));
  }

  @Test
  /** Test equals works */
  public void equalsTest7() {
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    assertFalse(kf.equals(null));
  }

  @Test
  /** Test equals works */
  public void equalsTest8() {
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    IShape rec = new Rectangle("R", 0, 0, Color.RED, 10, 20, true);
    assertFalse(kf.equals(rec));
  }


  @Test
  /** Test setHeight works as expected */
  public void setHeightTest1() {
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    assertEquals(20, kf.getHeight(), 0.01);
    kf.setHeight(30);
    assertEquals(30, kf.getHeight(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  /** Test setHeight works as expected */
  public void setHeightTest2() {
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    assertEquals(20, kf.getHeight(), 0.01);
    kf.setHeight(-30);
  }

  @Test
  /** Test setWidth works as expected */
  public void setWidthTest1() {
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    assertEquals(10, kf.getWidth(), 0.01);
    kf.setWidth(15);
    assertEquals(15, kf.getWidth(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  /** Test setWidth works as expected */
  public void setWidthTest2() {
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), Color.RED, 10, 20);
    assertEquals(10, kf.getWidth(), 0.01);
    kf.setWidth(-15);
  }

  @Test
  /** Test setPosition works as expected */
  public void setPosTest1() {
    Point2D oldPos = new Point2D.Double(0, 0);
    Point2D newPos = new Point2D.Double(10, 10);
    IKeyFrame kf = new OurKeyFrame(10, oldPos, Color.RED, 10, 20);
    assertEquals(oldPos, kf.getPosition());
    kf.setPosition(newPos);
    assertEquals(newPos, kf.getPosition());
    assertFalse(newPos == kf.getPosition());
    kf.getPosition().setLocation(oldPos);
    assertNotEquals(oldPos, kf.getPosition());
  }

  @Test(expected = IllegalArgumentException.class)
  public void setPosTest2() {
    Point2D pos = new Point2D.Double(0, 0);
    IKeyFrame kf = new OurKeyFrame(10, pos, Color.RED, 10, 20);
    assertEquals(pos, kf.getPosition());
    kf.setPosition(null);
  }

  @Test
  /** Test setColor works as expected */
  public void setColorTest1() {
    Color oldColor = new Color(255, 0, 0);
    Color newColor = new Color(0, 0, 255);
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), oldColor, 10, 20);
    assertEquals(oldColor, kf.getColor());
    kf.setColor(newColor);
    assertEquals(newColor, kf.getColor());
    assertFalse(oldColor == kf.getColor());
  }

  @Test(expected = IllegalArgumentException.class)
  /** Test setColor works as expected */
  public void setColorTest2() {
    Color color = new Color(255, 0, 0);
    IKeyFrame kf = new OurKeyFrame(10, new Point2D.Double(0, 0), color, 10, 20);
    assertEquals(color, kf.getColor());
    kf.setColor(null);
  }
}