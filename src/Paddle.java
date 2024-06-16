import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * The Paddle class.
 */
public class Paddle implements Sprite, Collidable {
    private final Rectangle shape = new Rectangle(new Point(350, 575), 125, 25);
    private final Color color;
    private KeyboardSensor keyboard;
    /**
     * Empty constructor.
     */
    public Paddle() {
        this.color = Color.BLACK;
    }
    /**
     * Constructor of the Paddle class.
     * @param color the color of the Paddle
     */
    public Paddle(Color color) {
        this.color = new Color(color.getRGB());
    }

    /**
     * Moves the paddle to the left.
     * in case that the Paddle is expected has left the boundaries of the screen
     * we are to reposition so as to make it appear to have returned from the other side
     */
    public void moveLeft() {
        if (this.shape.getUpperRight().getX() < 0) {
            this.shape.setUpperLeftXValue(800);
        }
        shape.setUpperLeftXValue(shape.getUpperLeft().getX() - 8);
    }

    /**
     * Moves the paddle to the right.
     * in case that the Paddle is expected has left the boundaries of the screen
     * we are to reposition so as to make it appear to have returned from the other side
     */
    public void moveRight() {
        if (this.shape.getUpperLeft().getX() > 800) {
            this.shape.setUpperLeftXValue(-this.shape.getWidth());
        }
        shape.setUpperLeftXValue(shape.getUpperLeft().getX() + 8);
    }
    /**
     * Determines the region in which the collision.
     * @param collisionPoint The Point of collision
     * @return The number of the region in which the Point of collision resides
     */
    public int getRegionOfCollision(Point collisionPoint) {
        final double upperLeftXValue = this.shape.getUpperLeft().getX();
        final double paddleWidth = this.shape.getWidth();
        final double regionLength = 0.2 * paddleWidth;

        for (int regionNumber = 1; regionNumber < 5; regionNumber++) {
            if (collisionPoint.getX() < upperLeftXValue + regionNumber * regionLength) {
                return regionNumber;
            }
        }
        return 5;
    }
    /**
     * Adds Paddle to the Game.
     * @param g the Game to which the Paddle is ought to be added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
        this.keyboard = g.getGui().getKeyboardSensor();
    }
    @Override
    // Responsible for the movement of the Paddle.
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }

        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
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
        d.setColor(Color.BLACK);
        d.drawRectangle(
            (int) this.shape.getUpperLeft().getX(),
            (int) this.shape.getUpperLeft().getY(),
            (int) this.shape.getWidth(),
            (int) this.shape.getHeight()
        );
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.shape);
    }
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        int region;
        final int projectionAngle;

        Block block = new Block(this.shape);
        final double currentSpeed = Math.sqrt(
            Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2)
        );

        if (block.isCollidingHorizontally(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        region = this.getRegionOfCollision(collisionPoint);
        // The initial angle is 60 degrees
        // for each increase in the zone's number, add 30 degrees, starting from 60
        projectionAngle = 60 - (region - 1) * 30;
        return Velocity.fromAngleAndSpeed(projectionAngle, currentSpeed);
    }
    // We would not like to clone the Paddle, and thus we return the same Paddle
    @Override
    public Collidable cloneDeep() {
        return this;
    }
    @Override
    public String toString() {
        return String.valueOf(this.shape.getUpperLeft().getX()) + ", " + String.valueOf(this.shape.getUpperLeft().getY());
    }
}