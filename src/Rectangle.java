/**
 * The Rectangle class.
 */
public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point bottomLeft;
    private Point bottomRight;
    private double width;
    private double height;
    /**
     * Constructor of Rectangle.
     * @param upperLeft the upperLeft Point of the Rectangle
     * @param width the width of the Rectangle
     * @param height the height of the Rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft);
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.width = width;
        this.height = height;
    }
    /**
     * Return a (possibly empty) List of intersection points with the specified Line.
     * @param line the Line for which we must find intersection Points
     * @return a possibly empty list of intersection Points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> list = new java.util.ArrayList<Point>();

        Line leftSide = new Line(this.upperLeft, this.bottomLeft);
        Line rightSide = new Line(this.upperRight, this.bottomRight);
        Line topSide = new Line(this.upperLeft, this.upperRight);
        Line bottomSide = new Line(this.bottomLeft, this.bottomRight);

        if (
            line.isLineColliding(leftSide)
            || line.isLineColliding(rightSide)
            || line.isLineColliding(topSide)
            || line.isLineColliding(bottomSide)
        ) {
            return null;
        }

        Point p1, p2, p3, p4;
        p1 = leftSide.intersectionWith(line);
        p2 = rightSide.intersectionWith(line);
        p3 = topSide.intersectionWith(line);
        p4 = bottomSide.intersectionWith(line);
        if (p1 != null) {
            list.add(p1);
        }
        if (p2 != null) {
            list.add(p2);
        }
        if (p3 != null) {
            list.add(p3);
        }
        if (p4 != null) {
            list.add(p4);
        }
        return list;
    }
    /**
     * Getter of the Rectangle's width.
     * @return the width of the Rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Getter of the Rectangle's height.
     * @return the height of the Rectangle
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Getter of the Rectangle's upperLeft Point.
     * @return the upperLeft Point of the Rectangle
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft);
    }
}