package cs3500.animator;

import cs3500.animator.controller.IController;
import cs3500.animator.controller.OurController;
import cs3500.animator.model.IModel;
import cs3500.animator.model.OurModel;
import cs3500.animator.model.ShapeType;
import cs3500.animator.view.GUIView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class Excellence {
  public static void main(String[] args){
    Color red = new Color(255, 0, 0);
    IModel model = new OurModel();
    model.addShape("R", ShapeType.RECTANGLE, new Point2D.Double(200, 200), 50, 100, red);
    model.addMotion("R", 1, 10, new Point2D.Double(200, 200), new Point2D.Double(200, 200), 50, 100,
            50, 100, red, red);
    model.addMotion("R", 10, 50, new Point2D.Double(200, 200), new Point2D.Double(300, 300), 50, 100,
            50, 100, red, red);
    model.addMotion("R", 50, 51, new Point2D.Double(300, 300), new Point2D.Double(300, 300), 50, 100,
            50, 100, red, red);
    model.addMotion("R", 51, 70, new Point2D.Double(300, 300), new Point2D.Double(300, 300), 50, 100,
            25, 100, red, red);
    model.addMotion("R", 70, 100, new Point2D.Double(300, 300), new Point2D.Double(200, 200), 25,
            100, 25, 100, red, red);
    try{
      FileOutputStream stream = new FileOutputStream("./test.svg");
      IView view = new SVGView(stream, 10);

      view.render(model);
    }catch(FileNotFoundException e){
      System.out.println("Bad File");
    }
  }

  private static class ControllerBuilder {
    private static String inputFile;
    private static String output;
    private static IView view;
    private static int ticksPerSecond = 1;

    public static void parseArgs(String[] args){
      String view = "";

      for(int i = 0; i < args.length; i+=2){
        if(i + 1 < args.length){
          if(args[i].equals("-in")){
            inputFile = args[i+1];
          }else if(args[i].equals("-view")){
            view = args[i+1];
          }else if(args[i].equals("-out")){
            output = args[i+1];
          }else if(args[i].equals("-speed")){
            ticksPerSecond = Integer.parseInt(args[i+1]);
          }
        }
      }

      buildView(view);
    }

    public static IController buildController() {
      IController controller = new OurController(view, new OurModel());
      return controller;
    }

    public static void buildView(String viewType){
      switch (viewType){
        case "svg":
          try{
            OutputStream stream = new FileOutputStream(output);
            view = new SVGView(stream, ticksPerSecond);
          }catch(FileNotFoundException e){
            // TODO
          }
          break;
        case "text":
          if(output == null){
            view = new TextView(System.out);
          }else{
            // TODO
          }
          break;
        case "visual":
          view = new GUIView(ticksPerSecond);
          break;
      }
    }

  }
}
