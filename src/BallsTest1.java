import biuoop.GUI;
import biuoop.DrawSurface;
/**
 * Forced to create this JDOC due to checkstyles.
 */
public class BallsTest1 {
    /**
     * Draws an animation.
     */
    public static void drawAnimation() {
        GUI gui = new GUI("title", 1920, 1080);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        java.util.Random rand = new java.util.Random();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            Ball ball = new Ball(rand.nextInt(1920), rand.nextInt(1080), 30, java.awt.Color.BLACK);
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
    /**
     * Bafoonery.
     */
    public static void main(String[] args) {
        drawAnimation();
        return;
        //GUI gui = new GUI("Balls Test 1",  400,  400);
        //DrawSurface d = gui.getDrawSurface();
        //Ball b1 = new Ball(new Point(100, 100), 30, java.awt.Color.RED);
        //Ball b2 = new Ball(new Point(100, 150), 10, java.awt.Color.BLUE);
        //Ball b3 = new Ball(new Point(80, 249), 50, java.awt.Color.GREEN);
        //b1.drawOn(d);
        //b2.drawOn(d);
        //b3.drawOn(d);
        //gui.show(d);
    }
}