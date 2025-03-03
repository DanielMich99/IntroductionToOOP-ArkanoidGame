//207795030 Daniel Michaelshvili

package game.collisionDetection;

import game.geometry.Point;

/**
 * @author Daniel Michaelshvili.
 * The 'Velocity' class has been created to assist make animations of a bouncing ball on the screen using the GUI,
 * resembles the features of the ball movment.
 */
public class Velocity {

    private static final int ONE_HUNDRED_AND_EIGHTY_DEGREES = 180;

    /*Represents the distance the ball move on the x-axis every frame change during the animation.*/
    private double dx;

    /*Represents the distance the ball move on the y-axis every frame change during the animation.*/
    private double dy;

    /**
     * A constructor for a 'Velocity' object.
     * @param dx (double Type) - the distance the ball move on the x-axis every frame change.
     * @param dy (double Type) - the distance the ball move on the y-axis every frame change.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * A method that returns the dx of the velocity.
     * @param dx ('double' type) - represents the number of units and direction the object moves in the horizontal axis.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * A method that returns the dy of the velocity.
     * @param dy ('double' type) - represents the number of units and direction the object moves in the vertical axis.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * A method that returns the distance the ball move on the x-axis every frame change.
     * @return the distance the ball move on the x-axis every frame change.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * A method that returns the distance the ball move on the y-axis every frame change.
     * @return the distance the ball move on the y-axis every frame change.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * A method that take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p ('Point' object) - Represents the initial position of the point before the movement.
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        Point point = new Point(this.dx + p.getX(), this.dy + p.getY());
        return point;
    }

    /**
     * A method that choose to represent the velocity of the ball in terms of angle and speed and calculates
     * the velocity represented by dx/dy components.
     * @param angle (double Type) - the requested angle that the ball should move on the surface.
     * @param speed (double Type) - the requested speed that the ball should move on the surface.
     * @return the velocity represented by dx/dy components.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(angle * Math.PI / ONE_HUNDRED_AND_EIGHTY_DEGREES) * speed;
        double dy = Math.cos(angle * Math.PI / ONE_HUNDRED_AND_EIGHTY_DEGREES) * speed;
        /*The calculations that based on the 'unit circle' structure in the trigonometry field.*/

        return new Velocity(dx, dy);
    }
}
