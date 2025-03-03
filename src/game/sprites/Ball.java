
package game.sprites;

import biuoop.DrawSurface;
import game.geometry.Point;
import game.collisionDetection.Comparison;
import game.collisionDetection.Velocity;
import game.geometry.Line;
import game.animations.GameLevel;
import game.hitListeners.HitListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Michaelshvili.
 * The 'Ball' class has been created to assist make animations that require involving 2d balls objects (circles),
 * in the project.
 */
public class Ball implements Sprite, HitNotifier {

    private static final int TWO_DIVIDER = 2;
    private static final int ZERO = 0;

    /*Represents the number of units the ball should move back from his collusion point when it collides
     with an object */
    private static final double EPSILON = 1;

    /*Represents the center point of the 2d ball.*/
    private Point center;

    /*Represents the radius of the 2d ball.*/
    private int r;

    /*Represents the Color of the 2d ball.*/
    private final java.awt.Color color;

    /*Represents the velocity of the 2d ball in motion while the animation is running.*/
    private Velocity velocity;

    /*Represents the left limit of the 2d ball can be drawn in the GUI surface.*/
    private int leftLimit;

    /*Represents the right limit of the 2d ball can be drawn in the GUI surface.*/
    private int rightLimit;

    /*Represents the top limit of the 2d ball can be drawn in the GUI surface.*/
    private int topLimit;

    /*Represents the bottom limit of the 2d ball can be drawn in the GUI surface.*/
    private int bottomLimit;

    /*Represents the game environment of the ball during the game run.*/
    private GameEnvironment gameEnvironment;

    /*Indicates if the ball colliding with any object in his current trajectory.*/
    private boolean isCollideInTrajectory;

    /*Represents the trajectory of the ball during the game run.*/
    private Line trajectory;

    /*Represents the next collusion of the ball in his current trajectory.*/
    private Point nextCollusionPoint;

    /*represents the next object the ball collide with in his current trajectory.*/
    private Collidable nextCollidable;

    /*represent the list of the 'listeners' in the game that activate the actions that need to happen when a ball hit
      a block in some certain scenario. */
    private List<HitListener> hitListeners;

    /**
     * A constructor for a 'Ball' object.
     * @param center ('Point' object) - the center point of the ball that will be created.
     * @param r (int Type) - the radius size of the ball that will be created.
     * @param color ('java.awt.Color' object) - the color of the ball that will be created.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * A second constructor for a 'Ball' object.
     * @param xValue (int Type) - the x value of the center point of the ball that will be created.
     * @param yValue (int Type) - the y value of the center point of the ball that will be created.
     * @param r (int Type) - the radius size of the ball that will be created.
     * @param color ('java.awt.Color' object) - the color of the ball that will be created.
     */
    public Ball(int xValue, int yValue, int r, java.awt.Color color) {
        this.center = new Point(xValue, yValue);
        this.r = r;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * A method that sets a new center point to the ball.
     * @param point (Point Object) - the new center for the ball.
     */
    public void setCenter(Point point) {
        this.center = point;
    }

    /**
     * A method that sets the velocity of the ball while he will be moving in the GUI animation.
     * @param v ('Velocity' object) - the velocity that will be set to the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * A method that sets all the limits of the ball during his animation movement in the game.
     * @param leftLimit (int Type) - the left limit that is going to be set to the ball.
     * @param rightLimit (int Type) - the right limit that is going to be set to the ball.
     * @param topLimit (int Type) - the top limit that is going to be set to the ball.
     * @param bottomLimit (int Type) - the bottom limit that is going to be set to the ball.
     */
    public void setLimits(int leftLimit, int rightLimit, int topLimit, int bottomLimit) {
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
        this.topLimit = topLimit;
        this.bottomLimit = bottomLimit;
    }

    /**
     * A method that sets the game environment of the ball during the game run.
     * @param gameEnvironment ('GameEnvironment' object) - the game environment set to the ball
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * A method that sets the next collidable object to the ball in his current trajectory.
     */
    public void setNextCollidable() {
        this.nextCollidable = this.gameEnvironment.getClosestCollusion(this.trajectory).collisionObject();
    }

    /**
     * A method that sets the question if the ball is going to collide with any object in his current trajectory.
     */
    public void setIsCollideInTrajectory() {
        this.isCollideInTrajectory = this.nextCollusionPoint != null;
    }

    /**
     * A method that sets the current trajectory of the ball.
     */
    public void setTrajectory() {
        this.trajectory = this.computeTrajectory();
    }

    /**
     * A method that sets the next collusion point of the ball in his current trajectory.
     */
    public void setNextCollusionPoint() {
        this.nextCollusionPoint = this.gameEnvironment.getClosestCollusion(this.trajectory).collisionPoint();
    }

    /**
     * A method that sets all the attributes that connected to the current trajectory of the ball.
     */
    public void setTrajectoryAttributes() {
        this.setTrajectory();
        this.setNextCollusionPoint();
        this.setNextCollidable();
        this.setIsCollideInTrajectory();
    }

    /**
     * A method that return the center of the ball.
     * @return the center of the ball.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * A method that returns the x value of the center point of the ball.
     * @return the x value of the center point of the ball.
     */
    public int getX() {
        return  (int) Math.round(this.center.getX());
    }

    /**
     * A method that returns the y value of the center point of the ball.
     * @return the y value of the center point of the ball.
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * A method that returns the color of the ball.
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    @Override
    /**
     * A method that draws the ball on the given DrawSurface - 'surface'.
     * @param surface ('DrawSurface' object) - the surface that the ball will be drawn on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * A method that indicates the ball that it is time to do his work in the loop of the game run.
     */
    @Override
    public void timePassed() {
        this.newMoveOneStep();
    }

    /**
     * A method that add the ball to the game list of its sprites.
     * @param g ('Game' object) - the game that the ball is added to.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * A method that add 'hl' as a 'HitListener' to the 'listeners' list of the sprite.
     * @param hl ('HitListener' object) - the 'HitListener' object that being added to the sprite listeners list.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * A method that removes 'hl' from the list of listeners to hit events of the sprite.
     * @param hl  ('HitListener' object) - the 'HitListener' object that being removed from the sprite listeners list.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * A method that fixes any issues that can happen to represent the first frame on an animation of a moving ball
     * on the GUI surface.
     */
    public void fixFirstFrame() {
        int minWidthAndHeight = Math.min(this.rightLimit - this.leftLimit, this.bottomLimit - this.topLimit);
        if (this.r > minWidthAndHeight / TWO_DIVIDER) {
            this.r = minWidthAndHeight / TWO_DIVIDER;
        }
        /*if the radius of the ball is too big, the program reduces it to the maximum it won't deviate the limits.*/

        if (this.center.getX() - this.r < this.leftLimit) {
            this.setCenter(new Point(this.r + this.leftLimit, this.center.getY()));
        }
        if (this.center.getX() + this.r > this.rightLimit) {
            this.setCenter(new Point(this.rightLimit - this.r, this.center.getY()));
        }
        if (this.center.getY() - this.r < this.topLimit) {
            this.setCenter(new Point(this.center.getX(), this.r + this.topLimit));
        }
        if (this.center.getY() + this.r > this.bottomLimit) {
            this.setCenter(new Point(this.center.getX(), this.bottomLimit - this.r));
        }
        /*if the initial center of the ball makes him to deviate the limits of the surface, the program moves the ball,
          so it will touch the matching limit from inside the surface.*/

    }

    /**
     * A method that computes the trajectory of the ball during his movement until it hits one of the limits
     * of the board. it helps to get the understanding of how the ball should move in his current tranjectory.
     * @return the trajectory line of the ball from his current center to the point it hits the limit of the board.
     */
    private Line computeTrajectory() {
        Line trajectory = null;
        Line firstStepLine = new Line(this.center.getX(), this.center.getY(),
                this.center.getX() + this.velocity.getDx(), this.center.getY() + this.velocity.getDy());
        //represents the segment line of the first move the ball does in his current trajectory.

        Double slope = firstStepLine.findSlope();
        if (slope == null) {
            if (this.velocity.getDy() > ZERO) {
                trajectory = new Line(this.center, new Point(this.center.getX(), this.bottomLimit));
                return trajectory;
            } else {
                trajectory = new Line(this.center, new Point(this.center.getX(), this.topLimit));
                return trajectory;
            }
        }
        //handles the scenario the slop of the trajectory tends to infinity.

        if (Comparison.equal(slope, (double) ZERO)) {
            if (this.velocity.getDx() > ZERO) {
                trajectory = new Line(this.center, new Point(this.rightLimit, this.center.getY()));
                return trajectory;
            } else {
                trajectory = new Line(this.center, new Point(this.leftLimit, this.center.getY()));
                return trajectory;
            }
        }
        //handles the scenario the slop of the trajectory is zero.

        if (this.velocity.getDx() > ZERO) {
            if (this.topLimit < firstStepLine.yValueForGivenX(this.rightLimit)
                    && firstStepLine.yValueForGivenX(this.rightLimit) < this.bottomLimit) {
                trajectory = new Line(this.center, new Point(this.rightLimit,
                        firstStepLine.yValueForGivenX(this.rightLimit)));
                return trajectory;
            } else if (this.velocity.getDy() > ZERO) {
                trajectory = new Line(this.center, new Point(firstStepLine.xValueForGivenY(this.bottomLimit),
                        this.bottomLimit));
                return trajectory;
            } else if (this.velocity.getDy() < ZERO) {
                trajectory = new Line(this.center, new Point(firstStepLine.xValueForGivenY(this.topLimit),
                        this.topLimit));
                return trajectory;
            }
        }
        //handles the scenario the ball moves horizontally to the positive direction of x-axis.

        if (this.velocity.getDx() < ZERO) {
            if (this.topLimit < firstStepLine.yValueForGivenX(this.leftLimit)
                    && firstStepLine.yValueForGivenX(this.leftLimit) < this.bottomLimit) {
                trajectory = new Line(this.center, new Point(this.leftLimit,
                        firstStepLine.yValueForGivenX(this.leftLimit)));
                return trajectory;
            } else if (this.velocity.getDy() > ZERO) {
                trajectory = new Line(this.center, new Point(firstStepLine.xValueForGivenY(this.bottomLimit),
                        this.bottomLimit));
                return trajectory;
            } else if (this.velocity.getDy() < ZERO) {
                trajectory = new Line(this.center, new Point(firstStepLine.xValueForGivenY(this.topLimit),
                        this.topLimit));
                return trajectory;
            }
        }
        //handles the scenario the ball moves horizontally to the negative direction of x-axis.

        return trajectory;
    }

    /**
     * A method that checks how the ball should move in each and every sep it makes during the animation, responsible
     * for changing its velocity, trajectory attributes and of course his position.
     */
    private void newMoveOneStep() {
        Point checkPoint = this.velocity.applyToPoint(this.center);
        //a point that represents the next position the ball will have if he moves one step.

        this.setTrajectoryAttributes();
        if (!this.isCollideInTrajectory) {
            this.center = checkPoint;
            return;
        }
        //checks the scenario of that there is no collusion expected to the ball in his current trajectory.

        if (this.velocity.getDx() > ZERO && ((checkPoint.getX() > this.nextCollusionPoint.getX())
                || (Comparison.equal(Math.abs(checkPoint.getX() - this.nextCollusionPoint.getX()), (double) ZERO)))) {
            this.center.setX(this.nextCollusionPoint.getX() - EPSILON);
            if (this.velocity.getDy() > ZERO) {
                this.center.setY(this.nextCollusionPoint.getY() - EPSILON);
            }
            if (this.velocity.getDy() < ZERO) {
                this.center.setY(this.nextCollusionPoint.getY() + EPSILON);
            }
            this.velocity = this.nextCollidable.hit(this, this.nextCollusionPoint, this.velocity);
            return;
        }
        /*checks the scenario of that the ball's movement is in the positive direction of x-axis and
         reached his collusion point or passed it and returns it back to a natural position of him to be
         and change its velocity accordingly.*/

        if (this.velocity.getDx() < ZERO && ((checkPoint.getX() < this.nextCollusionPoint.getX())
                || (Comparison.equal(Math.abs(checkPoint.getX() - this.nextCollusionPoint.getX()), (double) ZERO)))) {
            this.center.setX(this.nextCollusionPoint.getX() + EPSILON);
            if (this.velocity.getDy() > ZERO) {
                this.center.setY(this.nextCollusionPoint.getY() - EPSILON);
            }
            if (this.velocity.getDy() < ZERO) {
                this.center.setY(this.nextCollusionPoint.getY() + EPSILON);
            }
            this.velocity = this.nextCollidable.hit(this, this.nextCollusionPoint, this.velocity);
            return;
        }
        /*checks the scenario of that the ball's movement is in the negative direction of x-axis and
         reached his collusion point or passed it and returns it back to a natural position of him to be
         and change its velocity accordingly.*/

        if (Comparison.equal(this.velocity.getDx(), (double) ZERO)) {
            if ((this.velocity.getDy() > ZERO) && ((checkPoint.getY() > this.nextCollusionPoint.getY())
                    || (Comparison.equal(Math.abs(checkPoint.getY() - this.nextCollusionPoint.getY()), (double) 0)))) {
                this.center.setY(this.nextCollusionPoint.getY() - EPSILON);
                this.velocity = this.nextCollidable.hit(this, this.nextCollusionPoint, this.velocity);
                return;
            }
            if ((this.velocity.getDy() < ZERO) && ((checkPoint.getY() < this.nextCollusionPoint.getY())
                    || (Comparison.equal(Math.abs(checkPoint.getY() - this.nextCollusionPoint.getY()),
                    (double) ZERO)))) {
                this.center.setY(this.nextCollusionPoint.getY() + EPSILON);
                this.velocity = this.nextCollidable.hit(this, this.nextCollusionPoint, this.velocity);
                return;
            }
        }
        /*checks the scenario of that the ball's movement is only vertical to x-axis and that it
         reached his collusion point or passed it and returns it back to a natural position of him to be
         and change its velocity accordingly.*/

        this.center = checkPoint; //if a collusion didn't accrue his expected next position will be set to his center.
    }

    /**
     * A method that removes the ball from the game.
     * @param game ('Game' object) - the game the ball is being removed from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}


