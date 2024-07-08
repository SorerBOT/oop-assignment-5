/**
 * The Counter class.
 */
public class Counter {
    private int count;

    /**
     * Constructor of the Counter class.
     */
    public Counter() {
        this.count = 0;
    }
    /**
     * Adds a number to the current count.
     * @param number the number to be added to the count
     */
    public void increase(int number) {
        this.count += number;
    }
    /**
     * Decrease the current count by number.
     * @param number the number to be reduced
     */
    public void decrease(int number) {
        this.count -= number;
    }
    /**
     * Getter of the count field.
     * @return the count
     */
    public int getValue() {
        return this.count;
    }
}