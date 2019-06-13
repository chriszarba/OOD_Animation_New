package cs3500.animator;

import cs3500.animator.controller.IController;
import cs3500.animator.controller.OurController;
import cs3500.animator.model.IModel;
import cs3500.animator.model.OurModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.GUIView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

public class Excellence {
  public static void main(String[] args){
    Excellence.ControllerBuilder.parseArgs(args);
    IController controller = Excellence.ControllerBuilder.buildController();
    if(controller == null){
      System.out.println("Invalid Arguments!");
      return;
    }
    controller.run();
  }

  private static class ControllerBuilder {
    private static String viewString;
    private static String inputFile;
    private static String output;
    private static IView view;
    private static IModel model;
    private static int ticksPerSecond;

    public static void parseArgs(String[] args){
      // reset variables
      viewString = null;
      inputFile = null;
      output = null;
      view = null;
      model = null;
      ticksPerSecond = 1;

      for(int i = 0; i < args.length; i+=2){
        if(i + 1 < args.length){
          if(args[i].equals("-in")){
            inputFile = args[i+1];
          }else if(args[i].equals("-view")){
            viewString = args[i+1];
          }else if(args[i].equals("-out")){
            output = args[i+1];
          }else if(args[i].equals("-speed")){
            ticksPerSecond = Integer.parseInt(args[i+1]);
          }
        }
      }

    }

    private static void buildModel() {
      OurModel.Builder builder = new OurModel.Builder();
      try{
        AnimationReader.parseFile( new FileReader(inputFile), builder);
      }catch(FileNotFoundException e){
        return;
      }
      model = builder.build();
    }

    private static IController buildController() {
      if(inputFile == null || viewString == null){
        return null;
      }

      buildModel();
      buildView(viewString);
      IController controller = new OurController(view, model);
      return controller;
    }

    private static OutputStream buildOutputStream() {
      if(output == null){
        return System.out;
      }
      try{
        return new FileOutputStream(output);
      }catch(FileNotFoundException e){
        return null;
      }
    }

    private static Appendable buildAppendable() {
      if(output == null){
        return System.out;
      }else{

      }
      return null;
    }

    private static void buildView(String viewType){
      switch (viewType){
        case "svg":
            view = new SVGView(buildOutputStream(), ticksPerSecond);
          break;
        case "text":
          view = new TextView(buildAppendable());
          break;
        case "visual":
          view = new GUIView(ticksPerSecond);
          break;
      }
    }

  }
}
