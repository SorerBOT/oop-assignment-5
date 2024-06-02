// Alon Filler 216872374
/**
 * Forced to create this JDOC due to checkstyles.
 */
public class ThresholdCompare {
    /**
     * Applies threshold based comparison to the numbers.
     * @param firstNumber the first number
     * @param secondNumber the second number
     * @return true if they are equal and false otherwise
     */
    public static boolean isThresholdBasedEquals(double firstNumber, double secondNumber) {
        double epsilon = 0.000001d;
        return Math.abs(firstNumber - secondNumber) < epsilon;
    }
    /**
     * Applies threshold comparison and determines whether firstNumber is greater or equal to secondNumber.
     * @param firstNumber the first number
     * @param secondNumber the second number
     * @return true if the first number is greater than the second number and false otherwise
     */
    public static boolean isThresholdBasedGreaterEqual(double firstNumber, double secondNumber) {
        return (
            isThresholdBasedEquals(firstNumber, secondNumber)
            || firstNumber > secondNumber
        );
    }
}
