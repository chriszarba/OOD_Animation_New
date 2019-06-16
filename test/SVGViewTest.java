import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.animator.model.IModel;
import cs3500.animator.model.OurModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.OutputStream;
import org.junit.Test;

/**
 * Test public methods in {@link SVGView}.
 */
public class SVGViewTest {

  @Test(expected = IllegalArgumentException.class)
  /** Test constructor throws exception if given null outputStream */
  public void constructorTest1() {
    IView view = new SVGView(null, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  /** Test constructor throws exception if given invalid speed */
  public void constructorTest2() {
    IView view = new SVGView(System.out, -1);
  }

  @Test
  /** Test constructor succeeds on valid arguments */
  public void constructorTest3() {
    IView view = new SVGView(System.out, 10);
    assertNotEquals(null, view);
  }

  @Test
  /**
   * Test render works
   */
  public void renderTest1() {
    OutputStream stream = new ByteArrayOutputStream();
    IView view = new SVGView(stream, 10);
    String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
        + "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" "
        + "height=\"360\" version=\"1.1\" width=\"360\">\n"
        + "    <rect fill=\"rgb(255, 0, 0)\" height=\"100\" id=\"R\" width=\"50\" x=\"200\" "
        + "y=\"200\">\n"
        + "        <animate attributeName=\"x\" attributeType=\"XML\" begin=\"0s\" dur=\"4000ms\" "
        + "fill=\"freeze\" from=\"200\" id=\"R_motion0\" to=\"300\"/>\n"
        + "        <animate attributeName=\"y\" attributeType=\"XML\" begin=\"0s\" dur=\"4000ms\" "
        + "fill=\"freeze\" from=\"200\" id=\"R_motion1\" to=\"300\"/>\n"
        + "        <animate attributeName=\"width\" attributeType=\"XML\" begin=\"R_motion0.end\" "
        + "dur=\"1900ms\" fill=\"freeze\" from=\"50\" id=\"R_motion2\" to=\"25\"/>\n"
        + "        <animate attributeName=\"x\" attributeType=\"XML\" begin=\"R_motion2.end\" "
        + "dur=\"3000ms\" fill=\"freeze\" from=\"300\" id=\"R_motion3\" to=\"200\"/>\n"
        + "        <animate attributeName=\"y\" attributeType=\"XML\" begin=\"R_motion2.end\" "
        + "dur=\"3000ms\" fill=\"freeze\" from=\"300\" id=\"R_motion4\" to=\"200\"/>\n"
        + "    </rect>\n"
        + "    <ellipse cx=\"500\" cy=\"100\" fill=\"rgb(0, 0, 255)\" id=\"C\" rx=\"60\" ry=\"30\">"
        + "\n"
        + "        <animate attributeName=\"cy\" attributeType=\"XML\" begin=\"0s\" dur=\"3000ms\" "
        + "fill=\"freeze\" from=\"100\" id=\"C_motion0\" to=\"280\"/>\n"
        + "        <animate attributeName=\"cy\" attributeType=\"XML\" begin=\"C_motion0.end\" "
        + "dur=\"2000ms\" fill=\"freeze\" from=\"280\" id=\"C_motion1\" to=\"400\"/>\n"
        + "        <animate attributeName=\"fill\" attributeType=\"XML\" begin=\"C_motion0.end\" "
        + "dur=\"2000ms\" fill=\"freeze\" from=\"rgb(0, 0, 255)\" id=\"C_motion2\" "
        + "to=\"rgb(0, 170, 85)\"/>\n"
        + "        <animate attributeName=\"fill\" attributeType=\"XML\" begin=\"C_motion1.end\" "
        + "dur=\"1000ms\" fill=\"freeze\" from=\"rgb(0, 170, 85)\" id=\"C_motion3\" "
        + "to=\"rgb(0, 255, 0)\"/>\n"
        + "    </ellipse>\n"
        + "</svg>\n";
    try {
      Readable input = new FileReader("./test/TestInputs/smalldemo.txt");
      IModel model = AnimationReader.parseFile(input, new OurModel.Builder());
      view.render(model);
      assertEquals(expected, stream.toString());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      assert (false);
    }
  }

  @Test
  /**
   * Test render works and properly does different duration on different speeds
   */
  public void renderTest2() {
    OutputStream stream = new ByteArrayOutputStream();
    IView view = new SVGView(stream, 100);
    String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
        + "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" "
        + "height=\"360\" version=\"1.1\" width=\"360\">\n"
        + "    <rect fill=\"rgb(255, 0, 0)\" height=\"100\" id=\"R\" width=\"50\" x=\"200\" "
        + "y=\"200\">\n"
        + "        <animate attributeName=\"x\" attributeType=\"XML\" begin=\"0s\" dur=\"400ms\" "
        + "fill=\"freeze\" from=\"200\" id=\"R_motion0\" to=\"300\"/>\n"
        + "        <animate attributeName=\"y\" attributeType=\"XML\" begin=\"0s\" dur=\"400ms\" "
        + "fill=\"freeze\" from=\"200\" id=\"R_motion1\" to=\"300\"/>\n"
        + "        <animate attributeName=\"width\" attributeType=\"XML\" begin=\"R_motion0.end\" "
        + "dur=\"190ms\" fill=\"freeze\" from=\"50\" id=\"R_motion2\" to=\"25\"/>\n"
        + "        <animate attributeName=\"x\" attributeType=\"XML\" begin=\"R_motion2.end\" "
        + "dur=\"300ms\" fill=\"freeze\" from=\"300\" id=\"R_motion3\" to=\"200\"/>\n"
        + "        <animate attributeName=\"y\" attributeType=\"XML\" begin=\"R_motion2.end\" "
        + "dur=\"300ms\" fill=\"freeze\" from=\"300\" id=\"R_motion4\" to=\"200\"/>\n"
        + "    </rect>\n"
        + "    <ellipse cx=\"500\" cy=\"100\" fill=\"rgb(0, 0, 255)\" id=\"C\" rx=\"60\" ry=\"30\">"
        + "\n"
        + "        <animate attributeName=\"cy\" attributeType=\"XML\" begin=\"0s\" dur=\"300ms\" "
        + "fill=\"freeze\" from=\"100\" id=\"C_motion0\" to=\"280\"/>\n"
        + "        <animate attributeName=\"cy\" attributeType=\"XML\" begin=\"C_motion0.end\" "
        + "dur=\"200ms\" fill=\"freeze\" from=\"280\" id=\"C_motion1\" to=\"400\"/>\n"
        + "        <animate attributeName=\"fill\" attributeType=\"XML\" begin=\"C_motion0.end\" "
        + "dur=\"200ms\" fill=\"freeze\" from=\"rgb(0, 0, 255)\" id=\"C_motion2\" "
        + "to=\"rgb(0, 170, 85)\"/>\n"
        + "        <animate attributeName=\"fill\" attributeType=\"XML\" begin=\"C_motion1.end\" "
        + "dur=\"100ms\" fill=\"freeze\" from=\"rgb(0, 170, 85)\" id=\"C_motion3\" "
        + "to=\"rgb(0, 255, 0)\"/>\n"
        + "    </ellipse>\n"
        + "</svg>\n";
    try {
      Readable input = new FileReader("./test/TestInputs/smalldemo.txt");
      IModel model = AnimationReader.parseFile(input, new OurModel.Builder());
      view.render(model);
      assertEquals(expected, stream.toString());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      assert (false);
    }
  }

}