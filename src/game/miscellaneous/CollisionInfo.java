package game.miscellaneous;

import geometry.Point;

import game.objects.Collidable;
/**
 * CollisionInfo class.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Constructor of the CollisionInfo class.
     * @param collisionPoint the Point of collision
     * @param collisionObject the Collidable object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = new Point(collisionPoint);
        this.collisionObject = collisionObject;
    }
    /**
     * @return the Point at which the Collision occurs
     */
    public Point collisionPoint() {
        return new Point(this.collisionPoint);
    }
    /**
     * @return the Collidable object of the collision
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}