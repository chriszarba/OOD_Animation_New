import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        .addMotion("test_rec", 0, 10, new Point2D.Double(0, 0), new Point2D.Double(10, 10), 20, 25,
            10, 15, Color.RED, Color.BLUE));
    assertEquals(true, model
        .addMotion("test_rec", 11, 20, new Point2D.Double(10, 10), new Point2D.Double(10, 0), 10,
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

  @Test
  /**
   * Test that getDescription() works.
   */
  public void getDescriptionTest1() {
    IModel model = new OurModel();
    Color red = new Color(255, 0, 0);
    Color blue = new Color(0, 0, 255);
    assertEquals(true,
        model.addShape("R", ShapeType.RECTANGLE, new Point2D.Double(200, 200), 50, 100, red));
    assertEquals(true,
        model.addShape("C", ShapeType.ELLIPSE, new Point2D.Double(440, 70), 120, 60, blue));

    assertEquals(true, model
        .addMotion("R", 1, 10, new Point2D.Double(200, 200), new Point2D.Double(200, 200), 50, 100,
            50, 100, red, red));
    assertEquals(true, model
        .addMotion("R", 10, 50, new Point2D.Double(200, 200), new Point2D.Double(300, 300), 50, 100,
            50, 100, red, red));
    assertEquals(true, model
        .addMotion("R", 50, 51, new Point2D.Double(300, 300), new Point2D.Double(300, 300), 50, 100,
            50, 100, red, red));
    assertEquals(true, model
        .addMotion("R", 51, 70, new Point2D.Double(300, 300), new Point2D.Double(300, 300), 50, 100,
            25, 100, red, red));
    assertEquals(true, model
        .addMotion("R", 70, 100, new Point2D.Double(300, 300), new Point2D.Double(200, 200), 25,
            100, 25, 100, red, red));

    assertEquals(true, model
        .addMotion("C", 6, 20, new Point2D.Double(440, 70), new Point2D.Double(440, 70), 120, 60,
            120, 60, blue, blue));
    assertEquals(true, model
        .addMotion("C", 20, 50, new Point2D.Double(440, 70), new Point2D.Double(440, 250), 120, 60,
            120, 60, blue, blue));
    Color lightGreen = new Color(0, 170, 85);
    assertEquals(true, model
        .addMotion("C", 50, 70, new Point2D.Double(440, 250), new Point2D.Double(440, 370), 120, 60,
            120, 60, blue, lightGreen));
    Color green = new Color(0, 255, 0);
    assertEquals(true, model
        .addMotion("C", 70, 80, new Point2D.Double(440, 370), new Point2D.Double(440, 370), 120, 60,
            120, 60, lightGreen, green));
    assertEquals(true, model
        .addMotion("C", 80, 100, new Point2D.Double(440, 370), new Point2D.Double(440, 370), 120,
            60, 120, 60, green, green));

    String expected = "shape R rectangle\n"
        + "motion R 1   200 200 50  100 255 0   0      10  200 200 50  100 255 0   0\n"
        + "motion R 10  200 200 50  100 255 0   0      50  300 300 50  100 255 0   0\n"
        + "motion R 50  300 300 50  100 255 0   0      51  300 300 50  100 255 0   0\n"
        + "motion R 51  300 300 50  100 255 0   0      70  300 300 25  100 255 0   0\n"
        + "motion R 70  300 300 25  100 255 0   0      100 200 200 25  100 255 0   0\n"
        + "\n"
        + "\n"
        + "shape C ellipse\n"
        + "motion C 6   440 70  120 60  0   0   255    20  440 70  120 60  0   0   255\n"
        + "motion C 20  440 70  120 60  0   0   255    50  440 250 120 60  0   0   255\n"
        + "motion C 50  440 250 120 60  0   0   255    70  440 370 120 60  0   170 85\n"
        + "motion C 70  440 370 120 60  0   170 85     80  440 370 120 60  0   255 0\n"
        + "motion C 80  440 370 120 60  0   255 0      100 440 370 120 60  0   255 0";

    assertEquals(expected, model.getDescription());
  }
}
