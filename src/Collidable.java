/**
 * The Collidable interface.
 */
public interface Collidable {
    /**
     * Finds the collision Rectangle.
     * @return the collision Rectangle
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the hitted object.
     * @param collisionPoint the Point of collision
     * @param currentVelocity the velocity of the collision
     * @param hitter the Ball responsible for the hit
     * @return the new Velocity of the hitting object
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
    /**
     * Generates a String corresponding to the Collidable.
     * @return the String generated
     */
    String toString();
}