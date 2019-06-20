import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.IExtendedModel;
import cs3500.animator.model.IKeyFrame;
import cs3500.animator.model.IModel;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyKeyFrame;
import cs3500.animator.model.IReadOnlyShape;
import cs3500.animator.model.IShape;
import cs3500.animator.model.OurModel;
import cs3500.animator.model.OurMotion;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ShapeType;
import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

  //@Test
  /** Test that addMotion() fails if motions overlap. */
  /*
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
   */

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

  /**
   * Test public methods of {@link OurModel.Builder}.
   */
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
  public void declareShapeAddMotionTest1() {
    AnimationBuilder<IModel> builder = new OurModel.Builder();
    builder.declareShape("R", "rectangle");
    builder.addMotion("R", 0, 5, 10, 100, 200, 255, 0, 0, 10, 10, 15, 150, 250, 0, 0, 255);
    IModel model = builder.build();
    assertNotEquals(null, model);
    IShape expectedShape = new Rectangle("R", new Point2D.Double(5, 10), new Color(255, 0, 0), 100,
        200, true);
    IReadOnlyShape actualShape = model.getAllShapes().get(0);
    assertEquals(expectedShape.getShapeType(), actualShape.getShapeType());
    assertEquals(expectedShape.getName(), actualShape.getName());
    assertEquals(expectedShape.getX(), actualShape.getX(), 0.01);
    assertEquals(expectedShape.getY(), actualShape.getY(), 0.01);
    assertEquals(expectedShape.getColor(), actualShape.getColor());
    assertEquals(expectedShape.getWidth(), actualShape.getWidth(), 0.01);
    assertEquals(expectedShape.getHeight(), actualShape.getHeight(), 0.01);
    assertEquals(expectedShape.getVisible(), actualShape.getVisible());

    IMotion expectedMotion = new OurMotion(0, 10, new Point2D.Double(5, 10),
        new Point2D.Double(10, 15), 100, 200, 150, 250, new Color(255, 0, 0), new Color(0, 0, 255));
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

  @Test
  /** Test adding a keyframe to a shape with no keyframes results in the expected keyframe */
  public void addKeyFrameTest1() {
      IExtendedModel model = new OurModel();
      assertEquals(true, model
          .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
      assertEquals(true, model.addKeyFrame("test_rec", 10));

      List<IReadOnlyKeyFrame> kfList = model.getShapeKeyFrames("test_rec");
      assertEquals(1, kfList.size());
      assertEquals(10, kfList.get(0).getTick());
      assertTrue( new Point2D.Double(0,0).equals(kfList.get(0).getPosition()));
      assertTrue(Color.RED.equals(kfList.get(0).getColor()));
      assertEquals(10, kfList.get(0).getWidth(), 0.01);
    assertEquals(20, kfList.get(0).getHeight(), 0.01);
  }

  @Test
  /** Test editing keyframe works as expected */
  public void editKeyFrameTest1() {
    IExtendedModel model = new OurModel();
    assertTrue( model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertTrue( model.addKeyFrame("test_rec", 10));
    assertTrue(model.editKeyFrame("test_rec", 10, new Point2D.Double(10, 10), Color.BLUE, 20, 40));

    List<IReadOnlyKeyFrame> kfList = model.getShapeKeyFrames("test_rec");
    assertEquals(1, kfList.size());
    assertEquals(10, kfList.get(0).getTick());
    assertTrue( new Point2D.Double(10,10).equals(kfList.get(0).getPosition()));
    assertTrue(Color.BLUE.equals(kfList.get(0).getColor()));
    assertEquals(20, kfList.get(0).getWidth(), 0.01);
    assertEquals(40, kfList.get(0).getHeight(), 0.01);
  }

  @Test
  /** Test editing keyframe fails if there is invalid width */
  public void editKeyFrameTest2() {
    IExtendedModel model = new OurModel();
    assertTrue( model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertTrue( model.addKeyFrame("test_rec", 10));
    assertFalse(model.editKeyFrame("test_rec", 10, new Point2D.Double(10, 10), Color.BLUE, -20, 40));

    List<IReadOnlyKeyFrame> kfList = model.getShapeKeyFrames("test_rec");
    assertEquals(1, kfList.size());
    assertEquals(10, kfList.get(0).getTick());
    assertEquals( new Point2D.Double(0,0), kfList.get(0).getPosition());
    assertTrue(Color.RED.equals(kfList.get(0).getColor()));
    assertEquals(10, kfList.get(0).getWidth(), 0.01);
    assertEquals(20, kfList.get(0).getHeight(), 0.01);
  }

  @Test
  /** Test editing keyframe fails if there is invalid height */
  public void editKeyFrameTest3() {
    IExtendedModel model = new OurModel();
    assertTrue( model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertTrue( model.addKeyFrame("test_rec", 10));
    assertFalse(model.editKeyFrame("test_rec", 10, new Point2D.Double(10, 10), Color.BLUE, 20, -40));

    List<IReadOnlyKeyFrame> kfList = model.getShapeKeyFrames("test_rec");
    assertEquals(1, kfList.size());
    assertEquals(10, kfList.get(0).getTick());
    assertEquals( new Point2D.Double(0,0), kfList.get(0).getPosition());
    assertTrue(Color.RED.equals(kfList.get(0).getColor()));
    assertEquals(10, kfList.get(0).getWidth(), 0.01);
    assertEquals(20, kfList.get(0).getHeight(), 0.01);
  }

  @Test
  /** Test editing keyframe fails if it is invalid id */
  public void editKeyFrameTest4() {
    IExtendedModel model = new OurModel();
    assertTrue(model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertTrue(model.addKeyFrame("test_rec", 10));
    assertFalse(
        model.editKeyFrame("test_ellipse", 10, new Point2D.Double(10, 10), Color.BLUE, 20, 40));

    List<IReadOnlyKeyFrame> kfList = model.getShapeKeyFrames("test_rec");
    assertEquals(1, kfList.size());
    assertEquals(10, kfList.get(0).getTick());
    assertEquals(new Point2D.Double(0, 0), kfList.get(0).getPosition());
    assertTrue(Color.RED.equals(kfList.get(0).getColor()));
    assertEquals(10, kfList.get(0).getWidth(), 0.01);
    assertEquals(20, kfList.get(0).getHeight(), 0.01);
    assertEquals(0, model.getShapeKeyFrames("test_ellipse").size());
  }

    @Test
    /** Test editing keyframe fails if it is null pos */
    public void editKeyFrameTest5(){
      IExtendedModel model = new OurModel();
      assertTrue( model
          .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
      assertTrue( model.addKeyFrame("test_rec", 10));
      assertFalse(model.editKeyFrame("test_rec", 10, null, Color.RED, 20, 40));

      List<IReadOnlyKeyFrame> kfList = model.getShapeKeyFrames("test_rec");
      assertEquals(1, kfList.size());
      assertEquals(10, kfList.get(0).getTick());
      assertEquals( new Point2D.Double(0,0), kfList.get(0).getPosition());
      assertTrue(Color.RED.equals(kfList.get(0).getColor()));
      assertEquals(10, kfList.get(0).getWidth(), 0.01);
      assertEquals(20, kfList.get(0).getHeight(), 0.01);
      assertEquals(0, model.getShapeKeyFrames("test_ellipse").size());
    }

  @Test
  /** Test editing keyframe fails if it is null color */
  public void editKeyFrameTest6(){
    IExtendedModel model = new OurModel();
    assertTrue( model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertTrue( model.addKeyFrame("test_rec", 10));
    assertFalse(model.editKeyFrame("test_rec", 10, new Point2D.Double(10, 10), null, 20, 40));

    List<IReadOnlyKeyFrame> kfList = model.getShapeKeyFrames("test_rec");
    assertEquals(1, kfList.size());
    assertEquals(10, kfList.get(0).getTick());
    assertEquals( new Point2D.Double(0,0), kfList.get(0).getPosition());
    assertTrue(Color.RED.equals(kfList.get(0).getColor()));
    assertEquals(10, kfList.get(0).getWidth(), 0.01);
    assertEquals(20, kfList.get(0).getHeight(), 0.01);
    assertEquals(0, model.getShapeKeyFrames("test_ellipse").size());
  }

  @Test
  /** Test adding a keyframe to a shape with 1 keyframe results in the expected keyframe */
  public void addKeyFrameTest2() {
    IExtendedModel model = new OurModel();
    assertTrue( model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertTrue( model.addKeyFrame("test_rec", 100));
    assertTrue(model.editKeyFrame("test_rec", 100, new Point2D.Double(50, 50), Color.BLUE, 20, 40));
    assertTrue( model.addKeyFrame("test_rec", 50));


    List<IReadOnlyKeyFrame> kfList = model.getShapeKeyFrames("test_rec");
    assertEquals(2, kfList.size());
    // assert edited keyframe is as expected
    assertEquals(100, kfList.get(1).getTick());
    assertTrue( new Point2D.Double(50,50).equals(kfList.get(1).getPosition()));
    assertTrue(Color.BLUE.equals(kfList.get(1).getColor()));
    assertEquals(20, kfList.get(1).getWidth(), 0.01);
    assertEquals(40, kfList.get(1).getHeight(), 0.01);
    // assert second added keyframe tweens between shape start and other keyframe
    assertEquals(50, kfList.get(0).getTick());
    assertTrue( new Point2D.Double(25,25).equals(kfList.get(0).getPosition()));
    assertTrue(new Color(127, 0, 127).equals(kfList.get(0).getColor()));
    assertEquals(15, kfList.get(0).getWidth(), 0.01);
    assertEquals(30, kfList.get(0).getHeight(), 0.01);
  }

  @Test
  /** Test adding a keyframe to a shape with 1 keyframe results in the expected keyframe */
  public void addKeyFrameTest3() {
    IExtendedModel model = new OurModel();
    assertTrue( model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertTrue( model.addKeyFrame("test_rec", 10));
    assertTrue(model.editKeyFrame("test_rec", 10, new Point2D.Double(50, 50), Color.BLUE, 20, 40));
    assertTrue( model.addKeyFrame("test_rec", 50));


    List<IReadOnlyKeyFrame> kfList = model.getShapeKeyFrames("test_rec");
    assertEquals(2, kfList.size());
    // assert edited keyframe is as expected
    assertEquals(10, kfList.get(0).getTick());
    assertTrue( new Point2D.Double(50,50).equals(kfList.get(0).getPosition()));
    assertTrue(Color.BLUE.equals(kfList.get(0).getColor()));
    assertEquals(20, kfList.get(0).getWidth(), 0.01);
    assertEquals(40, kfList.get(0).getHeight(), 0.01);
    // assert second is same as first at different tick
    assertEquals(50, kfList.get(1).getTick());
    assertTrue( new Point2D.Double(50,50).equals(kfList.get(1).getPosition()));
    assertTrue(Color.BLUE.equals(kfList.get(1).getColor()));
    assertEquals(20, kfList.get(1).getWidth(), 0.01);
    assertEquals(40, kfList.get(1).getHeight(), 0.01);
  }

  @Test
  /** Test adding a keyframe to a shape between 2 other keyframes results in expected keyframe */
  public void addKeyFrameTest4() {
    IExtendedModel model = new OurModel();
    assertTrue( model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertTrue( model.addKeyFrame("test_rec", 25));
    assertTrue(model.editKeyFrame("test_rec", 25, new Point2D.Double(50, 50), Color.BLUE, 20, 40));
    assertTrue( model.addKeyFrame("test_rec", 75));
    assertTrue(model.editKeyFrame("test_rec", 75, new Point2D.Double(100, 100), Color.GREEN, 40, 80));
    assertTrue(model.addKeyFrame("test_rec", 50));


    List<IReadOnlyKeyFrame> kfList = model.getShapeKeyFrames("test_rec");
    assertEquals(3, kfList.size());
    // assert first edited keyframe is as expected
    assertEquals(25, kfList.get(0).getTick());
    assertEquals( new Point2D.Double(50,50), kfList.get(0).getPosition());
    assertEquals(Color.BLUE, kfList.get(0).getColor());
    assertEquals(20, kfList.get(0).getWidth(), 0.01);
    assertEquals(40, kfList.get(0).getHeight(), 0.01);
    // assert second edited keyframe is as expected
    assertEquals(75, kfList.get(2).getTick());
    assertEquals( new Point2D.Double(100,100), kfList.get(2).getPosition());
    assertEquals(Color.GREEN, kfList.get(2).getColor());
    assertEquals(40, kfList.get(2).getWidth(), 0.01);
    assertEquals(80, kfList.get(2).getHeight(), 0.01);
    // assert added keyframe is as expected
    assertEquals(50, kfList.get(1).getTick());
    assertEquals( new Point2D.Double(75,75), kfList.get(1).getPosition());
    assertEquals(new Color(0, 127, 127), kfList.get(1).getColor());
    assertEquals(30, kfList.get(1).getWidth(), 0.01);
    assertEquals(60, kfList.get(1).getHeight(), 0.01);
  }

  @Test
  /** Test that adding keyframe fails if the shape doesn't exist */
  public void addKeyFrameTest5() {
    IExtendedModel model = new OurModel();
    assertFalse(model.addKeyFrame("test_rec", 10));
  }

  @Test
  /** Test that adding keyframe fails if the tick is invalid */
  public void addKeyFrameTest6() {
    IExtendedModel model = new OurModel();
    assertTrue( model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertFalse(model.addKeyFrame("test_rec", -10));
  }

  @Test
  /** Make sure keyframes are sorted by tick in getShapeKeyFrames() */
  public void getShapeKeyFramesTest1() {
    IExtendedModel model = new OurModel();
    assertTrue( model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertTrue(model.addKeyFrame("test_rec", 10));
    assertTrue(model.addKeyFrame("test_rec", 20));
    assertTrue(model.addKeyFrame("test_rec", 30));

    List<IReadOnlyKeyFrame> kfList = model.getShapeKeyFrames("test_rec");
    int prevTick = -1;
    for(IReadOnlyKeyFrame kf : kfList){
      assertTrue(prevTick < kf.getTick());
      prevTick = kf.getTick();
    }
  }

  @Test
  /** Make sure that it returns an empty list for a non-existent shape */
  public void getShapeKeyFramesTest2() {
    IExtendedModel model = new OurModel();
    assertTrue( model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertTrue(model.addKeyFrame("test_rec", 10));
    assertTrue(model.addKeyFrame("test_rec", 20));
    assertTrue(model.addKeyFrame("test_rec", 30));

    List<IReadOnlyKeyFrame> kfList = model.getShapeKeyFrames("test_ellipse");
    assertEquals(0, kfList.size());
  }

  @Test
  /** Test that if there are no keyframes it results in 0 for the maxTick */
  public void getMaximumTickTest1() {
    IExtendedModel model = new OurModel();
    assertEquals(0, model.getMaximumTick());
  }

  @Test
  /** Test that getMaximumTick() returns the highest tick (with one shape) */
  public void getMaximumTickTest2() {
    IExtendedModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertEquals(true, model.addKeyFrame("test_rec", 10));
    assertEquals(true, model.addKeyFrame("test_rec", 20));
    assertEquals(20, model.getMaximumTick());
  }

  @Test
  /** Test that getMaximumTick() returns the highest tick (with multiple shapes) */
  public void getMaximumTickTest3() {
    IExtendedModel model = new OurModel();
    assertEquals(true, model
        .addShape("test_rec", ShapeType.RECTANGLE, new Point2D.Double(0, 0), 10, 20, Color.RED));
    assertEquals(true, model.addShape("test_ellipse", ShapeType.ELLIPSE, new Point2D.Double(10, 10), 20, 10, Color.BLUE));
    assertEquals(true, model.addKeyFrame("test_rec", 10));
    assertEquals(true, model.addKeyFrame("test_rec", 20));
    assertEquals(true, model.addKeyFrame("test_ellipse", 15));
    assertEquals(true, model.addKeyFrame("test_ellipse", 50));
    assertEquals(50, model.getMaximumTick());
  }

}
