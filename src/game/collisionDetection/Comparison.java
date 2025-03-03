//207795030 Daniel Michaelshvili

package game.collisionDetection;

/**
 * @author Daniel Michaelshvili.
 * The 'Comparison' class is a class that inculde a static method that it's role is to check weather to 'double'
 * variables are nearly equals (the diffrence between them is less than 'epsilon' defined with 0.00001).
 */
public class Comparison {
    private static final double COMPARISON_THRESHOLD = 0.00001;

    /**
     * A method that checks weather to 'Double' variables are nearly equals (the diffrence between them is less than
     * 'epsilon' defined with 0.00001), or they are both null.
     * @param x (Double Type) - the first double variable that his subtraction result with the second is checked.
     * @param y (Double Type) - the second double variable that his subtraction result with the first is checked.
     * @return true if the variables equals or they both null, false otherwise.
     */
    public static boolean equal(Double x, Double y) {
        if (x == null && y == null) {
            return true;
        }
        if (x == null || y == null) {
            return false;
        }
        return (Math.abs(x - y) < COMPARISON_THRESHOLD);
    }
}
