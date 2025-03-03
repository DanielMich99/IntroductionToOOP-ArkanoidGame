//207795030 Daniel Michaelshvili

package game.sprites;

import java.util.ArrayList;
import game.geometry.Line;
import game.geometry.Point;
import game.collisionDetection.CollisionInfo;

/**
 * @author Daniel Michaelshvili
 * The 'GameEnvironment' class has been created to organize all the information of the game collidables objects and
 * store them in a list.
 *
 */
public class GameEnvironment {

    /*The list of all the collidables objects in the game.*/
    private java.util.List<Collidable> collidables;

    /**
     * A constructor to the 'GameEnvironment' object that initialize an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * A method that returns a collidablle object of the game from the list that stored in certain index in it.
     * @param index (int Type) - the index of the collidable in the list.
     * @return a collidablle object of the game from the list that stored in certain index in it.
     */
    public Collidable getCollidable(int index) {
        return this.collidables.get(index);
    }

    /**
     * A method that add the given collidable to the environment.
     * @param c ('Collidable' interface object) - the collidable object that should be added to the game.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * A method that removes a collidable from the list of collidables of the game environment.
     * @param c ('Collidable' object) - the collidable that being removed from the collidables list.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * A method that checks if an object that moves from the start of 'trajectory' to the end of 'trajectory' is collide
     * with any of the collidables in the list, and if it does it return the information of the closest collusion,
     * otherwise it returns no information (null).
     * @param trajectory ('Line' object) - the line that the object trajectory is on.
     * @return the information of the closest collusion of the object,
     * if there is no collusion it returns no information (null).
     */
    public CollisionInfo getClosestCollusion(Line trajectory) {
        Collidable closestColidable = null;
        Point closestCollusionPoint = null;
        Double minDistance = null;
        for (Collidable colidable : this.collidables) {
            if (colidable.isCollusionOccur(trajectory)) {
                Point collusionPoint = trajectory.closestIntersectionToStartOfLine(colidable.getCollisionRectangle());
                if ((closestCollusionPoint == null) || (trajectory.start().distance(collusionPoint) < minDistance)) {
                    closestColidable = colidable;
                    closestCollusionPoint = collusionPoint;
                    minDistance = trajectory.start().distance(closestCollusionPoint);
                }
                //checks if this collusion is more close than the current closer collusion.
            }
        }
        return new CollisionInfo(closestCollusionPoint, closestColidable);
    }
}