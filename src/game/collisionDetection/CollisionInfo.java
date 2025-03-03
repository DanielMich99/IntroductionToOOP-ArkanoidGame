
package game.collisionDetection;

import game.geometry.Point;
import game.sprites.Collidable;

/**
 * @author Daniel Michaelshvili.
 * The 'CollisionInfo' class has been created to assist the developer of the game tounderstand what is collision
 * information of the ball when it collides with any object.
 */
public class CollisionInfo {

    /*Represents the point when the collusion occurs.*/
    private Point collisionPoint;

    /*Represents the collidable object that the ball collides with.*/
    private Collidable collisionObject;

    /**
     * A constructor to a 'CollisionInfo' object which sets his collision point and collision object.
     * @param collisionPoint ('Point' object) - the point when the collusion occurs.
     * @param collisionObject ('Collidable' interface object) - the collidable object that the ball collides with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * A method that returns the collusion point from the 'CollisionInfo' object.
     * @return the collusion point from the 'CollisionInfo' object.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * A method that returns the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
