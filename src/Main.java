/**
 * .
 */
public class Main {
    /**
     * .
     * @param args
     */
    public static void main(String[] args) {
        Point myPoint = new Point(5.0, 6.9);
        Point myPoint2 = new Point(9.0, 1.9);
        Point myPoint3 = new Point(9.0, 1.9);
        System.out.println(myPoint.distance(myPoint2));
        System.out.println(myPoint.equals(myPoint2));
        System.out.println(myPoint3.distance(myPoint2));
        System.out.println(myPoint3.equals(myPoint2));
    }
}
