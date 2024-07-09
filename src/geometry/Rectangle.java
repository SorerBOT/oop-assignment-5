package geometry;

import java.util.ArrayList;

/**
 * The Rectangle class.
 */
public class Rectangle {
    private Point upperLeft;
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
        this.width = width;
        this.height = height;
    }
    /**
     * Copy constructor of the Rectangle class.
     * @param rectangle the Rectangle to be copied
     */
    public Rectangle(Rectangle rectangle) {
        this(rectangle.getUpperLeft(), rectangle.getWidth(), rectangle.getHeight());
    }
    /**
     * Return a (possibly empty) List of intersection points with the specified Line.
     * @param line the Line for which we must find intersection Points
     * @return a possibly empty list of intersection Points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> list = new ArrayList<Point>();

        Point p1, p2, p3, p4;

        if (
            line.isLineColliding(this.getLeftSide())
            || line.isLineColliding(this.getRightSide())
            || line.isLineColliding(this.getTopSide())
            || line.isLineColliding(this.getBottomSide())
        ) {
            return null;
        }

        p1 = this.getLeftSide().intersectionWith(line);
        p2 = this.getRightSide().intersectionWith(line);
        p3 = this.getTopSide().intersectionWith(line);
        p4 = this.getBottomSide().intersectionWith(line);

        if (p1 != null) {
            list.add(p1);
        }
        if (p2 != null) {
            list.add(p2);
        }
        if (p3 != null) {
            if (!p3.equals(p1) && !p3.equals(p2)) {
                list.add(p3);
            }
        }
        if (p4 != null) {
            if (!p4.equals(p1) && !p4.equals(p2)) {
                list.add(p4);
            }
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
    /**
     * Finds the Rectangle's upperRight Point.
     * @return the upperRight Point of the Rectangle
     */
    public Point getUpperRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY());
    }
    /**
     * Finds the Rectangle's bottomLeft Point.
     * @return the bottomLeft Point of the Rectangle
     */
    public Point getBottomLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY() + height);
    }
    /**
     * Finds the Rectangle's bottomRight Point.
     * @return the bottomRight Point of the Rectangle
     */
    public Point getBottomRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }
    /**
     * Creates a Line emulating the left side of the Rectangle.
     * @return The left side
     */
    public Line getLeftSide() {
        return new Line(this.upperLeft, this.getBottomLeft());
    }
    /**
     * Creates a Line emulating the right side of the Rectangle.
     * @return The right side
     */
    public Line getRightSide() {
        return new Line(this.getUpperRight(), this.getBottomRight());
    }
    /**
     * Creates a Line emulating the top side of the Rectangle.
     * @return The top side
     */
    public Line getTopSide() {
        return new Line(this.upperLeft, this.getUpperRight());
    }
    /**
     * Creates a Line emulating the bottom side of the Rectangle.
     * @return The bottom side
     */
    public Line getBottomSide() {
        return new Line(this.getBottomLeft(), this.getBottomRight());
    }
    /**
     * Sets the x value of the upperLeft Point.
     * Considering the fact that we derived all other information about the Rectangle
     * Using this Point, it is sufficient to modify this Point in order to move the entire Rectangle
     * @param newXValue
     */
    public void setUpperLeftXValue(double newXValue) {
        this.upperLeft.setX(newXValue);
    }
}