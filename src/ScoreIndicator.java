import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The ScoreIndicator class.
 */
public class ScoreIndicator implements Sprite {
    private final Counter scoreCounter;
    private final Rectangle scoreIndicatorRectangle;

    /**
     * Constructor of the ScoreIndicator class.
     * @param scoreCounter the score Counter
     * @param upperLeft the upperLeft Point of the ScoreIndicator Rectangle
     * @param width the width of the ScoreIndicator
     * @param height the height of the ScoreIndicator
     */
    public ScoreIndicator(Counter scoreCounter, Point upperLeft, double width, double height) {
        this.scoreCounter = scoreCounter;
        this.scoreIndicatorRectangle = new Rectangle(upperLeft, width, height);
    }
    /**
     * Constructor of the ScoreIndicator.
     * @param scoreCounter the scoreCounter
     */
    public ScoreIndicator(Counter scoreCounter) {
        this(
            scoreCounter,
            new Point(0, 0),
            800,
            20
        );
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(
            (int) this.scoreIndicatorRectangle.getUpperLeft().getX(),
            (int) this.scoreIndicatorRectangle.getUpperLeft().getY(),
            (int) this.scoreIndicatorRectangle.getWidth(),
            (int) this.scoreIndicatorRectangle.getHeight()
        );
        d.setColor(Color.BLACK);
        int textX = (int) (
            this.scoreIndicatorRectangle.getUpperLeft().getX()
            + this.scoreIndicatorRectangle.getWidth()
        ) / 2;
        int textY = (int) (
            this.scoreIndicatorRectangle.getUpperLeft().getY()
            + this.scoreIndicatorRectangle.getHeight()
        ) / 2;
        d.drawText(textX, textY, "Score: " + this.scoreCounter.getValue(), 12);

    }

    @Override
    public void timePassed() {
    }

}