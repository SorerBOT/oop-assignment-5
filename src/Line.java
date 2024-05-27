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
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * Calculates the length of the Line.
     * @return the length of the Line
     */
    public double length() {
        return this.start.distance(this.end);
    }
    /**
     * Calculates the middle of the Line.
     * @return the middle Point of the Line.
     */
    public Point middle() {
        return new Point(
            (this.start.getX() + this.end.getX()) / 2,
            (this.start.getY() + this.end.getY()) / 2
        );
    }
    /**
     * @return the start Point of the Line
     */
    public Point start() {
        return new Point(this.start);
    }
    /**
     * @return the end Point of the Line
     */
    public Point end() {
        return new Point(this.end);
    }
    /**
     * @return the slope of the Line
     */
    double getSlope() {
        return (
            (this.end.getY() - this.start.getY())
            / (this.end.getX() - this.start.getX())
        );
    }
    /**
     * Determines whether the Line intersects with the other Line.
     * @param other the other Line
     * @return true if the Lines intersect and false otherwise
     */
    public boolean isIntersecting(Line other) {
        double firstSlope = this.getSlope();
        double secondSlope = other.getSlope();

        double firstB = this.start.getY() - firstSlope * this.start.getX();
        double secondB = other.start.getY() - firstSlope * other.start.getX();
        // Given system of equations that are of the form
        // y = slope*x + b
        // Derive equation of the form
        // ax = b
        // and solve for x

        double a = firstSlope - secondSlope;
        double b = -(firstB - secondB);

        if (a == 0) {
            // the lines are parallel, checking whether they collide
            return b == 0;
        }
        double intersectionXValue = b / a;

        double greaterLineXValue = Math.max(this.start.getX(), this.end.getX());
        double lowerLineXValue = Math.min(this.start.getX(), this.end.getX());

        return (
            ThresholdCompare.isThresholdBasedGreaterEqual(greaterLineXValue, intersectionXValue)
            && ThresholdCompare.isThresholdBasedGreaterEqual(intersectionXValue, lowerLineXValue)
        );
    }
    /**
     * Determines whether the Line intersects with the other Lines.
     * @param other1 another Line
     * @param other2 yet another Line
     * @return true if the two Lines intersect with the Line and false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return false;
    }
    /**
     * Calculates the intersection Point of the Line with the other Line.
     * @param other the other Line
     * @return the intersection Point if the Lines intersect and null otherwise
     */
    public Point intersectionWith(Line other) {
        return new Point(0, 0);
    }
    /**
     * Determines whether the Line is equal to the other Line.
     * @param other the other Line
     * @return true if the Lines are equal and false otherwise
     */
    public boolean equals(Line other) {
        return false;
    }
}