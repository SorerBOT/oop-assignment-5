import java.util.Random;
import java.awt.Color;
/**
 * Forced to create this JDOC due to checkstyles.
 */
public class NonContainedBall extends Ball {
    /**
     * Determines if the Point is valid.
     * @param x the x value of the Point
     * @param y the y value of the Point
     * @param radius the radius of the Circle
     * @return true if the Point is valid and false otherwise
     */
    public static boolean isGeneratedPointValid(double x, double y, int radius) {
        boolean touchingGray = (
            ThresholdCompare.isThresholdBasedGreaterEqual(x, 50 - radius)
            && ThresholdCompare.isThresholdBasedGreaterEqual(500 + radius, x)
            && ThresholdCompare.isThresholdBasedGreaterEqual(y, 50 - radius)
            && ThresholdCompare.isThresholdBasedGreaterEqual(500 + radius, y)
        );
        boolean touchingYellow = (
            ThresholdCompare.isThresholdBasedGreaterEqual(x, 450 - radius)
            && ThresholdCompare.isThresholdBasedGreaterEqual(650 + radius, x)
            && ThresholdCompare.isThresholdBasedGreaterEqual(y, 450 - radius)
            && ThresholdCompare.isThresholdBasedGreaterEqual(650 + radius, y)
        );
        boolean touchingScreen = (
            ThresholdCompare.isThresholdBasedGreaterEqual(radius, x)
            || ThresholdCompare.isThresholdBasedGreaterEqual(radius, y)
            || ThresholdCompare.isThresholdBasedGreaterEqual(x, 800 - radius)
            || ThresholdCompare.isThresholdBasedGreaterEqual(y, 800 - radius)
        );
        return !(touchingGray || touchingYellow || touchingScreen);
    }
    /**
     * Random Ball constructor.
     * @param r the radius of the Ball
     * @param color the color of the Ball
     */
    public NonContainedBall(int r, Color color) {
        Random random = new Random();
        this.setRadius(r);
        this.setColor(color);
        this.setVelocity(new Velocity());
        boolean found = false;
        while (!found) {
            Point randomPoint = new Point(
                random.nextDouble(800 + 1),
                random.nextDouble(800 + 1)
            );
            if (NonContainedBall.isGeneratedPointValid(
                    randomPoint.getX(),
                    randomPoint.getY(),
                    r
                )
            ) {
                this.setCenter(randomPoint);
                found = true;
            }
        }
    }
    /**
     * Determines whether the Ball has a horizontal collision.
     * @return true if there is a collision and false otherwise
     */
    public boolean isTouchingHorizontally() {
        boolean isTouchingGreyLeft = (
            (
                ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getY(), 50)
                && ThresholdCompare.isThresholdBasedGreaterEqual(500, this.getCenter().getY())
                && ThresholdCompare.isThresholdBasedGreaterEqual(50, this.getCenter().getX())
                && ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getX(), 0)
                && ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getX(), 50 - this.getSize())
            )
        );
        boolean isTouchingGreyRight = (
            (
                ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getY(), 50)
                && ThresholdCompare.isThresholdBasedGreaterEqual(500, this.getCenter().getY())
                && ThresholdCompare.isThresholdBasedGreaterEqual(800, this.getCenter().getX())
                && ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getX(), 500)
                && ThresholdCompare.isThresholdBasedGreaterEqual(500 + this.getSize(), this.getCenter().getX())
            )
        );
        boolean isTouchingYellowLeft = (
            ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getY(), 500)
            && ThresholdCompare.isThresholdBasedGreaterEqual(650, this.getCenter().getY())
            && ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getX(), 0)
            && ThresholdCompare.isThresholdBasedGreaterEqual(450, this.getCenter().getX())
            && ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getX(), 450 - this.getSize())
        );
        boolean isTouchingYellowRight = (
            ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getY(), 450)
            && ThresholdCompare.isThresholdBasedGreaterEqual(650, this.getCenter().getY())
            && ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getX(), 650)
            && ThresholdCompare.isThresholdBasedGreaterEqual(800, this.getCenter().getX())
            && ThresholdCompare.isThresholdBasedGreaterEqual(650 + this.getSize(), this.getCenter().getX())
        );
        boolean isTouchingScreen = (
            ThresholdCompare.isThresholdBasedGreaterEqual(this.getSize(), this.getCenter().getX())
            || ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getX(), 800 - this.getSize())
        );
        return (
            isTouchingGreyLeft
            || isTouchingGreyRight
            || isTouchingYellowLeft
            || isTouchingYellowRight
            || isTouchingScreen
        );
    }
    /**
     * Determines whether the Ball has a vertical collision.
     * @return true if there is a collision and false otherwise
     */
    public boolean isTouchingVertically() {
        boolean isTouchingScreen = (
            ThresholdCompare.isThresholdBasedGreaterEqual(this.getSize(), this.getCenter().getY())
            || ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getY(), 800 - this.getSize())
        );
        boolean isTouchingGreyTop = (
            ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getY(), 0)
            && ThresholdCompare.isThresholdBasedGreaterEqual(50, this.getCenter().getY())
            && ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getX(), 50)
            && ThresholdCompare.isThresholdBasedGreaterEqual(500, this.getCenter().getX())
            && ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getY(), 50 - this.getSize())
        );
        boolean isTouchingGreyBottom = (
            ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getY(), 500)
            && ThresholdCompare.isThresholdBasedGreaterEqual(800, this.getCenter().getY())
            && ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getX(), 50)
            && ThresholdCompare.isThresholdBasedGreaterEqual(450, this.getCenter().getX())
            && ThresholdCompare.isThresholdBasedGreaterEqual(500 + this.getSize(), this.getCenter().getY())
        );
        boolean isTouchingYellowTop = (
            ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getY(), 50)
            && ThresholdCompare.isThresholdBasedGreaterEqual(450, this.getCenter().getY())
            && ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getX(), 500)
            && ThresholdCompare.isThresholdBasedGreaterEqual(650, this.getCenter().getX())
            && ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getY(), 450 - this.getSize())
        );
        boolean isTouchingYellowBottom = (
            ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getY(), 650)
            && ThresholdCompare.isThresholdBasedGreaterEqual(800, this.getCenter().getY())
            && ThresholdCompare.isThresholdBasedGreaterEqual(this.getCenter().getX(), 450)
            && ThresholdCompare.isThresholdBasedGreaterEqual(650, this.getCenter().getX())
            && ThresholdCompare.isThresholdBasedGreaterEqual(450 + this.getSize(), this.getCenter().getY())
        );
        return (
            isTouchingGreyBottom
            || isTouchingGreyTop
            || isTouchingYellowBottom
            || isTouchingYellowTop
            || isTouchingScreen
        );
    }
    /**
     * moves the center Point one step.
     */
    public void moveOneStep() {
        if (this.isTouchingVertically()) {
            this.setVelocity(new Velocity(
                this.getVelocity().getDx(),
                -this.getVelocity().getDy()
            ));
        }
        if (this.isTouchingHorizontally()) {
            this.setVelocity(new Velocity(
                -this.getVelocity().getDx(),
                this.getVelocity().getDy()
            ));
        }
        this.setCenter(this.getVelocity().applyToPoint(this.getCenter()));
    }
}