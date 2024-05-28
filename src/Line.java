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
     * The orientation of a Point in regards to a Line segment is the 'direction' of the respecful diversion
     * required for a Line to be drawn between the end of the Line segment and the Point.
     * The orientation is thus comprised of one of the following three:
        * Clock-wise
        * Counter clock-wise
        * No diversion required
     * The following funciton finds the orientation of the third point in respect to the first two.
     * A thorough explanation which delves into the intricacies of this function's inner workings
     * And the list of geometric justifications to its integrity are available in the following link:
     * https://www.youtube.com/watch?v=5FkOO1Wwb8w&ab_channel=EngineerNick.
     * It goes without saying that the following is my own implementation
     * of the concepts taught in the video and not an imitation of it.
     * @param point the point to be compared with the Line
     * @return 0 if the point is collinear with the Line
     *  1 if it requires a diversion to the left (counter clock-wise) and
     * -1 if it requires a diversion to the right (clock-wise)
     */
    public int getOrientationOfPoint(Point point) {
        double determinant =
            (this.end.getX() - this.start.getX()) * (point.getY() - this.end.getY())
            - (this.end.getY() - this.start.getY()) * (point.getX() - this.end.getX());
        // using threshold based comparison on the determinant (its a double)
        if (ThresholdCompare.isThresholdBasedEquals(determinant, 0)) {
            return 0;
        }
        return (determinant > 0) ? 1 : -1;
    }
    /**
     * Checks whether an x value is in the Line Segment's range of x values.
     * @param point the point to be tested
     * @return true if the Point's x value is within the range of x value for which the Line is defined
     * and false otherwise
     */
    public boolean isPointOnLineSegment(Point point) {
        return (
            ThresholdCompare.isThresholdBasedGreaterEqual(point.getX(), Math.min(this.start.getX(), this.end.getX()))
            && ThresholdCompare.isThresholdBasedGreaterEqual(Math.max(this.start.getX(), this.end.getX()), point.getX())
            && ThresholdCompare.isThresholdBasedGreaterEqual(point.getY(), Math.min(this.start.getY(), this.end.getY()))
            && ThresholdCompare.isThresholdBasedGreaterEqual(Math.max(this.start.getY(), this.end.getY()), point.getY())
        );
    }
    /**
     * Determines whether the Line intersects with the other Line.
     * The result is procured through the following set of deductions:
        * If the orientations o1 and o2 are different, and o3 also differs from o4 the Line Segments form an X shape
            meaning that they are ought to intersect.
        * If the orientation is equal to 0,
            it means that the third point is on the same infinite line as our Line segment
            meaning that if the Point is between our two points, the lines intersect (we must ensure that is the case)
     * @param other the other Line
     * @return true if the Lines intersect and false otherwise
     */
    public boolean isIntersecting(Line other) {
        int o1 = this.getOrientationOfPoint(other.start());
        int o2 = this.getOrientationOfPoint(other.end());
        int o3 = other.getOrientationOfPoint(this.start);
        int o4 = other.getOrientationOfPoint(this.end);

        if (o1 != o2 && o3 != o4) {
            return true;
        }

        return (
            (o1 == 0 && this.isPointOnLineSegment(other.start()))
            || (o2 == 0 && this.isPointOnLineSegment(other.end()))
            || (o3 == 0 && other.isPointOnLineSegment(this.start))
            || (o4 == 0 && other.isPointOnLineSegment(this.end))
        );
    }
    /**
     * Determines whether the Line intersects with the other Lines.
     * @param other1 another Line
     * @param other2 yet another Line
     * @return true if the two Lines intersect with the Line and false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return (
            this.isIntersecting(other1)
            || this.isIntersecting(other2)
        );
    }
    /**
     * Calculates the intersection Point of the Line with the other Line.
     * @param other the other Line
     * @return the intersection Point if the Lines intersect and null otherwise
     */
    public Point intersectionWith(Line other) {
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