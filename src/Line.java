// Alon Filler 216872374
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
     * Attests to whether the Line provided is a mere Point.
     * @return true if the starting Point is equal to the ending Point and false otherwise
     */
    public boolean isLineAPoint() {
        return this.start.equals(this.end);
    }
    /**
     * Determines whether the Line collides with the other Line.
     * @param line the other Line
     * @return true if the Lines collide and false otherwise
     */
    public boolean isLineColliding(Line line) {
        return this.isIntersecting(line) && this.intersectionWith(line) == null;
    }
    /**
     * Can only be used under the assumption that the Line is not perpendicular to the main axis.
     * if such is not the case an error must be thrown
     * @return the slope of the Line if it can be procured by this method of calculation.
     */
    public double getSlope() {
        double deltaX = this.end.getX() - this.start.getX();
        double deltaY = this.end.getY() - this.start.getY();
        // A Line which is a mere Point is perpendicular and thus a slope cannot be derived for it
        // used two if's to ease debugging
        if (this.isLineAPoint()) {
            throw new Error("Cannot calculate slope for a Point.");
        }
        if (this.isPerpendicularToMainAxis()) {
            throw new Error("Cannot calculate slope for perpendicular Lines.");
        }

        return deltaY / deltaX;
    }
    /**
     * @return the b of the line (y = mx + b)
     */
    public double getB() {
        return this.start.getY() - this.getSlope() * this.start.getX();
    }
    /**
     * Gets the Line's y value at said x value.
     * @param x the x value for which we would like to find the y value
     * @return the y value at said position
     */
    public double getYAtX(double x) {
        return this.isLineAPoint()
            ? this.start.getY()
            : this.getSlope() * x + this.getB();
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
     * Determines whether the Line Segment is perpendicular to the main axis.
     * @return true if the Line Segment is perpendicular and false otherwise
     */
    public boolean isPerpendicularToMainAxis() {
        return ThresholdCompare.isThresholdBasedEquals(this.start.getX(), this.end.getX());
    }
    /**
     * Determines whether the Line Segment is horizontal.
     * @return true if the Line Segment is horizontal and false otherwise
     */
    public boolean isHorizontal() {
        return ThresholdCompare.isThresholdBasedEquals(this.start.getY(), this.end.getY());
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
     * https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/.
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
     * Determine whether a scenario in which the first and second points wrap
     * around the middle point from different directions.
     * @param middle the middle Point
     * @param first the first Point
     * @param second the second Point
     * @return true they are going in different directions
     */
    public boolean isGoingInDifferentDirections(double middle, double first, double second) {
        return ((middle - first) * (middle - second) < 0);
    }
    /**
     * Calculates the intersection Point of the Line with the other Line.
     * @param other the other Line
     * @return the intersection Point if the Lines intersect and null otherwise
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        }
        if (this.isLineAPoint()) {
            return new Point(this.start);
        }
        if (other.isLineAPoint()) {
            return new Point(other.start);
        }
        int o1 = this.getOrientationOfPoint(other.start());
        int o2 = this.getOrientationOfPoint(other.end());
        // In the following code chunk we are to deal with cases in which
        // the Line Segments are collinear and lie on the same infite Line
        if (o1 == 0 && o2 == 0) {
            // In order to distinguish a case in which the lines collide from that in which the lines only tangent
            // to one another we check if they share a common edge point and that each Line has its other point
            // lying in a different vertical direction from the common point
            if (this.isPerpendicularToMainAxis()) {
                // In the following cases we approach the problem under the presumption that both lines are
                // perpendicular to the main axis
                if (this.start.equals(other.start())) {
                    return (this.isGoingInDifferentDirections(this.start.getY(), this.end.getY(), other.end().getY()))
                        ? new Point(this.start)
                        : null;
                }
                if (this.start.equals(other.end())) {
                    return (this.isGoingInDifferentDirections(this.start.getY(), this.end.getY(), other.start().getY()))
                        ? new Point(this.start)
                        : null;
                }
                if (this.end.equals(other.start())) {
                    return (this.isGoingInDifferentDirections(this.end.getY(), this.start.getY(), other.end().getY()))
                        ? new Point(this.end)
                        : null;
                }
                if (this.end.equals(other.end())) {
                    return (this.isGoingInDifferentDirections(this.end.getY(), this.start.getY(), other.start().getY()))
                        ? new Point(this.end)
                        : null;
                }
                return null;
            }
            // we are now under the pretext that the lines are not perpendicular, we continue performing an identical
            // test only now we are using the X values of the Points, and checking for a
            // horizontal directional change
            if (this.start.equals(other.start())) {
                return (this.isGoingInDifferentDirections(this.start.getX(), this.end.getX(), other.end().getX()))
                    ? new Point(this.start)
                    : null;
            }
            if (this.start.equals(other.end())) {
                return (this.isGoingInDifferentDirections(this.start.getX(), this.end.getX(), other.start().getX()))
                    ? new Point(this.start)
                    : null;
            }
            if (this.end.equals(other.start())) {
                return (this.isGoingInDifferentDirections(this.end.getX(), this.start.getX(), other.end().getX()))
                    ? new Point(this.end)
                    : null;
            }
            if (this.end.equals(other.end())) {
                return (this.isGoingInDifferentDirections(this.end.getX(), this.start.getX(), other.start().getX()))
                    ? new Point(this.end)
                    : null;
            }
            return null;
        }
        // We are now allowed to assume that the Line Segments do not lie on the same
        // Infinite Line, thus the lines definitely do not collide
        // Additionally we assume that there can not exist a predicament under which
        // both Line Segments are perpendicular to the main axis
        if (this.isPerpendicularToMainAxis()) {
            double xValueOfPoint = this.start.getX();
            return new Point(xValueOfPoint, other.getYAtX(xValueOfPoint));
        }
        if (other.isPerpendicularToMainAxis()) {
            double xValueOfPoint = other.start().getX();
            return new Point(xValueOfPoint, this.getYAtX(xValueOfPoint));
        }
        // By now we have successfully dealt with all cases concerning Line Segments that are perpendicular to the main
        // axis and Line Segments that are collinear
        // Thus we can find the equations of the infinite Lines on which the Line Segments fall
        double xValueOfIntersection = -(this.getB() - other.getB()) / (this.getSlope() - other.getSlope());
        return new Point(xValueOfIntersection, this.getYAtX(xValueOfIntersection));
    }
    /**
     * Determines whether the Line is equal to the other Line. (Both Points are shared)
     * @param other the other Line
     * @return true if the Lines are equal and false otherwise
     */
    public boolean equals(Line other) {
        return (
            (this.start.equals(other.start()) && this.end.equals(other.end()))
            || (this.start.equals(other.end()) && this.end.equals(other.start()))
        );
    }
}