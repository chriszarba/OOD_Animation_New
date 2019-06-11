import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * An implementation of {@link IMotion}.
 */
public class OurMotion implements IMotion {

  private final int startTick;
  private final int endTick;
  private final Point2D initialPos;
  private final Point2D finalPos;
  private final double initialWidth;
  private final double initialHeight;
  private final double finalWidth;
  private final double finalHeight;
  private final Color initialColor;
  private final Color finalColor;

  /**
   * Constructs a new OurMotion.
   *
   * @param startTick - the starting tick of the motion.
   * @param endTick - the ending tick of the motion.
   * @param initialPos - the starting position of the associated shape.
   * @param finalPos - the ending position of the associated shape.
   * @param initialWidth - the starting width of the associated shape.
   * @param initialHeight - the starting height of the associated shape.
   * @param finalWidth - the ending width of the associated shape.
   * @param finalHeight - the ending height of the associated shape.
   * @param initialColor - the initial color of the associated shape.
   * @param finalColor - the ending color of the associated shape.
   * @throws IllegalArgumentException If either the ending tick is the same as or before the
   *     starting tick, if any of the dimensions are negative, or if any of the positions are
   *     negative.
   */
  public OurMotion(int startTick, int endTick, Point2D initialPos, Point2D finalPos,
      double initialWidth, double initialHeight, double finalWidth, double finalHeight,
      Color initialColor,
      Color finalColor) throws IllegalArgumentException {
    if (startTick < 0 || endTick <= startTick) {
      throw new IllegalArgumentException("invalid time range");
    }
    if (initialWidth < 0 || initialHeight < 0 || finalWidth < 0 || finalHeight < 0) {
      throw new IllegalArgumentException("invalid dimension");
    }
    if (initialPos == null || finalPos == null | initialPos.getX() < 0 || initialPos.getY() < 0
        || finalPos.getX() < 0
        || finalPos.getY() < 0) {
      throw new IllegalArgumentException("invalid position");
    }
    if (initialColor == null || finalColor == null) {
      throw new IllegalArgumentException("null color");
    }

    this.startTick = startTick;
    this.endTick = endTick;

    this.initialPos = new Point2D.Double(initialPos.getX(), initialPos.getY());
    this.finalPos = new Point2D.Double(finalPos.getX(), finalPos.getY());

    this.initialWidth = initialWidth;
    this.initialHeight = initialHeight;
    this.finalWidth = finalWidth;
    this.finalHeight = finalHeight;

    this.initialColor = new Color(initialColor.getRed(), initialColor.getGreen(),
        initialColor.getBlue(), initialColor.getAlpha());
    this.finalColor = new Color(finalColor.getRed(), finalColor.getGreen(), finalColor.getBlue(),
        finalColor.getAlpha());
  }


  @Override
  public int getStartTick() {
    return this.startTick;
  }

  @Override
  public int getEndTick() {
    return this.endTick;
  }

  @Override
  public Point2D getInitialPos() {
    return new Point2D.Double(this.initialPos.getX(), this.initialPos.getY());
  }

  @Override
  public Point2D getFinalPos() {
    return new Point2D.Double(this.finalPos.getX(), this.finalPos.getY());
  }

  @Override
  public double getInitialWidth() {
    return this.initialWidth;
  }

  @Override
  public double getFinalWidth() {
    return this.finalWidth;
  }

  @Override
  public double getInitialHeight() {
    return this.initialHeight;
  }

  @Override
  public double getFinalHeight() {
    return this.finalHeight;
  }

  @Override
  public Color getInitialColor() {
    return new Color(this.initialColor.getRed(), this.initialColor.getGreen(),
        this.initialColor.getBlue(), this.initialColor.getAlpha());
  }

  @Override
  public Color getFinalColor() {
    return new Color(this.finalColor.getRed(), this.finalColor.getGreen(),
        this.finalColor.getBlue(), this.finalColor.getAlpha());
  }

}