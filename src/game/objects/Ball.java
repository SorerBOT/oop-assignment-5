// Alon Filler 216872374

package game.objects;

import java.util.Random;
import java.awt.Color;

import biuoop.DrawSurface;

import utils.ThresholdCompare;

import geometry.Line;
import geometry.Point;

import game.Game;
import game.miscellaneous.GameEnvironment;
import game.miscellaneous.Screen;
import game.miscellaneous.CollisionInfo;

/**
 * Forced to create this JDOC due to checkstyles.
 */
public class Ball implements Sprite {
    private Point center;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private Paddle paddle;
    private final int radius;
    private Color color;

    /**
     * Constructor of the Ball class.
     * @param center the center of the Ball
     * @param r the radius of the Ball
     * @param color the color of the Ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = new Point(center);
        this.radius = r;
        this.color = new Color(
            color.getRed(),
            color.getGreen(),
            color.getBlue(),
            color.getAlpha()
        );
        this.velocity = new Velocity();
        this.gameEnvironment = null;
        this.paddle = null;
    }
    /**
     * Empty constructor of the Ball class.
     * The reason why this code is needed is the fact that CHECKSTYLES
     * won't allow using protected on the class.
     * This fact lead to a lot of spaghetti code, forcing me to use
     * getter and setters to mutate fields of the Ball class in its heir: ContainedBall
     */
    public Ball() {
        this(
            new Point(0, 0),
            0,
            new Color(0, 0, 0, 0)
        );
    }
    /**
     * Constructor of the Ball class.
     * @param x the X value of the Point
     * @param y the Y value of the Point
     * @param r the radius value of the Ball
     * @param color the color of the Ball
     */
    public Ball(double x, double y, int r, Color color) {
        this((int) x, (int) y, r, color);
    }
    /**
     * Constructs a Point then a Ball utilising it.
     * Calls the Point based constructor
     * @param x the X value of the Point
     * @param y the Y value of the Point
     * @param r the radius value of the Ball
     * @param color the color of the Ball
     */
    public Ball(int x, int y, int r, Color color) {
        this(new Point(x, y), r, color);
    }
    /**
     * Random Ball constructor.
     * @param r the radius of the Ball
     * @param color the color of the Ball
     */
    public Ball(int r, Color color) {
        Random random = new Random();
        boolean found = false;
        double x = 0, y = 0;

        this.radius = r;
        this.color = color;
        this.velocity = new Velocity();
        this.gameEnvironment = null;
        this.paddle = null;

        while (!found) {
            x = random.nextInt(Screen.WIDTH + 1);
            y = random.nextInt(Screen.HEIGHT + 1);
            boolean isTouchingScreen = (
                ThresholdCompare.isThresholdBasedGreaterEqual(this.radius, x)
                && ThresholdCompare.isThresholdBasedGreaterEqual(this.radius, y)
                && ThresholdCompare.isThresholdBasedGreaterEqual(x, Screen.WIDTH - this.radius)
                && ThresholdCompare.isThresholdBasedGreaterEqual(y, Screen.HEIGHT - this.radius)
            );
            if (!isTouchingScreen) {
                this.center = new Point(x, y);
                found = true;
            }
        }
    }
    /**
     * Returns the X value of the center Point.
     * @return the X value of the center Point
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * Returns the Y value of the center Point.
     * @return the Y value of the center Point
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * Returns the radius of the Ball.
     * @return the radius of the Ball
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * Returns the color of the Ball.
     * @return Returns the color of the Ball
     */
    public Color getColor() {
        return new Color(
            color.getRed(),
            color.getGreen(),
            color.getBlue(),
            color.getAlpha()
        );
    }
    /**
     * Changes the Velocity of the Ball.
     * @param v the new Velocity of the Ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v);
    }
    /**
     * Changes the Velocity of the Ball.
     * @param dx the new dx value of the Velocity
     * @param dy the new dy value of the Velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**
     * Getter of the center of the Ball.
     * @return the center of the Ball
     */
    public Point getCenter() {
        return this.center;
    }
    /**
     * Sets the center of the Ball.
     * @param newCenter the new center for the Ball
     */
    public void setCenter(Point newCenter) {
        this.center = new Point(newCenter);
    }
    /**
     * moves the center Point one step.
     */
    public void moveOneStep() {
        final double proximityToCollision = 0.9;
        Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);

        if (this.isInPaddle()) {
            // Teleport the ball upwards so that it is no longer inside of the Paddle.
            this.center.setY(this.paddle.getCollisionRectangle().getUpperLeft().getY() - 1);
            return;
        }
        if (collisionInfo == null) {
            this.center = this.velocity.applyToPoint(this.center);
            return;
        }

        Collidable collidable = collisionInfo.collisionObject();
        Point point = collisionInfo.collisionPoint();
        this.center = this.velocity.moveNearCollision(this.center, point, proximityToCollision);

        this.velocity = collidable.hit(this, point, this.velocity);
    }
    /**
     * Computes the trajectory of the Ball.
     * @return a Line drawn between the centre of the Ball and its position subsequent to the next movement.
     */
    public Line getTrajectory() {
        return new Line(
            this.center,
            this.velocity.applyToPoint(this.center)
        );
    }
    /**
     * Determines whether the Ball is in the Paddle.
     * @return true if the Ball is in the Paddle and false otherwise
     */
    public boolean isInPaddle() {
        final double paddleTopY = this.paddle.getCollisionRectangle().getUpperLeft().getY();
        final double paddleBottomY = this.paddle.getCollisionRectangle().getBottomLeft().getY();
        final double paddleLeftX = this.paddle.getCollisionRectangle().getUpperLeft().getX();
        final double paddleRightX = this.paddle.getCollisionRectangle().getUpperRight().getX();

        return (
            ThresholdCompare.isThresholdBasedGreaterEqual(this.center.getY(), paddleTopY)
            && ThresholdCompare.isThresholdBasedGreaterEqual(paddleBottomY, this.center.getY())
            && ThresholdCompare.isThresholdBasedGreaterEqual(this.center.getX(), paddleLeftX)
            && ThresholdCompare.isThresholdBasedGreaterEqual(paddleRightX, this.center.getX())
        );
    }
    /**
     * Adds the Ball to the Game.
     * @param g the Game to which the Ball is ought to be added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        this.gameEnvironment = g.getEnvironment();
        this.paddle = g.getPaddle();
    }
    /**
     * Removes the Ball from the Game.
     * @param g the Game from which the Ball should be removed
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
    /**
     * Setter of the color field.
     * @param color the new Color
     */
    public void setColor(Color color) {
        this.color = new Color(color.getRGB());
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(
            this.getX(),
            this.getY(),
            this.radius
        );
    }
    @Override
    public void timePassed() {
        this.moveOneStep();
    }
}