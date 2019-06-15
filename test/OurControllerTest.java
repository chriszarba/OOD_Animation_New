import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.animator.controller.IController;
import cs3500.animator.controller.OurController;
import cs3500.animator.model.IModel;
import cs3500.animator.model.OurModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Test;

public class OurControllerTest {

  @Test(expected = IllegalArgumentException.class)
  /**
   * Ensure constructor throws exception on null view
   */
  public void constructorTest1() {
    IController controller = new OurController(null, new OurModel());
  }

  @Test(expected = IllegalArgumentException.class)
  /**
   * Ensure constructor throws exception on null model
   */
  public void constructorTest2() {
    IController controller = new OurController(new TextView(System.out), null);
  }

  @Test
  /** Ensure constructor succeeds with valid arguments */
  public void constructorTest3() {
    IController controller = new OurController(new TextView(System.out), new OurModel());
    assertNotEquals(null, controller);
  }

  @Test
  /** Ensure run causes the animation to render */
  public void runTest() {
    StringBuilder builder = new StringBuilder();
    String expected = "canvas 200 70  360  360\n"
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
    try {
      Readable input = new FileReader("./test/TestInputs/smalldemo.txt");
      IModel model = AnimationReader.parseFile(input, new OurModel.Builder());
      IController controller = new OurController(new TextView(builder), model);
      controller.run();
      assertEquals(expected, builder.toString());
    } catch(FileNotFoundException e){
      e.printStackTrace();
      assert(false);
    }
    assert(!builder.toString().isEmpty());
  }

}