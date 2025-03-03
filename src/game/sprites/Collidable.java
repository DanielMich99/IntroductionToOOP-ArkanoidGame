//207795030 Daniel Michaelshvili

package game.sprites;

import game.geometry.Rectangle;
import game.geometry.Point;
import game.geometry.Line;
import game.collisionDetection.Velocity;

/**
 * @author Daniel Michaelshvili
 * The 'Collidable' inteface has been created so there will be an interface for all the collidables members that can be
 * inculuded in the game, so that they will be able to inherit methods from it.
 */
public interface Collidable {

    /**
     * A method that responsible to return the information about the shape of the rectangle that an object in the game
     * has collided with.
     * @return the information about the shape of the rectangle that an object in the game has collided with.
     */
    Rectangle getCollisionRectangle();

    /**
     * A method that checks what should be the ball velocity after colliding with 'This' collidable and returns it.
     * @param collisionPoint ('Point' object) - represents the collusion point with the ball.
     * @param currentVelocity ('Velocity' object) - represents the velocity the ball.
     * @param hitter ('Ball' object) - represents the ball that hit the block.
     * @return the new velocity of the ball after the hit.
     * */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * A method that checks if the ball will collide with the collidable in a specific 'line' trajectory.
     * @param line ('Line' object) - represents the line that the ball moves on.
     * @return true if collusion happened, false otherwise.
     */
    boolean isCollusionOccur(Line line);
}
