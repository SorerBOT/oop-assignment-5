import java.awt.Color;
/**
 * Forced to create this JDOC due to checkstyles.
 */
public class ContainedBall extends Ball {
    private Container container;
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
                Math.abs(this.container.getTopLeft().getY() - this.getY())
            )
            || ThresholdCompare.isThresholdBasedGreaterEqual(
                this.getSize(),
                Math.abs(this.container.getBottomRight().getY() - this.getY())
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
                Math.abs(this.container.getTopLeft().getX() - this.getX())
            )
            || ThresholdCompare.isThresholdBasedGreaterEqual(
                this.getSize(),
                Math.abs(this.container.getBottomRight().getX() - this.getX())
            )
        );
    }
}