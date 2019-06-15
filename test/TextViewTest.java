import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import cs3500.animator.model.IModel;
import cs3500.animator.model.OurModel;
import cs3500.animator.model.ShapeType;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;

import static org.junit.Assert.assertEquals;

public class TextViewTest {

  @Test
  /**
   * Test that constructor works.
   */
  public void testConstructor(){
    IView view = new TextView(new StringBuilder());
  }

  @Test (expected = IllegalArgumentException.class)
  /**
   * Test that constructor throws illegalargumentexception for null.
   */
  public void testConstructor1(){
    IView view = new TextView(null);
  }


  @Test
  /**
   * Test that render works properly.
   */
  public void testRender() {
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

    Appendable ap = new StringBuilder();
    IView textView = new TextView(ap);
    textView.render(model);

    String expected = "canvas 2147483647 2147483647 1000 1000\n"
            + "shape R rectangle\n"
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

    assertEquals(expected, ap.toString());
  }
}
