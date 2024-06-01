/**
 * Forced to create this JDOC due to checkstyles.
 */
public class Container {
    private Point topLeft;
    private Point topRight;
    private Point bottomLeft;
    private Point bottomRight;

    /**
     * Constructor of the Container class.
     * @param topLeft the topLeft Point of the Container
     * @param width the width of the Container
     * @param height the height of the Container
     */
    public Container(Point topLeft, int width, int height) {
        this.topLeft = new Point(topLeft);
        this.topRight = new Point(topLeft.getX() + width, topLeft.getY());
        this.bottomLeft = new Point(topLeft.getX(), topLeft.getY() + height);
        this.bottomRight = new Point(topLeft.getX() + width, topLeft.getY() + height);
    }
    /**
     * Constructor of the Container class.
     * @param topLeft topLeft Point of the container
     * @param bottomRight bottomRight Point of the container
     */
    public Container(Point topLeft, Point bottomRight) {
        this(
            topLeft,
            (int) (bottomRight.getX() - topLeft.getX()),
            (int) (bottomRight.getY() - topLeft.getY())
        );
    }
    /**
     * Constructor of the Container class.
     * @param container
     */
    public Container(Container container) {
        this(container.getTopLeft(), container.getWidth(), container.getHeight());
    }
    /**
     * Calculates the width of the Container.
     * @return the width of the Container
     */
    public int getWidth() {
        return (int) (this.topRight.getX() - this.topLeft.getX());
    }
    /**
     * Calculates the height of the Container.
     * @return the height of the Container
     */
    public int getHeight() {
        return (int) (this.bottomLeft.getY() - this.topLeft.getY());
    }
    /**
     * Getter of the topLeft Point.
     * @return the topLeft Point
     */
    public Point getTopLeft() {
        return new Point(this.topLeft);
    }
    /**
     * Getter of the bottomRight Point.
     * @return the bottomRight Point
     */
    public Point getBottomRight() {
        return new Point(this.bottomRight);
    }
}