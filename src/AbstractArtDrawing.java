import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
/**
 * Compelled to create this comments by checkstyles.
 */
public class AbstractArtDrawing {
    /**
     * Generated random Lines.
     * @param width the width of the screen
     * @param height the height of the screen
     * @param amountOfLines the amount of Lines to generate
     * @return the Lines generated
     */
    public Line[] generateRandomLines(int width, int height, int amountOfLines) {
        Random random = new Random();
        Line[] lines = new Line[amountOfLines];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Line(
                random.nextInt(height) + 1,
                random.nextInt(height) + 1,
                random.nextInt(width) + 1,
                random.nextInt(height) + 1
            );
        }
        return lines;
    }
    /**
     * Generates and draws random Lines.
     * @param width the width of the screen
     * @param height the height of the screen
     * @param amountOfLines the amount of Lines
     * @param radiusOfPoint the radius of the Points
     */
    public void drawRandomLines(
        int width,
        int height,
        int amountOfLines,
        int radiusOfPoint
    ) {
        GUI gui = new GUI("Eye lineR", width, height);
        DrawSurface drawSurface = gui.getDrawSurface();
        Line[] lines = generateRandomLines(width, height, amountOfLines);

        for (Line line : lines) {
            Point lineMiddlePoint = line.middle();
            drawSurface.setColor(Color.BLACK);
            drawSurface.drawLine(
                (int) line.start().getX(),
                (int) line.start().getY(),
                (int) line.end().getX(),
                (int) line.end().getY()
            );
            drawSurface.setColor(Color.BLUE);
            drawSurface.fillCircle(
                (int) lineMiddlePoint.getX(),
                (int) lineMiddlePoint.getY(),
                radiusOfPoint
            );
        }
        gui.show(drawSurface);
    }
    /**
     * Runs drawRandomCircles.
     * @param args
     */
    public static void main(String[] args) {
        AbstractArtDrawing runner = new AbstractArtDrawing();
        runner.drawRandomLines(1920, 1080, 10, 3);
    }
}