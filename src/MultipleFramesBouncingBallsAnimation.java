import java.awt.Color;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;
/**
 * Forced to create this JDOC due to checkstyles.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * Creates an animation featuring multiple frames and balls.
     * @param args array of CLI arguments
     */
    public static void main(String[] args) {
        if (args.length == 0 || args.length % 2 == 1) {
            return;
        }
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", 800, 800);
        DrawSurface drawSurface = gui.getDrawSurface();
        drawSurface.setColor(Color.GRAY);
        drawSurface.fillRectangle(50, 50, 450, 450);
        drawSurface.setColor(Color.YELLOW);
        drawSurface.fillRectangle(450, 450, 200, 200);
        gui.show(drawSurface);
    }
}