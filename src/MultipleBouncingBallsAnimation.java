import java.util.Random;
import java.awt.Color;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;
/**
 * Forced to create this JDOC due to checkstyles.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * Generates random Ball.
     * @param sizesArray an array featuring the sizes of the Balls
     * which are ought to be created. (represented using Strings)
     * @return an array of Balls
     */
    public static Ball[] generateRandomBalls(String[] sizesArray) {
        Random random = new Random();
        Ball[] balls = new Ball[sizesArray.length];
        for (int i = 0; i < sizesArray.length; i++) {
            Ball ball = new Ball(Integer.parseInt(sizesArray[i]), Color.BLACK);
            int speed = Math.max(11 - ball.getSize() / 5, 1) / 2;
            // nextDouble(x) returns a number between 0 and x-1, adding 1
            // not using 361 for the sake of simplicity
            double angle = random.nextDouble(360 + 1);
            ball.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
            balls[i] = ball;
        }
        return balls;
    }
    /**
     * Creates an animation featuring multiple balls.
     * @param args array of CLI arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        GUI gui = new GUI("Multiple Bouncing Balls Animation", Screen.WIDTH, Screen.HEIGHT);
        Ball[] balls = generateRandomBalls(args);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
}