package cs3500.animator.view;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IReadOnlyShape;
import cs3500.animator.model.ShapeType;
import java.io.IOException;

public class TextView implements IView {

  private Appendable appendable;

  public TextView(Appendable ap) throws IllegalArgumentException {
    if(ap == null){
      throw new IllegalArgumentException("null appendable");
    }
    this.appendable = ap;
  }

  @Override
  public void render(IReadOnlyModel model) throws IllegalArgumentException {
    if(model == null){
      throw new IllegalArgumentException("null model");
    }
    StringBuilder builder = new StringBuilder();
    for (IReadOnlyShape shape : model.getAllShapes()) {
      builder.append("shape ");
      builder.append(shape.getName());
      builder.append(" ");
      builder.append(this.getTypeString(shape.getShapeType()));
      builder.append("\n");
      for (IMotion motion : model.getShapeMotions(shape.getName())) {
        builder.append("motion ");
        builder.append(shape.getName());

        builder.append(
            String.format(" %-3d %-3.0f %-3.0f %-3.0f %-3.0f %-3d %-3d %-3d", motion.getStartTick(),
                motion.getInitialPos().getX(),
                motion.getInitialPos().getY(), motion.getInitialWidth(), motion.getInitialHeight(),
                motion.getInitialColor().getRed(),
                motion.getInitialColor().getGreen(), motion.getInitialColor().getBlue()));

        builder.append("   ");

        builder.append(
            String.format(" %-3d %-3.0f %-3.0f %-3.0f %-3.0f %-3d %-3d %d", motion.getEndTick(),
                motion.getFinalPos().getX(),
                motion.getFinalPos().getY(), motion.getFinalWidth(), motion.getFinalHeight(),
                motion.getFinalColor().getRed(),
                motion.getFinalColor().getGreen(), motion.getFinalColor().getBlue()));

        builder.append("\n");
      }
      builder.append("\n\n");
    }
    // remove last newlines
    builder.delete(builder.length() - 3, builder.length());

    try{
      this.appendable.append(builder.toString());
    }catch(IOException e){
      // TODO
    }
  }

  /**
   * Helper function to get the String value of the enum associated with a shape.
   *
   * @param type - the type of shape to get the String representation for.
   * @return the String representation of the given type.
   */
  private String getTypeString(ShapeType type) {
    switch (type) {
      case ELLIPSE:
        return "ellipse";
      case RECTANGLE:
        return "rectangle";
      default:
        return "";
    }
  }

}
