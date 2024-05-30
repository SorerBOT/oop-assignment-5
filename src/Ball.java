import java.awt.Color;
import biuoop.DrawSurface;
/**
 * Forced to create this JDOC due to checkstyles.
 */
public class Ball {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;

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
     * Draws the Ball on the surface.
     * @param surface the surface on which the Ball must be drawn
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(
            this.getX(),
            this.getY(),
            this.radius
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
     * Returns the Velocity object of the Ball.
     * @return the velocity object of the Ball
     */
    public Velocity getVelocity() {
        return new Velocity(this.velocity);
    }
    /**
     * moves the center Point one step.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }
}