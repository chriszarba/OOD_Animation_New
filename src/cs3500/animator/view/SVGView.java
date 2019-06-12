package cs3500.animator.view;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IReadOnlyShape;
import cs3500.animator.model.ShapeType;
import java.awt.Color;
import java.io.FileWriter;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SVGView implements IView {

  private DocumentBuilder builder;
  private FileWriter writer;

  public SVGView(String filepath) {
    this.builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    writer = new FileWriter(filepath);
  }

  @Override
  public void render(IReadOnlyModel model) {
    Document dom = this.builder.newDocument();
    Element rootEl = dom.createElement("svg");
    // TODO: svg attributes

    for(IReadOnlyShape shape : model.getAllShapes()){
      rootEl.appendChild(this.createShapeElement(shape, model.getShapeMotions(shape.getName()), dom));
    }


    dom.appendChild(rootEl);
    // write to file
    try {
      TransformerFactory trFactory = TransformerFactory.newInstance();
      Transformer tr = trFactory.newTransformer();
      tr.setOutputProperty(OutputKeys.METHOD, "xml");
      tr.setOutputProperty(OutputKeys.INDENT, "yes");
      tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
      tr.transform(new DOMSource(dom), new StreamResult(this.writer));
    }catch(TransformerConfigurationException e){
      // TODO
    }catch(TransformerException e){
      // TODO
    }
  }

  private Element createShapeElement(IReadOnlyShape shape, List<IMotion> motions, Document dom) {
    Element shapeEl = dom.createElement(this.typeToTag(shape.getShapeType()));

    shapeEl.setAttribute("id", shape.getName());
    shapeEl.setAttribute("x", this.doubleToIntString(shape.getX()));
    shapeEl.setAttribute("y", this.doubleToIntString(shape.getY()));
    shapeEl.setAttribute("width", this.doubleToIntString(shape.getWidth()));
    shapeEl.setAttribute("height", this.doubleToIntString(shape.getHeight()));
    shapeEl.setAttribute("fill", this.colorToRGBString(shape.getColor()));

    int count = 0;
    String beginString = "0s";
    String idString = shape.getName() + "-motion" + count;
    IMotion lastMotion = null;
    for(IMotion motion : motions){
      if(lastMotion.getEndTick() != motion.getStartTick()){
        // TODO: 
      }
      lastMotion = motion;
      // Create Animations associated with shape
      String durString = this.getDurationString(motion.getStartTick(), motion.getEndTick(), );

      if(Math.abs(motion.getInitialPos().getX() - motion.getFinalPos().getX()) > 0.01){
        // animate x position change
        shapeEl.appendChild(this.createAnimateElement(dom, idString, "x", this.doubleToIntString(motion.getInitialPos().getX()),
            this.doubleToIntString(motion.getFinalPos().getX()), durString, beginString));
        ++count;
        idString = shape.getName() + "-motion" + count;
      }

      if(Math.abs(motion.getInitialPos().getY() - motion.getFinalPos().getY()) > 0.01){
        // animate y position change
        shapeEl.appendChild(this.createAnimateElement(dom, idString, "y", this.doubleToIntString(motion.getInitialPos().getY()),
            this.doubleToIntString(motion.getFinalPos().getY()), durString, beginString));
        ++count;
        idString = shape.getName() + "-motion" + count;
      }

      if(Math.abs(motion.getInitialWidth() - motion.getFinalWidth()) > 0.01){
        // animate width change
        shapeEl.appendChild(this.createAnimateElement(dom, idString, "width", this.doubleToIntString(motion.getInitialWidth()),
            this.doubleToIntString(motion.getFinalWidth()), durString, beginString));
        ++count;
        idString = shape.getName() + "-motion" + count;
      }

      if(Math.abs(motion.getInitialHeight() - motion.getFinalHeight()) > 0.01){
        // animate height change
        shapeEl.appendChild(this.createAnimateElement(dom, idString, "width", this.doubleToIntString(motion.getInitialHeight()),
            this.doubleToIntString(motion.getFinalHeight()), durString, beginString));
        ++count;
        idString = shape.getName() + "-motion" + count;
      }

      if(!motion.getInitialColor().equals(motion.getFinalColor())){
        // animate color change
        shapeEl.appendChild(this.createAnimateElement(dom, idString, "fill", this.colorToRGBString(motion.getInitialColor()),
            this.colorToRGBString(motion.getFinalColor()), durString, beginString));
        ++count;
        idString = shape.getName() + "-motion" + count;
      }

      beginString = shape.getName() + "-motion" + count + ".end";
    }

    return shapeEl;
  }

  private Element createAnimateElement(Document dom, String id, String attName, String from, String to, String dur, String begin) {
    Element animateEl = dom.createElement("animate");
    animateEl.setAttribute("id", id);
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

  private String getDurationString(int startTick, int endTick, int tickPerSecond){
    double durInSec = (endTick - startTick) / tickPerSecond;
    return String.format(".1f", durInSec) + "s";
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
