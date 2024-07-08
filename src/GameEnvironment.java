import java.util.ArrayList;
/**
 * GameEnvironment class.
 */
public class GameEnvironment {
    private final ArrayList<Collidable> collidables;

    /**
     * Constructor of the GameEnvironment class.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Given a Collidable, add it to the collidables list.
     * @param c The collidable to be added
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c.cloneDeep());
    }

    /**
     * Examines the trajectory in which the object is moving.
     * In case that the path of the object is to lead to its Collision with an element of the collidables array
     * Return the CollisionInfo of the closest object for which an intersection is to occur, otherwise return null
     * @param trajectory The trajectory of the moving object
     * @return CollisionInfo of the closest collision if there is any, null otherwise
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closestCollisionInfo = null;

        for (Collidable collidable : this.collidables) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collisionPoint == null) {
                continue;
            }
            if (closestCollisionInfo == null) {
                closestCollisionInfo = new CollisionInfo(collisionPoint, collidable);
                continue;
            } else {
                double currentDistance = trajectory.start().distance(collisionPoint);
                double minDistance = trajectory.start().distance(closestCollisionInfo.collisionPoint());

                if (currentDistance < minDistance) {
                    closestCollisionInfo = new CollisionInfo(collisionPoint, collidable);
                }
            }
        }
        return closestCollisionInfo;
    }
    /**
     * Getter of the collidables field.
     * @return the collidables field
     */
    public ArrayList<Collidable> getCollidables() {
        return new ArrayList<Collidable>(this.collidables);
    }
    /**
     * Removes the provided Collidable from the collidables list.
     * @param c the Collidable to be removed
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}