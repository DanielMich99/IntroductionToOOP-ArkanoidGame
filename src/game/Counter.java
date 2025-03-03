
package game;

/**
 * @author Daniel Michaelshvili
 * The 'Counter' class has been created for the option to make objects that based on the idea of a counter
 * in the game. */
public class Counter {

    /*represents the number in the counter.*/
    private int count;

    /**
     * A constructor to the 'Counter' object, in the Constructor it sets the counter to count from 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * A method that increases the number in the count by 'number'.
     * @param number ('int' type) - the number that the counter is increased by.
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * A method that decreases the number in the count by 'number'.
     * @param number ('int' type) - the number that the counter is decreased by.
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * A method that returns the value of the counter.
     * @return the value of the counter.
     */
    public int getValue() {
        return this.count;
    }
}
