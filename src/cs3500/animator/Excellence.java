package cs3500.animator;

import cs3500.animator.controller.IController;
import cs3500.animator.controller.OurController;
import cs3500.animator.model.OurModel;
import cs3500.animator.view.GUIView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class Excellence {
  public static void main(String[] args){

  }

  private class ControllerBuilder {
    private String inputFile;
    private String output;
    private IView view;
    private int ticksPerSecond = 1;

    public void parseArgs(String[] args){
      String view = "";

      for(int i = 0; i < args.length; i+=2){
        if(i + 1 < args.length){
          if(args[i].equals("-in")){
            inputFile = args[i+1];
          }else if(args[i].equals("-view")){
            view = args[i+1];
          }else if(args[i].equals("-out")){
            this.output = args[i+1];
          }else if(args[i].equals("-speed")){
            this.ticksPerSecond = Integer.parseInt(args[i+1]);
          }
        }
      }

      this.buildView(view);
    }

    public IController buildController() {
      IController OurController(this.view, new OurModel());
    }

    public void buildView(String viewType){
      switch (viewType){
        case "svg":
          OutputStream stream = new FileOutputStream(this.output);
          this.view = new SVGView(stream);
          break;
        case "text":
          if(output == null){
            this.view = new TextView(System.out);
          }else{
            // TODO
          }
          break;
        case "visual":
          this.view = new GUIView();
          break;
      }
    }

  }
}
