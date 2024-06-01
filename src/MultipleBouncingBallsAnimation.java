import java.awt.Color;
import biuoop.GUI;
/**
 * Forced to create this JDOC due to checkstyles.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * Generates random balls.
     * @param sizesArray an array featuring the sizes of the Balls
     * which are ought to be created. (represented using Strings)
     * @return an array of Balls
     */
    public static Ball[] generateRandomBalls(String[] sizesArray) {
        Ball[] balls = new Ball[sizesArray.length];
        for (int i = 0; i < sizesArray.length; i++) {
            balls[i] = new Ball(Integer.parseInt(sizesArray[i]), Color.BLACK);
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
        GUI gui = new GUI("Multiple BOuncing Balls Animation", Screen.WIDTH, Screen.HEIGHT);
        Ball[] balls = generateRandomBalls(args);
    }
}