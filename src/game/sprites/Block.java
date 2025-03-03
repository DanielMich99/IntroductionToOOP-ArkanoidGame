
package game.sprites;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import game.hitListeners.HitListener;
import game.geometry.Rectangle;
import game.geometry.Point;
import game.geometry.Line;
import game.collisionDetection.Velocity;
import game.animations.GameLevel;

/**
 * @author Daniel Michaelshvili
 * The 'Block' class has been created to organize all the methods and attrributs connected to the blocks that are part
 * of the game.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    /*Represents the shape of the block that is a rectangle.*/
    private Rectangle rectangle;

    /*Represents the color of the block.*/
    private Color color;

    /*represent the list of the 'listeners' in the game that activate the actions that need to happen when a ball hit
      a block in some certain scenario. */
    private List<HitListener> hitListeners;

    @Override
    /**
     * A method that returns the shape of the block itself and with it all its attributes
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    /**
     * A method that checks what should be the ball velocity after colliding with 'This' block and returns it, also
     * updates the listeners of the block about the hit.
     * @param collisionPoint ('Point' object) - represents the collusion point with the ball.
     * @param currentVelocity ('Velocity' object) - represents the velocity the ball.
     * @param hitter ('Ball' object) - represents the ball that hit the block.
     * @return the new velocity of the ball after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity updatedVelocity = currentVelocity;
        if (this.rectangle.getLeftLine().isOnLine(collisionPoint)
                || this.rectangle.getRightLine().isOnLine(collisionPoint)) {
            updatedVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            this.notifyHit(hitter);
        }
        //checks the scenario the ball it hits the surface of the block in the sides.

        if (this.rectangle.getTopLine().isOnLine(collisionPoint)
                || this.rectangle.getBottomLine().isOnLine(collisionPoint)) {
            updatedVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            this.notifyHit(hitter);
        }
        //checks the scenario the ball it hits the surface of the block in the top or in the bottom of it.

        return updatedVelocity;
    }

    @Override
    /**
     * A method that checks if the ball will collide with the block in a specific 'line' trajectory.
     * @param line ('Line' object) - represents the line that the ball moves on.
     * @return true if collusion happened, false otherwise.
     */
    public boolean isCollusionOccur(Line line) {
        return (line.closestIntersectionToStartOfLine(this.rectangle) != null);
    }

    @Override
    /**
     * A method that draws the block on the given DrawSurface - 'd'.
     * @param d ('DrawSurface' object) - the surface that the block will be drawn on.
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

    @Override
    /**
     * A method that indicates the block that it is time to do his work in the loop of the game run.
     */
    public void timePassed() {

    }

    /**
     * A method that add the block to the game list of its sprites and to the collidables list of the game.
     * @param g ('Game' object) - the game that the block is added to.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
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
     * A constructor that creats a new 'Block' object.
     * @param rectangle ('Rectangle' object) - the rectangle shape that the block actually is.
     * @param color ('Color' object) - the color of the block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * A method that removes the block from the game.
     * @param game ('Game' object) - the game the ball is being removed from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * A method that iterate on the 'HitListeners' list of the sprite and update them that a ball hit that block.
     * @param hitter ('Ball' object) - the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Make a copy of the hitListeners before iterating over them.

        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
        // Notify all listeners about a hit event:

    }
}
