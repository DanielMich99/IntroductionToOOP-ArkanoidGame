
package game.sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import game.geometry.Rectangle;
import game.geometry.Point;
import game.geometry.Line;
import game.collisionDetection.Velocity;
import game.collisionDetection.Comparison;
import game.animations.GameLevel;


/**
 * @author Daniel Michaelshvili
 * The 'Paddle' class has been created for the option to get a 'Paddle' object to add to the game with all its required
 * methods and attributes.
 */
public class Paddle implements Sprite, Collidable {

    private static final int NUM_HIT_AREAS = 5;
    private static final int FIRST_AREA_ANGLE = 300;
    private static final int SECOND_AREA_ANGLE = 330;
    private static final int FOURTH_AREA_ANGLE = 30;
    private static final int FIFTH_AREA_ANGLE = 60;
    private static final int GUI_WIDTH = 800;
    private static final int BOARDER_BLOCK_WIDTH = 10;

    /*Represents the keyboard sensor that gets the information on what the user press during the run of the game. */
    private KeyboardSensor keyboard;

    /*Represents the shape of the paddle that is a rectangle.*/
    private Rectangle rectangle;

    /*Represents the color of the paddle.*/
    private final Color color;

    /*Represents the list of all the balls in the start of the game. */
    private java.util.List<Ball> balls;

    /*Represents how many pixels the paddle moves per move.*/
    private int speed;

    /**
     * A constructor to the 'Paddle' object that sets him the keybord sensor, the rectangle shape of him and the balls
     * that it hits in the game.
     * @param keyboard ('biuoop.KeyboardSensor' object) - the keyboard sensor that set to the paddle.
     * @param rectangle ('Rectangle' object) - the rectangle shape that set to the paddle.
     * @param balls ('java.util.List<>' object) - the list of all the balls in the start of the game.
     * @param speed ('int' type) - how many pixels the paddle moves per move.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, java.util.List<Ball> balls, int speed) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.balls = new ArrayList<Ball>(balls);
        this.color = Color.YELLOW;
        this.speed = speed;
    }

    /**
     * A method that indicates the paddle that it is time to do his work in the loop of the game run.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * A method that draws the paddle on the given DrawSurface - 'd'.
     * @param d ('DrawSurface' object) - the surface that the paddle will be drawn on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        this.rectangle.getLeftLine().drawOn(d);
        this.rectangle.getRightLine().drawOn(d);
        this.rectangle.getTopLine().drawOn(d);
        this.rectangle.getBottomLine().drawOn(d);
    }

    /**
     * A method that add the paddle to the game list of its sprites and to the collidables list of the game.
     * @param g ('Game' object) - the game that the paddle is added to.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * A method that returns the shape of the paddle itself and with it all its attributes.
     * @return the information about the shape of the rectangle that an object in the game has collided with.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * A method that checks what should be the ball velocity after colliding with 'This' paddle and returns it.
     * @param collisionPoint ('Point' object) - represents the collusion point with the ball.
     * @param currentVelocity ('Velocity' object) - represents the velocity the ball.
     * @param hitter ('Ball' object) - represents the ball that hit the block.
     * @return the new velocity of the ball after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity updatedVelocity = currentVelocity;
        double speed = currentVelocity.getDx() * currentVelocity.getDx()
                + currentVelocity.getDy() * currentVelocity.getDy();
        speed = Math.sqrt(speed);
        //represents the speed of the ball based on calculation that based on 'Pitagoras' rules.

        if ((this.rectangle.getLeftLine().isOnLine(collisionPoint) && (currentVelocity.getDx() > 0))
                || (this.rectangle.getRightLine().isOnLine(collisionPoint) && (currentVelocity.getDx() < 0))) {
            updatedVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            return updatedVelocity;
        }
        //checks the scenario the ball hits the surface of the paddle in the sides.

        if (this.rectangle.getTopLine().isOnLine(collisionPoint) && currentVelocity.getDy() > 0) {
            //checks the scenario the ball hits the surface of the paddle in the top of it.

            double paddleLength = this.rectangle.getTopLine().length();
            if (collisionPoint.getX() <= ((paddleLength / NUM_HIT_AREAS) + this.rectangle.getUpperLeft().getX())) {
                updatedVelocity = Velocity.fromAngleAndSpeed(FIRST_AREA_ANGLE, speed);
                updatedVelocity.setDy(-updatedVelocity.getDy());
                return updatedVelocity;
            }
            if ((((paddleLength / NUM_HIT_AREAS) + this.rectangle.getUpperLeft().getX()) < collisionPoint.getX())
                && (collisionPoint.getX() <= (2 * (paddleLength / NUM_HIT_AREAS)
                    + this.rectangle.getUpperLeft().getX()))) {
                updatedVelocity = Velocity.fromAngleAndSpeed(SECOND_AREA_ANGLE, speed);
                updatedVelocity.setDy(-updatedVelocity.getDy());
                return updatedVelocity;
            }
            if (((2 * (paddleLength / NUM_HIT_AREAS) + this.rectangle.getUpperLeft().getX()) < collisionPoint.getX())
                    && (collisionPoint.getX() <= (3 * (paddleLength / NUM_HIT_AREAS)
                    + this.rectangle.getUpperLeft().getX()))) {
                updatedVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                return updatedVelocity;
            }
            if (((3 * (paddleLength / NUM_HIT_AREAS) + this.rectangle.getUpperLeft().getX()) < collisionPoint.getX())
                    && (collisionPoint.getX() <= (4 * (paddleLength / NUM_HIT_AREAS)
                    + this.rectangle.getUpperLeft().getX()))) {
                updatedVelocity = Velocity.fromAngleAndSpeed(FOURTH_AREA_ANGLE, speed);
                updatedVelocity.setDy(-updatedVelocity.getDy());
                return updatedVelocity;
            }
            if (((4 * (paddleLength / NUM_HIT_AREAS) + this.rectangle.getUpperLeft().getX()) < collisionPoint.getX())
                    && (collisionPoint.getX() <= (paddleLength + this.rectangle.getUpperLeft().getX()))) {
                updatedVelocity = Velocity.fromAngleAndSpeed(FIFTH_AREA_ANGLE, speed);
                updatedVelocity.setDy(-updatedVelocity.getDy());
                return updatedVelocity;
            }
        }
        /*the surface of the top of the paddle divided to 5 equal in length and different lines, for each of them
          the ball returns from them in different angle, from the first fifth it returns in 300 angle, from the second
          in 330 angle, from the third it returns normally like its collided with a block, from the fourth in 30 angle
          and from the fifth in 60 angle. (the assumption that the opposite vertical to x-axis direction is 0 degrees
          angle).*/

        return updatedVelocity;
    }

    /**
     * A method that checks if the ball will collide with the paddle in a specific 'line' trajectory.
     * @param line ('Line' object) - represents the line that the ball moves on.
     * @return true if collusion happened, false otherwise.
     */
    public boolean isCollusionOccur(Line line) {
        return (line.closestIntersectionToStartOfLine(this.rectangle) != null);
    }

    /**
     * A method that checks if the ball will be inside the paddle if the paddle will move 'x' units to one of the sides.
     * it helps to avoid that situation.
     * @param ball ('Ball' object) - the ball that being checked if it will be in the paddle.
     * @param x ('double' type) - the number of units the paddle will move to the left/right side.
     * @return true if the ball will be inside the paddle, false otherwise.
     */
    private boolean isBallInPedal(Ball ball, double x) {
        double paddleRightLimit = this.getCollisionRectangle().getUpperRight().getX();
        double paddleLeftLimit = this.getCollisionRectangle().getUpperLeft().getX();
        double paddleTopLimit = this.getCollisionRectangle().getUpperLeft().getY();
        double paddleBottomLimit = this.getCollisionRectangle().getLowerLeft().getY();
        return (((ball.getCenter().getX() < paddleRightLimit + x) || (Comparison.equal(ball.getCenter().getX(),
                paddleRightLimit + x))) && ((ball.getCenter().getX() > paddleLeftLimit + x)
                || (Comparison.equal(ball.getCenter().getX(), paddleLeftLimit + x)))
                && ((ball.getCenter().getY() > paddleTopLimit)
                || (Comparison.equal(ball.getCenter().getY(), paddleTopLimit)))
                && ((ball.getCenter().getY() < paddleBottomLimit)
                || (Comparison.equal(ball.getCenter().getY(), paddleBottomLimit))));
    }

    /**
     * A method that checks if the player of the game press the left arrow at the moment and if it does and the paddle
     * shouldn't "swallow" the ball in that move it will move one move left.
     */
    public void moveLeft() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            for (Ball ball : this.balls) {
                if (this.isBallInPedal(ball, -this.speed)) {
                    return;
                }
            }
            // checks that none of the balls in the game will be swallowed in that move.

            if ((this.rectangle.getUpperLeft().getX() - this.speed) > BOARDER_BLOCK_WIDTH) {
                this.rectangle.moveRectangleLeft(this.speed);
            } else {
                this.rectangle.moveRectangleLeft((this.rectangle.getUpperLeft().getX()) - BOARDER_BLOCK_WIDTH);
            }
            /* if the paddle is going to cross the limit of the board in that move it moves him just enough, so it will
               not pass the limit of the game board.*/

        }
    }

    /**
     * A method that checks if the player of the game press the right arrow at the moment and if it does and the paddle
     * shouldn't "swallow" the ball in that move it will move one move right.
     */
    public void moveRight() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            for (Ball ball : this.balls) {
                if (this.isBallInPedal(ball, this.speed)) {
                    return;
                }
            }
            // checks that none of the balls in the game will be swallowed in that move.

            if ((this.rectangle.getUpperRight().getX() + this.speed) < GUI_WIDTH - BOARDER_BLOCK_WIDTH) {
                this.rectangle.moveRectangleRight(this.speed);
            } else {
                this.rectangle.moveRectangleRight((GUI_WIDTH - BOARDER_BLOCK_WIDTH)
                        - this.rectangle.getUpperRight().getX());
            }
            /* if the paddle is going to cross the limit of the board in that move it moves him just enough, so it will
               not pass the limit of the game board.*/

        }
    }



}
