/**
 * Forced to create this JDOC due to checkstyles.
 */
public class Line {
    private Point start;
    private Point end;
    /**
     * Constructor of the Line class.
     * @param start the Point at which the Line oridginates
     * @param end the Point at which the Line ends
     */
    public Line(Point start, Point end) {
        this.start = new Point(start);
        this.end = new Point(end);
    }
    /**
     * Constructor of the Line class.
     * @param x1 the x value of the starting Point
     * @param y1 the y value of the starting Point
     * @param x2 the x value of the ending Point
     * @param y2 the y value of the ending Point
     */
    public Line(double x1, double y1, double x2, double y2) { }
    /**
     * Calculates the length of the Line.
     * @return the length of the Line
     */
    public double length() { }
    /**
     * Calculates the middle of the Line.
     * @return the middle Point of the Line.
     */
    public Point middle() { }
    /**
     * @return the start Point of the Line
     */
    public Point start() { }
    /**
     * @return the end Point of the Line
     */
    public Point end() { }
    /**
     * Determines whether the Line intersects with the other Line.
     * @param other the other Line
     * @return true if the Lines intersect and false otherwise
     */
    public boolean isIntersecting(Line other) { }
    /**
     * Determines whether the Line intersects with the other Lines.
     * @param other1 another Line
     * @param other2 yet another Line
     * @return true if the two Lines intersect with the Line and false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) { }
    /**
     * Calculates the intersection Point of the Line with the other Line.
     * @param other the other Line
     * @return the intersection Point if the Lines intersect and null otherwise
     */
    public Point intersectionWith(Line other) { }
    /**
     * Determines whether the Line is equal to the other Line.
     * @param other the other Line
     * @return true if the Lines are equal and false otherwise
     */
    public boolean equals(Line other) { }
}