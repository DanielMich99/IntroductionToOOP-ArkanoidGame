//207795030 Daniel Michaelshvili

package game.sprites;

import biuoop.DrawSurface;
import game.animations.GameLevel;

/**
 * @author Daniel Michaelshvili
 * The 'Sprite' inteface has been created so there will be an interface for all the sprites members that can be
 * inculuded in the game, so that they will be able to inherit methods from it.
 */
public interface Sprite {

    /**
     * A method that draws the sprite on the screen.
     * @param d ('DrawSurface' object) - the surface that the sprite will be drawn on.
     */
    void drawOn(DrawSurface d);

    /**
     * A method that indicates the sprite that it is time to do his work in the loop of the game run.
     */
    void timePassed();

    /**
     * A method that add the sprite to the game list of its sprites.
     * @param g ('Game' object) - the game that the sprite is added to.
     */
    void addToGame(GameLevel g);
}