import java.util.Random;
import java.awt.Color;
/**
 * Forced to create this JDOC due to checkstyles.
 */
public class ContainedBall extends Ball {
    private Container container;
    /**
     * Random Ball constructor.
     * @param r the radius of the Ball
     * @param color the color of the Ball
     * @param container the Container in which the Ball should reside
     */
    public ContainedBall(int r, Color color, Container container) {
        Random random = new Random();
        this.setRadius(r);
        this.setColor(color);
        this.setVelocity(new Velocity());
        this.container = new Container(container);
        this.setCenter(
            new Point(
                random.nextInt(this.container.getWidth() + 1) + this.container.getTopLeft().getX(),
                random.nextInt(this.container.getHeight() + 1) + this.container.getTopLeft().getY()
            )
        );
    }
    /**
     * Constructor of the ContainedBall class.
     * @param center the center of the ContainedBall
     * @param radius the radius of the ContainedBall
     * @param color the color of the ContainedBall
     * @param container the Container inside which the ContainedBall is trapped
     */
    public ContainedBall(Point center, int radius, Color color, Container container) {
        super(center, radius, color);
        this.container = new Container(container);
    }
    /**
     * Determines whether the ContainedBall touches the Container vertically.
     * @return true if they touch and false otherwise
     */
    public boolean isTouchingContainerVertically() {
        return (
            ThresholdCompare.isThresholdBasedGreaterEqual(
                this.getSize(),
                this.getY() - this.container.getTopLeft().getY()
            )
            || ThresholdCompare.isThresholdBasedGreaterEqual(
                this.getSize(),
                this.container.getBottomRight().getY() - this.getY()
            )
        );
    }
    /**
     * Determines whether the ContainedBall touches the Container horizontally.
     * @return true if they touch and false otherwise
     */
    public boolean isTouchingContainerHorizontally() {
        return (
            ThresholdCompare.isThresholdBasedGreaterEqual(
                this.getSize(),
                this.getX() - this.container.getTopLeft().getX()
            )
            || ThresholdCompare.isThresholdBasedGreaterEqual(
                this.getSize(),
                this.container.getBottomRight().getX() - this.getX()
            )
        );
    }
    /**
     * moves the center Point one step.
     */
    public void moveOneStep() {
        if (this.isTouchingContainerVertically()) {
            this.setVelocity(new Velocity(
                this.getVelocity().getDx(),
                -this.getVelocity().getDy()
            ));
        }
        if (this.isTouchingContainerHorizontally()) {
            this.setVelocity(new Velocity(
                -this.getVelocity().getDx(),
                this.getVelocity().getDy()
            ));
        }
        this.setCenter(this.getVelocity().applyToPoint(this.getCenter()));
    }
}