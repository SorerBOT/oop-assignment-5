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
     * The method clones the Collidable object, similarlyj to a copy constructor.
     * @return a copy of the Collidable object
     */
    Collidable cloneDeep();
}