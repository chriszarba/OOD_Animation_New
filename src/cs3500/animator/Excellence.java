package cs3500.animator;

import cs3500.animator.controller.IController;
import cs3500.animator.controller.OurController;
import cs3500.animator.model.IModel;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.OurModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.CompositeView;
import cs3500.animator.view.ControllableView;
import cs3500.animator.view.GUIView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Class to contain the main method of the program.
 */
public class Excellence {

  /**
   * The main method of the program. Uses the command line arguments to construct a model and view,
   * and plays the animation.
   *
   * @param args - the command line arguments.
   */
  public static void main(String[] args) {
    Excellence.ControllerBuilder.parseArgs(args);
    IController controller = Excellence.ControllerBuilder.buildController();
    if (controller == null) {
      System.out.println("Invalid Arguments!");
      return;
    }
    controller.run();
  }

  /**
   * Static builder class to construct a controller (and the model and view) based on command line
   * arguments.
   */
  private static class ControllerBuilder {

    private static String viewString;
    private static String inputFile;
    private static String output;
    private static IView view;
    private static IModel model;
    private static int ticksPerSecond;

    /**
     * Parses the command line arguments. Must be the first method called.
     *
     * @param args - the command line arguments.
     */
    public static void parseArgs(String[] args) {
      // reset variables
      viewString = null;
      inputFile = null;
      output = null;
      view = null;
      model = null;
      ticksPerSecond = 1;

      for (int i = 0; i < args.length; i += 2) {
        if (i + 1 < args.length) {
          if (args[i].equals("-in")) {
            inputFile = args[i + 1];
          } else if (args[i].equals("-view")) {
            viewString = args[i + 1];
          } else if (args[i].equals("-out")) {
            output = args[i + 1];
          } else if (args[i].equals("-speed")) {
            ticksPerSecond = Integer.parseInt(args[i + 1]);
          }
        }
      }

    }

    /**
     * Builds a model based on the given input file using {@link AnimationReader}. Currently the
     * model immplementation used is {@link OurModel}.
     */
    private static void buildModel() {
      OurModel.Builder builder = new OurModel.Builder();
      try {
        AnimationReader.parseFile(new FileReader(inputFile), builder);
      } catch (FileNotFoundException e) {
        return;
      }
      model = builder.build();
    }

    /**
     * Builds the controller based on the given arguments.
     *
     * @return - A controller containing the correct model and view based on the command line
     *     arguments. null if the required arguments were not provided.
     */
    private static IController buildController() {
      if (inputFile == null || viewString == null) {
        return null;
      }

      buildModel();
      buildView(viewString);
      IController controller = new OurController(view, model);
      return controller;
    }

    /**
     * Builds an output stream based on the given out argument, or System.out by default.
     *
     * @return an output stream corresponding to the given output argument. null if the output file
     *     cannot be created or accessed, or is a incorrect path.
     */
    private static OutputStream buildOutputStream() {
      if (output == null) {
        return System.out;
      }
      try {
        return new FileOutputStream(output);
      } catch (FileNotFoundException e) {
        return null;
      }
    }

    /**
     * Builds an appendable based on the given output argument, system.out if the argument was not
     * provided.
     *
     * @return an appendable corresponding to the given output argument. null if the output file
     *     cannot be created or accessed, or is an incorrect path.
     */
    private static Writer buildWriter() {
      if (output == null) {
        return new PrintWriter(System.out);
      } else {
        try {
          return new FileWriter(output);
        } catch (IOException e) {
          return null;
        }
      }
    }

    /**
     * Builds a view based on the given view and speed.
     *
     * @param viewType - the string representing the type of view in the command line arguments.
     */
    private static void buildView(String viewType) {
      switch (viewType) {
        case "svg":
          view = new SVGView(buildOutputStream(), ticksPerSecond);
          break;
        case "text":
          view = new TextView(buildWriter());
          break;
        case "visual":
          view = new GUIView(ticksPerSecond);
          break;
        default:
          view = null;
          break;
      }
    }

  }
}
