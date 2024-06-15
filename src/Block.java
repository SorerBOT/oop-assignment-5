import java.awt.Color;
import biuoop.DrawSurface;
/**
 * The Block class.
 */
public class Block implements Collidable, Sprite {
    private final Rectangle shape;
    private final Color color;
    /**
     * Constructor of the Block class.
     * @param rectangle the collision Rectangle of the Block
     * @param color the Color of the Block
     */
    public Block(Rectangle rectangle, Color color) {
        this.shape = new Rectangle(rectangle);
        this.color = new Color(color.getRGB());
    }
    /**
     * Adds the Block to the Game.
     * @param g the game to which the Block is to be added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
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
    @Override
    public Block cloneDeep() {
        return new Block(this.shape, this.color);
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(
            (int) this.shape.getUpperLeft().getX(),
            (int) this.shape.getUpperLeft().getY(),
            (int) this.shape.getWidth(),
            (int) this.shape.getHeight()
        );
    }
    @Override
    public void timePassed() {
        return;
    }
}
