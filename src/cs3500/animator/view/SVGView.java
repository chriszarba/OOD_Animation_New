package cs3500.animator.view;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IReadOnlyShape;
import cs3500.animator.model.ShapeType;
import java.awt.Color;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SVGView implements IView {

  @Override
  public void render(IReadOnlyModel model) {
    // TODO: NOWHERE NEAR DONE
    DocumentBuilder builder;
    Document dom = builder.newDocument();
    Element rootEl = dom.createElement("svg");
    // TODO: svg attributes

    for(IReadOnlyShape shape : model.getAllShapes()){
      // Create Shape
      // TODO: abstract create shape
      Element shapeEl = dom.createElement(this.typeToTag(shape.getShapeType()));

      shapeEl.setAttribute("x", Double.toString(shape.getX()));
      shapeEl.setAttribute("y", Double.toString(shape.getY()));
      shapeEl.setAttribute("width", Double.toString(shape.getWidth()));
      shapeEl.setAttribute("height", Double.toString(shape.getHeight()));
      Color shapeColor = shape.getColor();
      shapeEl.setAttribute("fill", "rgb(" + shapeColor.getRed()
          + ", " + shapeColor.getGreen() + ", " + shapeColor.getBlue() + ")");


      for(IMotion motion : model.getShapeMotions(shape.getName())){
        // Create Animations
        // TODO: abstract animate element

        if(Math.abs(motion.getInitialPos().getX() - motion.getFinalPos().getX()) > 0.01){
          // animate x position change
          Element animateEl = dom.createElement("animate");
          animateEl.setAttribute("attributeType", "XML");
          animateEl.setAttribute("attributeName", "x");
          animateEl.setAttribute("from", Double.toString(motion.getInitialPos().getX()));
          animateEl.setAttribute("to", Double.toString(motion.getFinalPos().getX());
          animateEl.setAttribute("dur", /* TODO */);
          animateEl.setAttribute("repeatCount", "never");
          animateEl.setAttribute("begin", /* TODO */);
          animateEl.setAttribute("fill", "remove");
          shapeEl.appendChild(animateEl);
        }

        if(Math.abs(motion.getInitialPos().getY() - motion.getFinalPos().getY()) > 0.01){
          // animate y position change
          Element animateEl = dom.createElement("animate");
          animateEl.setAttribute("attributeType", "XML");
          animateEl.setAttribute("attributeName", "y");
          animateEl.setAttribute("from", Double.toString(motion.getInitialPos().getY()));
          animateEl.setAttribute("to", Double.toString(motion.getFinalPos().getY());
          animateEl.setAttribute("dur", /* TODO */);
          animateEl.setAttribute("repeatCount", "never");
          animateEl.setAttribute("begin", /* TODO */);
          animateEl.setAttribute("fill", "remove");
          shapeEl.appendChild(animateEl);
        }

        if(Math.abs(motion.getInitialWidth() - motion.getFinalWidth()) > 0.01){
          // animate width change
          Element animateEl = dom.createElement("animate");
          animateEl.setAttribute("attributeType", "XML");
          animateEl.setAttribute("attributeName", "width");
          animateEl.setAttribute("from", Double.toString(motion.getInitialWidth()));
          animateEl.setAttribute("to", Double.toString(motion.getFinalWidth());
          animateEl.setAttribute("dur", /* TODO */);
          animateEl.setAttribute("repeatCount", "never");
          animateEl.setAttribute("begin", /* TODO */);
          animateEl.setAttribute("fill", "remove");
          shapeEl.appendChild(animateEl);
        }

        if(Math.abs(motion.getInitialHeight() - motion.getFinalHeight()) > 0.01){
          // animate height change
          Element animateEl = dom.createElement("animate");
          animateEl.setAttribute("attributeType", "XML");
          animateEl.setAttribute("attributeName", "height");
          animateEl.setAttribute("from", Double.toString(motion.getInitialHeight()));
          animateEl.setAttribute("to", Double.toString(motion.getFinalHeight());
          animateEl.setAttribute("dur", /* TODO */);
          animateEl.setAttribute("repeatCount", "never");
          animateEl.setAttribute("begin", /* TODO */);
          animateEl.setAttribute("fill", "remove");
          shapeEl.appendChild(animateEl);
        }

        if(!motion.getInitialColor().equals(motion.getFinalColor())){
          // animate color change
          Element animateEl = dom.createElement("animate");
          animateEl.setAttribute("attributeType", "XML");
          animateEl.setAttribute("attributeName", "fill");
          animateEl.setAttribute("from", "rgb(" + motion.getInitialColor().getRed() + ", "
          + motion.getInitialColor().getGreen() + ", " + motion.getInitialColor().getBlue() + ")");
          animateEl.setAttribute("to", "rgb(" + motion.getFinalColor().getRed() + ", "
              + motion.getFinalColor().getGreen() + ", " + motion.getFinalColor().getBlue() + ")");
          animateEl.setAttribute("dur", /* TODO */);
          animateEl.setAttribute("repeatCount", "never");
          animateEl.setAttribute("begin", /* TODO */);
          animateEl.setAttribute("fill", "remove");
          shapeEl.appendChild(animateEl);
        }
      } // motion for
      rootEl.appendChild(shapeEl);
    } // shape for


    dom.appendChild(rootEl);

  }

  private String typeToTag(ShapeType type){
    switch (type){
      case RECTANGLE:
        return "rec";
      case ELLIPSE:
        return "ellipse";
      default:
        return "";
    }
  }
}
