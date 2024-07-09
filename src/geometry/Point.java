package geometry;

import utils.ThresholdCompare;
// Alon Filler 216872374
/**
 * Forced to create this JDOC due to checkstyles.
 */
public class Point {
    private double x;
    private double y;
    /**
     * Constructor of the Point class.
     * @param x x value of the Point
     * @param y y value of the Point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Copy constructor of the Point class.
     * @param other the Point to be copied
     */
    public Point(Point other) {
        this.x = other.getX();
        this.y = other.getY();
    }
    /**
     * Measure distance between the Point and another Point.
     * @param other the Point to measure distance from
     * @return the distance between the Point objects
     */
    public double distance(Point other) {
        return Math.sqrt(
            Math.pow(this.x - other.getX(), 2)
            + Math.pow(this.y - other.getY(), 2)
        );
    }
    /**
     * Checks if the Point is equal to a different Point.
     * @param other the Point to compare
     * @return true if the Point is equal to the other Point and false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (
            ThresholdCompare.isThresholdBasedEquals(this.x, other.getX())
            && ThresholdCompare.isThresholdBasedEquals(this.y, other.getY())
        );
    }
    /**
     * Getter of the x field.
     * @return the x value of the Point
     */
    public double getX() {
        return this.x;
    }
    /**
     * Getter of the y field.
     * @return the y value of the Point
     */
    public double getY() {
        return this.y;
    }
    /**
     * Sets the Point's x value.
     * @param x the x value to be set
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Sets the Point's y value.
     * @param y the y value to be set
     */
    public void setY(double y) {
        this.y = y;
    }
}