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
}