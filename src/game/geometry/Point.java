//207795030 Daniel Michaelshvili
package game.geometry;

import game.collisionDetection.Comparison;
/**
 * @author Daniel Michaelshvili.
 * The 'Point' class has been created to assist make animations that require involving 2d points objects,
 * in the project.
 */
public class Point {

    /*The x value of the point*/
    private double x;
    /*The y value of the point*/
    private double y;

    /**
     *A constructor for a 'Point' object.
     * @param x (double Type)- The x value of the point.
     * @param y (double Type)- The y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * A method that sets to the point a new x value.
     * @param x ('double' type) - the new x value that should be set to the point.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * A method that sets to the point a new y value.
     * @param y ('double' type) - the new y value that should be set to the point.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * This method returns the x value of the point.
     * @return the x value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * This method returns the y value of the point.
     * @return the y value of the point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * This method returns the distance of this point to the 'other' point.
     * @param other ('Point' Type) - the point that is checked what the distance from it to 'This' point.
     * @return the distance of this point to the 'other' point.
     */
    public double distance(Point other) {
        double distanceX = this.x - other.x;
        double distanceY = this.y - other.y;
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }

    /**
     * Checks if 2 given points are equal.
     * @param other ('Point' Type)- the point that being checked if it equals to 'This' point.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return Comparison.equal(this.x, other.x) && Comparison.equal(this.y, other.y);
    }
}