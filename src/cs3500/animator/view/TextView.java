package cs3500.animator.view;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IReadOnlyShape;
import cs3500.animator.model.ShapeType;
import java.io.IOException;
import java.io.Writer;


/**
 * Outputs a formatted description of all shapes, and the associated motions. The number values in
 * order are: starting tick, starting x position, the starting y position, the starting width, the
 * starting height, the starting red value of the RGB color, the starting green value of the RGB
 * color, the starting blue value of the RGB color, the ending tick, the ending x position, the
 * ending y position, the ending width, the ending height, the ending red value of the RGB color,
 * the ending green value of the RGB color, and the ending blue value of the RGB color. This is
 * written to the given appendable.
 */
public class TextView implements IView {

  private final Writer writer;

  /**
   * Constructs a new TextView.
   *
   * @param writer - the writer to write output to.
   * @throws IllegalArgumentException - if the given writer is null.
   */
  public TextView(Writer writer) throws IllegalArgumentException {
    if (writer == null) {
      throw new IllegalArgumentException("null appendable");
    }
    this.writer = writer;
  }

  @Override
  public void render(IReadOnlyModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("null model");
    }
    StringBuilder builder = new StringBuilder();

    builder.append(String
        .format("canvas %-3d %-3d %-4d %d\n\n", model.getBoundingX(), model.getBoundingY(),
            model.getCanvasWidth(), model.getCanvasHeight()));

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

    try {
      this.writer.write(builder.toString());
      this.writer.flush();
    } catch (IOException e) {
      throw new IllegalStateException("Failed to append to appendable");
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
