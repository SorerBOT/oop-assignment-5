// Alon Filler 216872374
import java.util.Random;
import java.awt.Color;
import biuoop.GUI;
import biuoop.DrawSurface;
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
                random.nextInt(width + 1),
                random.nextInt(height + 1),
                random.nextInt(width + 1),
                random.nextInt(height + 1)
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
        GUI gui = new GUI("Abstract Art Drawing", width, height);
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

        for (int i = 0; i < lines.length - 1; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                Point intersectionPoint = lines[i].intersectionWith(lines[j]);
                if (intersectionPoint == null) {
                    continue;
                }
                drawSurface.setColor(Color.RED);
                drawSurface.fillCircle(
                    (int) intersectionPoint.getX(),
                    (int) intersectionPoint.getY(),
                    radiusOfPoint
                );
            }
        }

        for (int i = 0; i < lines.length - 2; i++) {
            for (int j = i + 1; j < lines.length - 1; j++) {
                for (int k = j + 1; k < lines.length; k++) {
                    Point firstTriangleVertex = lines[i].intersectionWith(lines[j]);
                    Point secondTriangleVertex = lines[i].intersectionWith(lines[k]);
                    Point thirdTriangleVertex = lines[j].intersectionWith(lines[k]);

                    if (
                        firstTriangleVertex == null
                        || secondTriangleVertex == null
                        || thirdTriangleVertex == null
                    ) {
                            continue;
                    }

                    Line firstTriangleSide = new Line(firstTriangleVertex, secondTriangleVertex);
                    Line secondTriangleSide = new Line(firstTriangleVertex, thirdTriangleVertex);
                    Line thirdTriangleSide = new Line(secondTriangleVertex, thirdTriangleVertex);

                    drawSurface.setColor(Color.GREEN);
                    drawSurface.drawLine(
                        (int) firstTriangleSide.start().getX(),
                        (int) firstTriangleSide.start().getY(),
                        (int) firstTriangleSide.end().getX(),
                        (int) firstTriangleSide.end().getY()
                    );
                    drawSurface.setColor(Color.GREEN);
                    drawSurface.drawLine(
                        (int) secondTriangleSide.start().getX(),
                        (int) secondTriangleSide.start().getY(),
                        (int) secondTriangleSide.end().getX(),
                        (int) secondTriangleSide.end().getY()
                    );
                    drawSurface.setColor(Color.GREEN);
                    drawSurface.drawLine(
                        (int) thirdTriangleSide.start().getX(),
                        (int) thirdTriangleSide.start().getY(),
                        (int) thirdTriangleSide.end().getX(),
                        (int) thirdTriangleSide.end().getY()
                    );
                }
            }
        }
        gui.show(drawSurface);
    }
    /**
     * Runs drawRandomCircles.
     * @param args
     */
    public static void main(String[] args) {
        AbstractArtDrawing runner = new AbstractArtDrawing();
        runner.drawRandomLines(400, 300, 10, 3);
    }
}