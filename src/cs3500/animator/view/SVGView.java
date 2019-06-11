package cs3500.animator.view;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IReadOnlyShape;
import cs3500.animator.model.ShapeType;
import java.awt.Color;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SVGView implements IView {

  private DocumentBuilder builder;
  private Document dom;

  // TODO arguments
  public SVGView() {
    // TODO: instantiate builder
    this.dom = this.builder.newDocument();
  }

  @Override
  public void render(IReadOnlyModel model) {
    Element rootEl = this.dom.createElement("svg");
    // TODO: svg attributes

    for(IReadOnlyShape shape : model.getAllShapes()){
      rootEl.appendChild(this.createShapeElement(shape, model.getShapeMotions(shape.getName())));
    }


    dom.appendChild(rootEl);
    // TODO: write to file
  }

  private Element createShapeElement(IReadOnlyShape shape, List<IMotion> motions) {
    Element shapeEl = this.dom.createElement(this.typeToTag(shape.getShapeType()));

    shapeEl.setAttribute("id", shape.getName());
    shapeEl.setAttribute("x", this.doubleToIntString(shape.getX()));
    shapeEl.setAttribute("y", this.doubleToIntString(shape.getY()));
    shapeEl.setAttribute("width", this.doubleToIntString(shape.getWidth()));
    shapeEl.setAttribute("height", this.doubleToIntString(shape.getHeight()));
    shapeEl.setAttribute("fill", this.colorToRGBString(shape.getColor()));

    for(IMotion motion : motions){
      // Create Animations associated with shape

      if(Math.abs(motion.getInitialPos().getX() - motion.getFinalPos().getX()) > 0.01){
        // animate x position change
        shapeEl.appendChild(this.createAnimateElement("x", this.doubleToIntString(motion.getInitialPos().getX()),
            this.doubleToIntString(motion.getFinalPos().getX()), /* TODO */, /* TODO */));
      }

      if(Math.abs(motion.getInitialPos().getY() - motion.getFinalPos().getY()) > 0.01){
        // animate y position change
        shapeEl.appendChild(this.createAnimateElement("y", this.doubleToIntString(motion.getInitialPos().getY()),
            this.doubleToIntString(motion.getFinalPos().getY()), /* TODO */, /* TODO */));
      }

      if(Math.abs(motion.getInitialWidth() - motion.getFinalWidth()) > 0.01){
        // animate width change
        shapeEl.appendChild(this.createAnimateElement("width", this.doubleToIntString(motion.getInitialWidth()),
            this.doubleToIntString(motion.getFinalWidth()), /* TODO */, /* TODO */));
      }

      if(Math.abs(motion.getInitialHeight() - motion.getFinalHeight()) > 0.01){
        // animate height change
        shapeEl.appendChild(this.createAnimateElement("width", this.doubleToIntString(motion.getInitialHeight()),
            this.doubleToIntString(motion.getFinalHeight()), /* TODO */, /* TODO */));
      }

      if(!motion.getInitialColor().equals(motion.getFinalColor())){
        // animate color change
        shapeEl.appendChild(this.createAnimateElement("fill", this.colorToRGBString(motion.getInitialColor()),
            this.colorToRGBString(motion.getFinalColor()), /* TODO */, /* TODO */));
      }
    }

    return shapeEl;
  }

  private Element createAnimateElement(String attName, String from, String to, String dur, String begin) {
    Element animateEl = this.dom.createElement("animate");
    animateEl.setAttribute("attributeType", "XML");
    animateEl.setAttribute("attributeName", attName);
    animateEl.setAttribute("from", from);
    animateEl.setAttribute("to", to);
    animateEl.setAttribute("dur", dur);
    animateEl.setAttribute("repeatCount", "never");
    animateEl.setAttribute("begin", begin);
    animateEl.setAttribute("fill", "remove");

    return animateEl;
  }

  private String doubleToIntString(double d){
    return Integer.toString(((int) (d + 0.5)));
  }

  private String colorToRGBString(Color c){
    StringBuilder sb = new StringBuilder(30);

    sb.append("rgb( ");
    sb.append(c.getRed()).append(", ");
    sb.append(c.getGreen()).append(", ");
    sb.append(c.getBlue()).append(")");

    return sb.toString();
  }

  private String typeToTag(ShapeType type){
    switch (type){
      case RECTANGLE:
        return "rect";
      case ELLIPSE:
        return "ellipse";
      default:
        return "";
    }
  }
}
