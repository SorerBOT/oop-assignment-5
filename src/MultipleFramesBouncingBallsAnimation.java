import java.util.Random;
import java.awt.Color;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;
/**
 * Forced to create this JDOC due to checkstyles.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * Generates random ContainedBalls.
     * @param sizesArray an array featuring the sizes of the ContainedBalls
     * which are ought to be created. (represented using Strings)\
     * @param container the Container in which the Balls should reside
     * @return an array of ContainedBalls
     */
    public static ContainedBall[] generateRandomBalls(String[] sizesArray, Container container) {
        Random random = new Random();
        int lengthOfContainedBalls = sizesArray.length / 2;
        ContainedBall[] balls = new ContainedBall[lengthOfContainedBalls];
        for (int i = 0; i < lengthOfContainedBalls; i++) {
            ContainedBall ball = new ContainedBall(Integer.parseInt(sizesArray[i]), Color.RED, container);
            int speed = Math.max(1, 50 / ball.getSize());
            // nextDouble(x) returns a number between 0 and x-1, adding 1
            // not using 361 for the sake of simplicity
            double angle = random.nextDouble(360 + 1);
            ball.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
            balls[i] = ball;
        }
        return balls;
    }
    /**
     * Creates an animation featuring multiple frames and balls.
     * @param args array of CLI arguments
     */
    public static void main(String[] args) {
        if (args.length == 0 || args.length % 2 == 1) {
            return;
        }
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", 800, 800);
        Sleeper sleeper = new Sleeper();
        Container greyContainer = new Container(new Point(50, 50), new Point(500, 500));
        Container yellowContainer = new Container(new Point(450, 450), new Point(650, 650));
        Ball[] balls = generateRandomBalls(args, greyContainer);

        while (true) {
            DrawSurface drawSurface = gui.getDrawSurface();
            drawSurface.setColor(Color.GRAY);
            drawSurface.fillRectangle(
                (int) greyContainer.getTopLeft().getX(),
                (int) greyContainer.getTopLeft().getY(),
                greyContainer.getWidth(),
                greyContainer.getHeight()
            );
            drawSurface.setColor(Color.YELLOW);
            drawSurface.fillRectangle(
                (int) yellowContainer.getTopLeft().getX(),
                (int) yellowContainer.getTopLeft().getY(),
                yellowContainer.getWidth(),
                yellowContainer.getHeight()
            );
            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(drawSurface);
            }
            gui.show(drawSurface);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
}