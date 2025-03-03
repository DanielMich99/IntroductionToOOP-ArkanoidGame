
package game.geometry;

import java.util.ArrayList;
/**
 * @author Daniel Michaelshvili
 * The 'Rectangle' class is a calss has been created to assist make animations that require involving 2d rectangles
 * objects, in the project.
 */
public class Rectangle {

    /*represents the upper left point of the rectangle.*/
    private Point upperLeft;

    /*represents the upper right point of the rectangle.*/
    private Point upperRight;

    /*represents the lower left point of the rectangle.*/
    private Point lowerLeft;

    /*represents the lower right point of the rectangle.*/
    private Point lowerRight;

    /*represents the width of the rectangle.*/
    private final double width;

    /*represents the height of the rectangle.*/
    private final double height;

    /*represents the segment line of the left side of the rectangle.*/
    private Line leftLine;

    /*represents the segment line of the right side of the rectangle.*/
    private Line rightLine;

    /*represents the segment line of the top side of the rectangle.*/
    private Line topLine;

    /*represents the segment line of the bottom side of the rectangle.*/
    private Line bottomLine;

    /**
     * A constructor to the 'Rectangle' object that set to him all his attributes.
     * @param upperLeft ('Point' object) - the upper left point of the rectangle
     * @param width ('double' type) - the width of the rectangle.
     * @param height ('double' type) - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.upperRight = new Point((upperLeft.getX() + width), upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(), (upperLeft.getY() + height));
        this.lowerRight = new Point((upperLeft.getX() + width), (upperLeft.getY() + height));
        this.width = width;
        this.height = height;
        this.leftLine = new Line(upperLeft, lowerLeft);
        this.rightLine = new Line(upperRight, lowerRight);
        this.topLine = new Line(upperLeft, upperRight);
        this.bottomLine = new Line(lowerLeft, lowerRight);
    }

    /**
     * A method that sets the upper left point of the rectangle.
     * @param upperLeft ('Point' object) - the upper left point of the rectangle.
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    /**
     * A method that sets the upper right point of the rectangle.
     * @param upperRight ('Point' object) - the upper right point of the rectangle.
     */
    public void setUpperRight(Point upperRight) {
        this.upperRight = upperRight;
    }

    /**
     * A method that sets the lower left point of the rectangle.
     * @param lowerLeft ('Point' object) - the lower left point of the rectangle.
     */
    public void setLowerLeft(Point lowerLeft) {
        this.lowerLeft = lowerLeft;
    }

    /**
     * A method that sets the lower right point of the rectangle.
     * @param lowerRight ('Point' object) - the lower rught point of the rectangle.
     */
    public void setLowerRight(Point lowerRight) {
        this.lowerRight = lowerRight;
    }

    /**
     * A method that returns the width of the rectangle.
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * A method that returns the height of the rectangle.
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * A method that returns the upper left point of the rectangle.
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * A method that returns the upper right point of the rectangle.
     * @return the upper right point of the rectangle.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * A method that returns the lower left point of the rectangle.
     * @return the lower left point of the rectangle.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * A method that returns the lower right point of the rectangle.
     * @return the lower right point of the rectangle.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * A method that returns the segment line of the left side of the rectangle.
     * @return the segment line of the left side of the rectangle.
     */
    public Line getLeftLine() {
        return this.leftLine;
    }

    /**
     * A method that returns the segment line of the right side of the rectangle.
     * @return the segment line of the right side of the rectangle.
     */
    public Line getRightLine() {
        return this.rightLine;
    }

    /**
     * A method that returns the segment line of the top side of the rectangle.
     * @return the segment line of the top side of the rectangle.
     */
    public Line getTopLine() {
        return this.topLine;
    }

    /**
     * A method that returns the segment line of the bottom side of the rectangle.
     * @return the segment line of the bottom side of the rectangle.
     */
    public Line getBottomLine() {
        return this.bottomLine;
    }

    /**
     * A method that returns a (possibly empty) List of intersection points of the rectangle with the specified line.
     * @param line ('Line' object) - the line that checked if it has intersection points with the rectangle.
     * @return a (possibly empty) List of intersection points of the rectangle with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> points = new ArrayList<Point>();
        Point leftLineWithLine = line.intersectionWith(this.leftLine);
        Point rightLineWithLine = line.intersectionWith(this.rightLine);
        Point topLineWithLine = line.intersectionWith(this.topLine);
        Point bottomLineWithLine = line.intersectionWith(this.bottomLine);
        if (leftLineWithLine != null) {
            points.add(leftLineWithLine);
        }
        if (rightLineWithLine != null) {
            points.add(rightLineWithLine);
        }
        if (topLineWithLine != null) {
            points.add(topLineWithLine);
        }
        if (bottomLineWithLine != null) {
            points.add(bottomLineWithLine);
        }
        return points;
    }

    /**
     * A method that "moves" a rectangle horizontally 'x' units to the left.
     * @param x ('double' type) - the number of units the rectangle moves left.
     */
    public void moveRectangleLeft(double x) {
        this.upperLeft.setX(this.upperLeft.getX() - x);
        this.lowerLeft.setX(this.lowerLeft.getX() - x);
        this.upperRight.setX(this.upperRight.getX() - x);
        this.lowerRight.setX(this.lowerRight.getX() - x);
        //changing the dots of the rectangle accordingly.

        this.leftLine.setStart(this.upperLeft);
        this.leftLine.setEnd(this.lowerLeft);
        this.rightLine.setStart(this.upperRight);
        this.rightLine.setEnd(this.lowerRight);
        this.topLine.setStart(this.upperLeft);
        this.topLine.setEnd(this.upperRight);
        this.bottomLine.setStart(this.lowerLeft);
        this.bottomLine.setEnd(this.lowerRight);
        //changing the lines of the sides of the rectangle accordingly.

    }

    /**
     * A method that "moves" a rectangle horizontally 'x' units to the right.
     * @param x ('double' type) - the number of units the rectangle moves right.
     */
    public void moveRectangleRight(double x) {
        this.upperLeft.setX(this.upperLeft.getX() + x);
        this.lowerLeft.setX(this.lowerLeft.getX() + x);
        this.upperRight.setX(this.upperRight.getX() + x);
        this.lowerRight.setX(this.lowerRight.getX() + x);
        //changing the dots of the rectangle accordingly.

        this.leftLine.setStart(this.upperLeft);
        this.leftLine.setEnd(this.lowerLeft);
        this.rightLine.setStart(this.upperRight);
        this.rightLine.setEnd(this.lowerRight);
        this.topLine.setStart(this.upperLeft);
        this.topLine.setEnd(this.upperRight);
        this.bottomLine.setStart(this.lowerLeft);
        this.bottomLine.setEnd(this.lowerRight);
        //changing the lines of the sides of the rectangle accordingly.x

    }



}
