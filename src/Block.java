import java.awt.Color;
import biuoop.DrawSurface;

/**
 * The Block class.
 */
public class Block implements Collidable {
    private final Rectangle shape;
    /**
     * Constructor of the Block class.
     * @param rectangle the collision Rectangle of the Block
     */
    public Block(Rectangle rectangle) {
        this.shape = new Rectangle(rectangle);
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.shape);
    }
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        int horizontalVelocityScalar = this.isCollidingHorizontally(collisionPoint) ? -1 : 1;
        int verticalVelocityScalar = this.isCollidingVertically(collisionPoint) ? -1 : 1;
        return new Velocity(
            horizontalVelocityScalar * currentVelocity.getDx(),
            verticalVelocityScalar * currentVelocity.getDy()
        );
    }
    @Override
    public boolean isCollidingHorizontally(Point collisionPoint) {
        return (
            ThresholdCompare.isThresholdBasedGreaterEqual(collisionPoint.getY(), this.shape.getUpperLeft().getY())
            && ThresholdCompare.isThresholdBasedGreaterEqual(this.shape.getBottomLeft().getY(), collisionPoint.getY())
            && (
                ThresholdCompare.isThresholdBasedEquals(this.shape.getUpperLeft().getX(), collisionPoint.getX())
                || ThresholdCompare.isThresholdBasedEquals(this.shape.getUpperRight().getX(), collisionPoint.getX())
            )
        );
    }
    @Override
    public boolean isCollidingVertically(Point collisionPoint) {
        return (
            ThresholdCompare.isThresholdBasedGreaterEqual(collisionPoint.getX(), this.shape.getUpperLeft().getX())
            && ThresholdCompare.isThresholdBasedGreaterEqual(this.shape.getUpperRight().getX(), collisionPoint.getX())
            && (
                ThresholdCompare.isThresholdBasedEquals(this.shape.getUpperLeft().getY(), collisionPoint.getY())
                || ThresholdCompare.isThresholdBasedEquals(this.shape.getBottomLeft().getY(), collisionPoint.getY())
            )
        );
    }
}
