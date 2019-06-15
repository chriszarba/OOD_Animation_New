import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.animator.model.IModel;
import cs3500.animator.model.OurModel;
import cs3500.animator.model.ShapeType;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Tests the public methods in {@link OurModel}.
 */
public class OurModelTest {

  @Test
  /** Test constructor succeeds. */
  public void constructorTest() {
    assertNotEquals(null, new OurModel());
  }

  @Test
  /** Test that it fails to add a shape if it has a negative position. */
  public void addShapeTest1() {
    IModel model = new OurModel();
    assertEquals(false, model
        .addShape("test_shape", ShapeType.RECTANGLE, new Point2D.Double(-10, 0), 10, 20,
            Color.RED));
  }

  @Test
  /** Test that it fails to add a shape if it has a negative position. */
  public void addShapeTest2() {
    IModel model = new OurModel();
    assertEquals(false, model
        .addShape("test_shape", ShapeType.RECTANGLE, new Point2D.Double(0, -5), 10, 20, Color.RED));
  }

  @Test
  /** Test that it fails to add a shape if it has a null position. */
  public void addShapeTest3() {
    IModel model = new OurModel();
    assertEquals(false, model.addShape("test_shape", ShapeType.RECTANGLE, null, 10, 20, Color.RED));
  }

  @Test
  /** Test that it fails to add a shape if it has a negative width. */
  public void addShapeTest4() {
    IModel model = new OurModel();
    assertEquals(false, model
        .addShape("test_shape", ShapeType.RECTANGLE, new Point2D.Double(0, 0), -10, 20, Color.RED));
  }

  @Test
  /** Test that it fails to add a shape if it has a negative height. */
  public void addShapeTest5() {
    IModel model = new OurModel();
    assertEquals(false, model
        .addShape("test_shape", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, -20, Color.RED));
  }

  @Test
  /** Test that it fails to add a shape if it has a null color. */
  public void addShapeTest6() {
    IModel model = new OurModel();
    assertEquals(false,
        model.addShape("test_shape", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, -20, null));
  }

  @Test
  /** Test that it succeeds in adding a shape. */
  public void addShapeTest7() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_shape", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertEquals(true, model.getShapeNames().contains("test_shape"));
  }

  @Test
  /** Test that getShapeNames() works. */
  public void getShapeNamesTest() {
    IModel model = new OurModel();
    List<String> expected = new ArrayList<>(2);
    expected.add("test_rec");
    expected.add("test_ellipse");

    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertEquals(true, model
        .addShape("test_ellipse", ShapeType.ELLIPSE, new Point2D.Double(100, 100), 5, 10,
            Color.RED));
    assertEquals(expected, model.getShapeNames());
  }

  @Test
  /** Test that addMotion() fails if the name isn't associated with a shape. */
  public void addMotionTest1() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(false, model
        .addMotion("test_shape", 0, 10, new Point2D.Double(0, 0), new Point2D.Double(10, 10), 20,
            25, 10, 15, Color.RED, Color.BLUE));
  }

  @Test
  /** Test that addMotion() fails if the initial position is negative. */
  public void addMotionTest2() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(false, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(-10, 0), new Point2D.Double(10, 10), 20,
            25, 10, 15, Color.RED, Color.BLUE));
  }

  @Test
  /** Test that addMotion() fails if the initial position is negative. */
  public void addMotionTest3() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(false, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(0, -5), new Point2D.Double(10, 10), 20, 25,
            10, 15, Color.RED, Color.BLUE));
  }

  @Test
  /** Test that addMotion() fails if the final position is negative. */
  public void addMotionTest4() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(false, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(0, 0), new Point2D.Double(-10, 10), 20, 25,
            10, 15, Color.RED, Color.BLUE));
  }

  @Test
  /** Test that addMotion() fails if the final position is negative. */
  public void addMotionTest5() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(false, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(0, 0), new Point2D.Double(10, -10), 20, 25,
            10, 15, Color.RED, Color.BLUE));
  }

  @Test
  /** Test that addMotion() fails if the starting width is negative. */
  public void addMotionTest6() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(false, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(0, 0), new Point2D.Double(10, 10), -20, 25,
            10, 15, Color.RED, Color.BLUE));
  }

  @Test
  /** Test that addMotion() fails if the starting height is negative. */
  public void addMotionTest7() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(false, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(0, 0), new Point2D.Double(10, 10), 20, -25,
            10, 15, Color.RED, Color.BLUE));
  }

  @Test
  /** Test that addMotion() fails if the ending width is negative. */
  public void addMotionTest8() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(false, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(0, 0), new Point2D.Double(10, 10), 20, 25,
            -10, 15, Color.RED, Color.BLUE));
  }

  @Test
  /** Test that addMotion() fails if the ending height is negative. */
  public void addMotionTest9() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(false, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(0, 0), new Point2D.Double(10, 10), 20, 25,
            10, -15, Color.RED, Color.BLUE));
  }

  @Test
  /** Test that addMotion() fails if the starting color is null. */
  public void addMotionTest10() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(false, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(0, 0), new Point2D.Double(10, 10), 20, 25,
            10, 15, null, Color.BLUE));
  }

  @Test
  /** Test that addMotion() fails if the ending color is null. */
  public void addMotionTest11() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(false, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(0, 0), new Point2D.Double(10, 10), 20, 25,
            10, 15, Color.RED, null));
  }

  @Test
  /** Test that addMotion() succeeds. */
  public void addMotionTest12() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(true, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(0, 0), new Point2D.Double(10, 10), 20, 25,
            10, 15, Color.RED, Color.BLUE));
  }

  @Test
  /** Test that addMotion() fails if motions overlap. */
  public void addMotionTest13() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(true, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(0, 0), new Point2D.Double(10, 10), 20, 25,
            10, 15, Color.RED, Color.BLUE));
    assertEquals(false, model
        .addMotion("test_rec", 5, 15, new Point2D.Double(10, 10), new Point2D.Double(10, 0), 10, 15,
            30, 35, Color.BLUE, Color.GREEN));
  }

  @Test
  /** Test that addMotion() succeeds. */
  public void addMotionTest14() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(true, model
        .addMotion("test_rec", 1, 10, new Point2D.Double(0, 0), new Point2D.Double(10, 10), 20, 25,
            10, 15, Color.RED, Color.BLUE));
    assertEquals(true, model
        .addMotion("test_rec", 10, 20, new Point2D.Double(10, 10), new Point2D.Double(10, 0), 10,
            15, 30, 35, Color.BLUE, Color.GREEN));
  }

  @Test
  /**
   * Test that removeShape() succeeds.
   */
  public void removeShapeTest1() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertEquals(true, model
        .addShape("test_ellipse", ShapeType.ELLIPSE, new Point2D.Double(100, 100), 5, 10,
            Color.RED));

    List<String> expected = new ArrayList<>(2);
    expected.add("test_rec");
    expected.add("test_ellipse");

    assertEquals(expected, model.getShapeNames());

    assertEquals(true, model.removeShape("test_ellipse"));
    expected.remove(1);

    assertEquals(expected, model.getShapeNames());
  }

  @Test
  /**
   * Test that removeShape() fails if name isn't associated with any shapes.
   */
  public void removeShapeTest2() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertEquals(true, model
        .addShape("test_ellipse", ShapeType.ELLIPSE, new Point2D.Double(100, 100), 5, 10,
            Color.RED));

    List<String> expected = new ArrayList<>(2);
    expected.add("test_rec");
    expected.add("test_ellipse");

    assertEquals(expected, model.getShapeNames());

    assertEquals(false, model.removeShape("test_shape"));

    assertEquals(expected, model.getShapeNames());
  }


}
