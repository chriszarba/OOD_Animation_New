import java.awt.Color;
import org.junit.Test;

import java.awt.geom.Point2D;

import cs3500.animator.model.IModel;
import cs3500.animator.model.OurModel;
import cs3500.animator.model.ShapeType;
import cs3500.animator.view.GUIView;
import cs3500.animator.view.IView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the public methods of {@link GUIView}.
 */
public class GUIViewTest {

  /**
   * Java doesn't have a way to test render or paint because they are swing methods and don't have
   * equivalency, so we won't really test them in this file even though they are public.
   */

  @Test
  /**
   * Test that constructor works.
   */
  public void testConstructors() {
    IView view = new GUIView(2);
    assertNotEquals(null, view);
    IView view2 = new GUIView(10);
    assertNotEquals(null, view2);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Test that constructor throws illegal argument for tick speed of 0.
   */
  public void testConstructorFail() {
    IView view = new GUIView(0);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Test that constructor throws illegal argument for tick speed of < 0.
   */
  public void testConstructorFail2() {
    IView view = new GUIView(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Test that constructor throws illegal argument for tick speed of < 0.
   */
  public void testConstructorFail3() {
    IView view = new GUIView(Integer.MIN_VALUE);
  }

  @Test
  /**
   * Test that render runs.
   */
  public void testRender() throws InterruptedException {
    IModel model = new OurModel();
    Color red = new Color(255, 0, 0);
    Color blue = new Color(0, 0, 255);
    assertEquals(true,
        model.addShape("R", ShapeType.RECTANGLE, new Point2D.Double(200,
            200), 50, 100, red));
    assertEquals(true,
        model.addShape("C", ShapeType.ELLIPSE, new Point2D.Double(440,
            70), 120, 60, blue));

    assertEquals(true, model
        .addMotion("R", 1, 10, new Point2D.Double(200, 200),
            new Point2D.Double(200, 200), 50, 100,
            50, 100, red, red));
    assertEquals(true, model
        .addMotion("R", 10, 50, new Point2D.Double(200, 200),
            new Point2D.Double(300, 300), 50, 100,
            50, 100, red, red));
    assertEquals(true, model
        .addMotion("R", 50, 51, new Point2D.Double(300, 300),
            new Point2D.Double(300, 300), 50, 100,
            50, 100, red, red));
    assertEquals(true, model
        .addMotion("R", 51, 70, new Point2D.Double(300, 300),
            new Point2D.Double(300, 300), 50, 100,
            25, 100, red, red));
    assertEquals(true, model
        .addMotion("R", 70, 100, new Point2D.Double(300, 300),
            new Point2D.Double(200, 200), 25,
            100, 25, 100, red, red));

    assertEquals(true, model
        .addMotion("C", 6, 20, new Point2D.Double(440, 70),
            new Point2D.Double(440, 70), 120, 60,
            120, 60, blue, blue));
    assertEquals(true, model
        .addMotion("C", 20, 50, new Point2D.Double(440, 70),
            new Point2D.Double(440, 250), 120, 60,
            120, 60, blue, blue));
    Color lightGreen = new Color(0, 170, 85);
    assertEquals(true, model
        .addMotion("C", 50, 70, new Point2D.Double(440, 250),
            new Point2D.Double(440, 370), 120, 60,
            120, 60, blue, lightGreen));
    Color green = new Color(0, 255, 0);
    assertEquals(true, model
        .addMotion("C", 70, 80, new Point2D.Double(440, 370),
            new Point2D.Double(440, 370), 120, 60,
            120, 60, lightGreen, green));
    assertEquals(true, model
        .addMotion("C", 80, 100, new Point2D.Double(440, 370),
            new Point2D.Double(440, 370), 120,
            60, 120, 60, green, green));

    IView gui = new GUIView(50);
    gui.render(model);
    assert (true);
  }

}
