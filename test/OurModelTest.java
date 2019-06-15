import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.animator.model.IModel;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyShape;
import cs3500.animator.model.IShape;
import cs3500.animator.model.OurModel;
import cs3500.animator.model.OurMotion;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ShapeType;
import cs3500.animator.util.AnimationBuilder;
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
  /** Test that addMotion() suceeds if the initial position is negative. */
  public void addMotionTest2() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(true, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(-10, 0), new Point2D.Double(10, 10), 20,
            25, 10, 15, Color.RED, Color.BLUE));
  }

  @Test
  /** Test that addMotion() succeeds if the initial position is negative. */
  public void addMotionTest3() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(true, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(0, -5), new Point2D.Double(10, 10), 20, 25,
            10, 15, Color.RED, Color.BLUE));
  }

  @Test
  /** Test that addMotion() succeeds if the final position is negative. */
  public void addMotionTest4() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(true, model
        .addMotion("test_rec", 0, 10, new Point2D.Double(0, 0), new Point2D.Double(-10, 10), 20, 25,
            10, 15, Color.RED, Color.BLUE));
  }

  @Test
  /** Test that addMotion() succeeds if the final position is negative. */
  public void addMotionTest5() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));

    assertEquals(true, model
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

  @Test
  /**
   * Quick test of the getter and setter for the bounding X value.
   */
  public void setGetXTest() {
    IModel model = new OurModel();
    model.setBoundingX(30);
    assertEquals(30, model.getBoundingX());
    model.setBoundingX(-30);
    assertEquals(30, model.getBoundingX());
  }

  @Test
  /**
   * Quick test of the getter and setter for the bounding Y value.
   */
  public void setGetYTest() {
    IModel model = new OurModel();
    model.setBoundingY(30);
    assertEquals(30, model.getBoundingY());
    model.setBoundingY(-100);
    assertEquals(30, model.getBoundingY());
  }

  @Test
  /**
   * Quick test of the getter and setter for the canvas width.
   */
  public void setGetCanvasWidthTest() {
    IModel model = new OurModel();
    model.setCanvasWidth(300);
    assertEquals(300, model.getCanvasWidth());
    model.setCanvasWidth(-240);
    assertEquals(300, model.getCanvasWidth());
  }

  @Test
  /**
   * Quick test of the getter and setter for the canvas height.
   */
  public void setGetCanvasHeightTest() {
    IModel model = new OurModel();
    model.setCanvasHeight(300);
    assertEquals(300, model.getCanvasHeight());
    model.setCanvasHeight(-300);
    assertEquals(300, model.getCanvasHeight());
  }

  @Test
  /**
   * Test getShapeMotions works as expected.
   */
  public void testGetShapeMotions() {
    IModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertEquals(true, model
        .addMotion("test_rec", 1, 10, new Point2D.Double(0, 0), new Point2D.Double(10, 10), 20, 25,
            10, 15, Color.RED, Color.BLUE));
    assertEquals(true, model
        .addMotion("test_rec", 10, 20, new Point2D.Double(10, 10), new Point2D.Double(10, 0), 10,
            15, 30, 35, Color.BLUE, Color.GREEN));
    List<IMotion> motions = model.getShapeMotions("test_rec");
    assertEquals(2, motions.size());
    // first motion same
    assertEquals(1, motions.get(0).getStartTick());
    assertEquals(10, motions.get(0).getEndTick());
    assertEquals(true, motions.get(0).getInitialPos().equals(new Point2D.Double(0, 0)));
    assertEquals(true, motions.get(0).getFinalPos().equals(new Point2D.Double(10, 10)));
    assertEquals(20, motions.get(0).getInitialWidth(), 0.01);
    assertEquals(25, motions.get(0).getInitialHeight(), 0.01);
    assertEquals(10, motions.get(0).getFinalWidth(), 0.01);
    assertEquals(15, motions.get(0).getFinalHeight(), 0.01);
    assertEquals(true, motions.get(0).getInitialColor().equals(Color.RED));
    assertEquals(true, motions.get(0).getFinalColor().equals(Color.BLUE));
    // second motion same
    assertEquals(10, motions.get(1).getStartTick());
    assertEquals(20, motions.get(1).getEndTick());
    assertEquals(true, motions.get(1).getInitialPos().equals(new Point2D.Double(10, 10)));
    assertEquals(true, motions.get(1).getFinalPos().equals(new Point2D.Double(10, 0)));
    assertEquals(10, motions.get(1).getInitialWidth(), 0.01);
    assertEquals(15, motions.get(1).getInitialHeight(), 0.01);
    assertEquals(30, motions.get(1).getFinalWidth(), 0.01);
    assertEquals(35, motions.get(1).getFinalHeight(), 0.01);
    assertEquals(true, motions.get(1).getInitialColor().equals(Color.BLUE));
    assertEquals(true, motions.get(1).getFinalColor().equals(Color.GREEN));

    // non-existent shape gets empty list
    assertEquals(0, model.getShapeMotions("bad_name").size());
  }

  /** Test public methods of {@link OurModel.Builder}. */
    @Test
    /** Test constructor suceeds */
    public void constructorTest1() {
      AnimationBuilder builder = new OurModel.Builder();
      assertNotEquals(null, builder);
    }

    @Test
    /** Test setBounds sets the bounds in the constructed model properly */
    public void setBoundsTest1() {
      AnimationBuilder<IModel> builder = new OurModel.Builder();
      builder.setBounds(10, 15, 400, 500);
      IModel model = builder.build();
      assertNotEquals(null, model);
      assertEquals(10, model.getBoundingX());
      assertEquals(15, model.getBoundingY());
      assertEquals(400, model.getCanvasWidth());
      assertEquals(500, model.getCanvasHeight());
    }

    @Test
    /** Test adding a shape and motion (and you must add at least 1 motion per shape) succeeds in
     * creating a model with said shape and motion */
    public void declareShapeAddMotionTest1(){
      AnimationBuilder<IModel> builder = new OurModel.Builder();
      builder.declareShape("R", "rectangle");
      builder.addMotion("R", 0, 5, 10, 100, 200, 255, 0, 0, 10, 10, 15, 150, 250, 0, 0, 255);
      IModel model = builder.build();
      assertNotEquals(null, model);
      IShape expectedShape = new Rectangle("R", new Point2D.Double(5, 10), new Color(255, 0, 0), 100, 200, true);
      IReadOnlyShape actualShape = model.getAllShapes().get(0);
      assertEquals(expectedShape.getShapeType(), actualShape.getShapeType());
      assertEquals(expectedShape.getName(), actualShape.getName());
      assertEquals(expectedShape.getX(), actualShape.getX(), 0.01);
      assertEquals(expectedShape.getY(), actualShape.getY(), 0.01);
      assertEquals(expectedShape.getColor(), actualShape.getColor());
      assertEquals(expectedShape.getWidth(), actualShape.getWidth(), 0.01);
      assertEquals(expectedShape.getHeight(), actualShape.getHeight(), 0.01);
      assertEquals(expectedShape.getVisible(), actualShape.getVisible());

      IMotion expectedMotion = new OurMotion(0, 10, new Point2D.Double(5, 10), new Point2D.Double(10, 15), 100, 200, 150, 250, new Color(255, 0, 0), new Color(0, 0, 255));
      IMotion actualMotion = model.getShapeMotions("R").get(0);
      assertEquals(expectedMotion.getStartTick(), actualMotion.getStartTick());
      assertEquals(expectedMotion.getEndTick(), actualMotion.getEndTick());
      assertEquals(expectedMotion.getInitialPos(), actualMotion.getInitialPos());
      assertEquals(expectedMotion.getInitialColor(), actualMotion.getInitialColor());
      assertEquals(expectedMotion.getInitialWidth(), actualMotion.getInitialWidth(), 0.01);
      assertEquals(expectedMotion.getInitialHeight(), actualMotion.getInitialHeight(), 0.01);
      assertEquals(expectedMotion.getFinalPos(), actualMotion.getFinalPos());
      assertEquals(expectedMotion.getFinalColor(), actualMotion.getFinalColor());
      assertEquals(expectedMotion.getFinalWidth(), actualMotion.getFinalWidth(), 0.01);
      assertEquals(expectedMotion.getFinalHeight(), actualMotion.getFinalHeight(), 0.01);
    }

}
