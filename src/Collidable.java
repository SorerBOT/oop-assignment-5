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
     * @return the new Velocity of the hitting object
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
    /**
     * Determines if there is a horizontal collision.
     * @param collisionPoint the Point suspected of collision
     * @return true if there is a collision and false otherwise
     */
    boolean isCollidingHorizontally(Point collisionPoint);
    /**
     * Determines if there is a horizontal collision.
     * @param collisionPoint the Point suspected of collision
     * @return true if there is a collision and false otherwise
     */
    boolean isCollidingVertically(Point collisionPoint);
}