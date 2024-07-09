package game.objects;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import biuoop.DrawSurface;

import utils.ThresholdCompare;

import geometry.Rectangle;
import geometry.Point;

import game.Game;
import game.hitlisteners.HitListener;
import game.hitlisteners.HitNotifier;
/**
 * The Block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle shape;
    private final Color color;
    private final List<HitListener> hitListeners;
    /**
     * Constructor of the Block class.
     * @param rectangle the collision Rectangle of the Block
     * @param color the Color of the Block
     */
    public Block(Rectangle rectangle, Color color) {
        this.shape = new Rectangle(rectangle);
        this.color = new Color(color.getRGB());
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * Constructor of the Block class.
     * @param rectangle the Rectangle of the Block
     */
    public Block(Rectangle rectangle) {
        this(rectangle, Color.BLACK);
    }
    /**
     * Whenever a hit occurs, notifies all the registered HitListeners.
     * @param hitter the Ball responsible for the hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * Adds the Block to the Game.
     * @param g the game to which the Block is to be added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Getter of the hitListener field.
     * @return the hitListener field
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * Adds multiple HitListener.
     * @param hitListeners an array of HitListeners
     */
    public void addHitListener(HitListener[] hitListeners) {
        for (HitListener hitListener : hitListeners) {
            this.addHitListener(hitListener);
        }
    }
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.shape);
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int horizontalVelocityScalar = this.isCollidingHorizontally(collisionPoint) ? -1 : 1;
        int verticalVelocityScalar = this.isCollidingVertically(collisionPoint) ? -1 : 1;

        this.notifyHit(hitter);

        return new Velocity(
            horizontalVelocityScalar * currentVelocity.getDx(),
            verticalVelocityScalar * currentVelocity.getDy()
        );
    }
    /**
     * Determines if there is a horizontal collision.
     * @param collisionPoint the Point suspected of collision
     * @return true if there is a collision and false otherwise
     */
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
    /**
     * Determines if there is a vertical collision.
     * @param collisionPoint the Point suspected of collision
     * @return true if there is a collision and false otherwise
     */
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
    /**
     * Compares the color of the Block with the color of the Ball.
     * @param ball the Ball to be compared with
     * @return true if the colors are equals and false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return (
            this.color.getRed() == ball.getColor().getRed()
            && this.color.getGreen() == ball.getColor().getGreen()
            && this.color.getBlue() == ball.getColor().getBlue()
        );
    }
    /**
     * Removes the Block from the Game.
     * @param g the Game from which the Block should be removed
     */
    public void removeFromGame(Game g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }
    /**
     * Getter of the color field.
     * @return the Color of the Block
     */
    public Color getColor() {
        return new Color(this.color.getRGB());
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
    public void timePassed() {
        return;
    }
    @Override
    public String toString() {
        return (
            String.valueOf(this.shape.getUpperLeft().getX())
            + ", "
            + String.valueOf(this.shape.getUpperLeft().getY())
        );
    }
}
