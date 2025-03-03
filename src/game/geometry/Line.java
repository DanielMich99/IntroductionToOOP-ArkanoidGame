
package game.geometry;

import biuoop.DrawSurface;
import java.awt.Color;
import game.collisionDetection.Comparison;

/**
 * @author Daniel Michaelshvili.
 * The 'Line' class has been created to assist make animations that require involving 2d lines objects,
 * in the project.
 */
public class Line {

    private static final int TWO_DIVIDER = 2;

    /*Represents the starting point of the line. */
    private  Point start;

    /*Represents the ending point of the line.*/
    private  Point end;

    /**
     * A constructor for a 'Line' object.
     * @param start ('Point' Type) - the starting point of the line.
     * @param end ('Point' Type) - the ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * A second constructor for a 'Line' object.
     * @param x1 (double Type) - the x value of the starting point of the line.
     * @param y1 (double Type) - the y value of the starting point of the line.
     * @param x2 (double Type) - the x value of the ending point of the line.
     * @param y2 (double Type) - the y value of the ending point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        this.start = p1;
        this.end = p2;
    }

    /**
     * A method that sets the starting point of the line.
     * @param start ('Point' object) - the starting point that should be set to the line.
     */
    public void setStart(Point start) {
        this.start = start;
    }

    /**
     * A method that sets the end point of the line.
     * @param end ('Point' object) - the end point that should be set to the line.
     */
    public void setEnd(Point end) {
        this.end = end;
    }

    /**
     * A method that returns the start point of the line.
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * A method that returns the end point of the line.
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * A method that calculates the length of the line.
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * A method that calculates the middle point of the line.
     * @return the middle point of the line.
     */
    public Point middle() {
        Point middle = new Point(((this.start.getX() + this.end.getX()) / TWO_DIVIDER),
                ((this.start.getY() + this.end.getY()) / TWO_DIVIDER));
        return middle;
    }

    /**
     * A method that draws the line on the given DrawSurface - 'd'.
     * @param d ('DrawSurface' object) - the surface that the ball will be drawn on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());
    }

    /**
     * A method that returns the cutting point with the Y-axis of the 'linear equation' that
     * 'This' line segment lays on.
     * @return the cutting point with the Y-axis of the 'linear equation' that 'This' line segment lays on..
     */
    private Double getCuttingPointWithYAxis() {
        if (this.findSlope() == null) {
            return null;
        }
        return -this.findSlope() * this.start.getX() + this.start.getY();
    }

    /**
     * A method that returns the Y value for the 'x' it gets, for the 'linear equation' that 'This' lays on.
     * @param x (double Type) - the x value that is being checked what his y value in the 'linear equation'.
     * @return the Y value for the 'x' it gets, for the 'linear equation' that 'This' lays on.
     */
    public double yValueForGivenX(double x) {
        return this.findSlope() * x + this.getCuttingPointWithYAxis();
    }

    /**
     *  A method that returns the X value for the 'y' it gets, for the 'linear equation' that 'This' lays on.
     * @param y (double Type) - the y value that is being checked what his x value in the 'linear equation'.
     * @return the X value for the 'y' it gets, for the 'linear equation' that 'This' lays on.
     */
    public double xValueForGivenY(double y) {
        return ((y - this.getCuttingPointWithYAxis()) / this.findSlope());
    }

    /**
     *  A method that checks if a given point is on the line or not.
     * @param p ('Point' object) - the points that being checked if it is on the line or not.
     * @return true if 'p' is on the line, false otherwise.
     */
    public boolean isOnLine(Point p) {
        if (((p.getX() > Math.min(this.start.getX(), this.end.getX()))
                || (Comparison.equal(p.getX(), Math.min(this.start.getX(), this.end.getX()))))
                && ((p.getX() < Math.max(this.start.getX(), this.end.getX()))
                || (Comparison.equal(p.getX(), Math.max(this.start.getX(), this.end.getX()))))
                && ((p.getY() > Math.min(this.start.getY(), this.end.getY()))
                || (Comparison.equal(p.getY(), Math.min(this.start.getY(), this.end.getY()))))
                && ((p.getY() < Math.max(this.start.getY(), this.end.getY()))
                || (Comparison.equal(p.getY(), Math.max(this.start.getY(), this.end.getY()))))) {
                /* Checks if the x and the y value of the point is between the minimum and the maximum values of them
                  in the line. If they are it means the point is on the line. */

            return true;
        }
        return false;
    }

    /**
     * A method that calculates, according to the 'linear equation', the slope of the line. if the slope tends to
     * infinity, it returns null
     * @return the slope of the line, if the slope tends to infinity, it returns null
     */
    public Double findSlope() {
        if (Comparison.equal(this.start.getX(), this.end.getX())) {
            return null;
        }
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * A method that checks if 'This' line segment is a dot.
     * @return true if the line is a dot, false otherwise.
     */
    private boolean isADot() {
        return (Comparison.equal(this.start.getX(), this.end.getX())
                && Comparison.equal(this.start.getY(), this.end.getY()));
    }

    /**
     * A method that checks if 'This' is vertical to the x-axis.
     * @return true if the line is vertical to the x-axis, false otherwise.
     */
    private boolean isVerticalToX() {
        return Comparison.equal(this.start.getX(), this.end.getX());
    }

    /**
     * A method that checks if 2 different lines are intersecting with each other.
     * (note - all the calculations in this method based on the 'linear equation' structure).
     * @param other ('Line' Object) - the line that being checked if it is intersecting with 'This' line.
     * @return true if the lines are intersecting with each other, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.isADot()) {
            return other.isOnLine(this.start);
        }
        if (other.isADot()) {
            return this.isOnLine(other.start);
        }
        /*checks the scenario of one the lines is actually a dot.*/

        boolean isThisVerticalToX = this.isVerticalToX();
        boolean isOtherVerticalToX = other.isVerticalToX();
        if (isThisVerticalToX && isOtherVerticalToX) {
            if (Comparison.equal(this.start.getX(), other.start.getX())) {
                if (this.isOnLine(other.start) || this.isOnLine(other.end) || other.isOnLine(this.start)
                        || other.isOnLine(this.end)) {
                    /*If one of the segment lines starts or ends between the other line start and end point,
                      it means they intersect with each other.*/

                    return true;
                }
            } else {
                return false;
            }
        }
        if (isThisVerticalToX && !isOtherVerticalToX) {
            Point point = new Point(this.start.getX(), other.yValueForGivenX(this.start.getX()));
            return (this.isOnLine(point) && other.isOnLine(point));
        }
        if (!isThisVerticalToX && isOtherVerticalToX) {
            Point point = new Point(other.start.getX(), this.yValueForGivenX(other.start.getX()));
            return (this.isOnLine(point) && other.isOnLine(point));
        }
        /* a different check is being done for the case one of the lines is vertical to x-axis and the slope
           tends to infinity.*/

        Double thisLineSlope = this.findSlope();
        Double otherLineSlope = other.findSlope();
        if (!Comparison.equal(thisLineSlope, otherLineSlope)) {
            /*Checks the condition of 2 different slopes to them lines.*/

            double xValue = (((other.start.getX() * -otherLineSlope) + other.start.getY())
                    - (this.start.getX() * -thisLineSlope + this.start.getY()))
                    / (thisLineSlope - otherLineSlope);
            double yValue = thisLineSlope * (xValue - this.start.getX()) + this.start.getY();
            Point intersectionPoint = new Point(xValue, yValue);
            return (this.isOnLine(intersectionPoint) && other.isOnLine(intersectionPoint));
            /*2 different lines that, have different slopes, will cut each other in some point in the plane.
             The program calculates the cutting point of the two infinite lines and checks if this
             cutting point is on both segment lines. If it does, that means that the 2 segment lines cut each other.*/

        } else {
            /*Checks the condition of both lines having the same slope.*/

            if (Comparison.equal(this.getCuttingPointWithYAxis(), other.getCuttingPointWithYAxis())) {
                /*Checks the scenario of both segment lines 'laid' on the same 'Linear equation'.*/

                if (this.isOnLine(other.start) || this.isOnLine(other.end) || other.isOnLine(this.start)
                        || other.isOnLine(this.end)) {
                    /*If one of the segment lines starts or ends between the other line start and end point,
                      it means they intersect with each other.*/

                    return true;
                }
            }
        }
        return false;
    }

    /**
     * A method that returns the intersection point of 2 segment lines if they intersect, or NULL if they don't
     * or if they're intersecting with more than 1 point.
     * @param other ('Line' object) - the line that being checked if it is intersecting with 'This' line.
     * @return the intersection point of 2 segment lines if they intersect, or NULL if they don't or if they're
     * intersecting with more than 1 point.
     */
    public Point intersectionWith(Line other) {
        if (this.isADot() && this.isIntersecting(other)) {
            return this.start;
        }
        if (other.isADot() && this.isIntersecting(other)) {
            return other.start;
        }
        /*checks the scenario of one the lines is actually a dot.*/

        boolean isThisVerticalToX = this.isVerticalToX();
        boolean isOtherVerticalToX = other.isVerticalToX();
        if ((this.isIntersecting(other))
                && ((isThisVerticalToX && isOtherVerticalToX)
                || (Comparison.equal(this.findSlope(), other.findSlope())))) {
            double combDistance = this.start.distance(this.end) + other.start.distance(other.end);
            if (Comparison.equal(combDistance, this.start.distance(other.start))) {
                return this.end;
            }
            if (Comparison.equal(combDistance, this.start.distance(other.end))) {
                return this.end;
            }
            if (Comparison.equal(combDistance, this.end.distance(other.start))) {
                return this.start;
            }
            if (Comparison.equal(combDistance, this.end.distance(other.end))) {
                return this.start;
            }
            return null;
            /*If both lines intersecting with each other and having the same slop, it means they can have more
              than one intersection point, if the sum of their lengths equals the sum of 2 edges of the lines
              (one from 'This' line and the other from 'Other' line), it means they intersect with the 2 other
               edges of them. */
        }
        if (this.isIntersecting(other)
                && ((isThisVerticalToX && !isOtherVerticalToX) || (!isThisVerticalToX && isOtherVerticalToX))) {
            if (isThisVerticalToX && !isOtherVerticalToX) {
                Point point = new Point(this.start.getX(), other.yValueForGivenX(this.start.getX()));
                return point;
            }
            if (!isThisVerticalToX && isOtherVerticalToX) {
                Point point = new Point(other.start.getX(), this.yValueForGivenX(other.start.getX()));
                return point;
            }
        }
        /* a different checks is being done for the case one of the lines is vertical to x-axis and the slope
           tends to infinity.*/

        if (this.isIntersecting(other) && (!Comparison.equal(this.findSlope(), other.findSlope()))) {
            /*If the lines intersect and have different slopes, they have exactly one intersection point,
             the calculation is based on the 'linear equation' structure. */

            double slopeThisLine = this.findSlope();
            double slopeOtherLine = other.findSlope();
            double xValue = (((other.start.getX() * -slopeOtherLine) + other.start.getY())
                    - (this.start.getX() * -slopeThisLine + this.start.getY()))
                    / (slopeThisLine - slopeOtherLine);
            double yValue = slopeThisLine * (xValue - this.start.getX()) + this.start.getY();
            Point intersectionPoint = new Point(xValue, yValue);
            return intersectionPoint;
        }
        return null;
    }

     /**
     * A method that returns true if the lines are equal, false otherwise.
     * @param other ('Line' object) - the line that is checked if it equals to 'This' line.
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start)) && (this.end.equals(other.end)));
    }

    /**
     * A method that gets an 'Rectangle' object and checks what is the closest intersection point between it and the
     * line and returns it, if they don't intersect it returns null.
     * @param rect the rectangle that being checked for his closes collusion point with the line.
     * @return the closest intersection point between the rectangle and the line and returns it,
     * if they don't intersect it returns null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> points = rect.intersectionPoints(this);
        //checks all the intersection points.

        if (points.isEmpty()) {
            return null;
        }
        Point closestPoint = null;
        Double minDistance = null;

        for (Point point : points) {
            double distance = Math.abs(this.start.distance(point));
            if (minDistance == null || distance < minDistance) {
                closestPoint = point;
                minDistance = distance;
            }
        }
        return closestPoint;
    }
}

