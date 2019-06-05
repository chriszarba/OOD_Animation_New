import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class OurMotion implements IMotion {

  private final int startTick;
  private final int endTick;
  private final Point2D initialPos;
  private final Point2D finalPos;
  private final int initialWidth;
  private final int initialHeight;
  private final int finalWidth;
  private final int finalHeight;
  private final Color initialColor;
  private final Color finalColor;

  public OurMotion(int startTick, int endTick, Point2D initialPos, Point2D finalPos,
      int initialWidth, int initialHeight, int finalWidth, int finalHeight, Color initialColor,
      Color finalColor) throws IllegalArgumentException {
    if (startTick < 0 || endTick <= startTick) {
      throw new IllegalArgumentException("invalid time range");
    }
    if (initialWidth < 0 || initialHeight < 0 || finalWidth < 0 || finalHeight < 0) {
      throw new IllegalArgumentException("invalid dimension");
    }
    if (initialPos.getX() < 0 || initialPos.getY() < 0 || finalPos.getX() < 0
        || finalPos.getY() < 0) {
      throw new IllegalArgumentException("invalid position");
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
  public int getInitialWidth() {
    return this.initialWidth;
  }

  @Override
  public int getFinalWidth() {
    return this.finalWidth;
  }

  @Override
  public int getInitialHeight() {
    return this.initialHeight;
  }

  @Override
  public int getFinalHeight() {
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