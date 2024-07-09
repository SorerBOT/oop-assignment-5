package game.objects;

import geometry.Point;

// Alon Filler 216872374
/**
 * Forced to create this JDOC due to checkstyles.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Returns the Velocity object's dx value.
     * @return the Velocity object's dx value
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * Returns the Velocity object's dy value.
     * @return the Velocity object's dy value
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * Sets the dx value of the Velocity.
     * @param dx the new value
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * Sets the dy value of the Velocity.
     * @param dy the new value
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
    /**
     * Empty constructor of Velocity class.
     */
    public Velocity() {
        this(0, 0);
    }
    /**
     * Constructor of the Velocity class.
     * @param dx delta of x
     * @param dy delta of y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Creates a new Velocity object from angle and speed.
     * @param angle the angle of the Velocity instance
     * @param speed the speed of the Velocity instance
     * @return the new Velocity instance
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Parses the angle provided to an angle which can be used in regular trigonometric calculations
        // (Up == 90 degrees)
        double normalAngle = angle + 90;
        double angleInRadians = (normalAngle * 2 * Math.PI) / 360;
        double dx = speed * Math.cos(angleInRadians);
        // Normal cartesian axis' consider upward motion to be represented by positive
        // change of coordinates.
        // The computer screen's representation of the axis' uses a Matrix
        // in which the positive change of coordinates represents downward motion
        double dy = -speed * Math.sin(angleInRadians);
        return new Velocity(dx, dy);
    }
    /**
     * Copy constructor of Velocity.
     * @param velocity the Velocity object to be copied
     */
    public Velocity(Velocity velocity) {
        this(velocity.getDx(), velocity.getDy());
    }
    /**
     * Slightly tweaks coordinates of the Point.
     * @param p a Point to which we must apply the effect
     * @return a new Point with the tweaked values
     */
    public Point applyToPoint(Point p) {
        return new Point(
            p.getX() + this.dx,
            p.getY() + this.dy
        );
    }
    /**
     * Procures a Point that is near the Point of collision between
     * between the Ball and the GameEnvironment collidables.
     * @param center the current center of the Ball
     * @param collision
     * @param changeParam
     * @return the new Point
     */
    public Point moveNearCollision(Point center, Point collision, double changeParam) {
        double newX = center.getX() + (collision.getX() - center.getX()) * changeParam;
        double newY = center.getY() + (collision.getY() - center.getY()) * changeParam;
        return new Point(newX, newY);
    }
}