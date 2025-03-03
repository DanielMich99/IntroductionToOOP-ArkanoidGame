//207795030 Daniel Michaelshvili

package game;

import java.util.ArrayList;
import biuoop.DrawSurface;
import game.sprites.Sprite;

/**
 * @author Daniel Michaelshvili
 * The 'SpriteCollection' class has been created to organize all the information of the game 'Sprites' objects and
 * store them in a list.
 */
public class SpriteCollection {

    /*The list of all the sprites objects in the game.*/
    private java.util.List<Sprite> spriteList;

    /**
     * A constructor to the 'SpriteCollection' object that initialize an empty list of 'Sprites'.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * A method that add the given sprite to the list of sprites.
     * @param s ('Sprite' interface object) - the sprite object that should be added to the game.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * A method that removes 's' Sprite from the sprites list of the game.
     * @param s ('Sprite' object) - the Sprite that being removed from the sprites list of the game.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * A method that notify all the sprites of the game that it is the time for them to do their work in order to
     * make the animation going on.
     */
    public void notifyAllTimePassed() {
        java.util.List<Sprite> sprites = new ArrayList<Sprite>(this.spriteList);
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * A method that order to all the sprites to be drawn on the GUI draw Surface.
     * @param d ('DrawSurface' object) - the draw Surface th sprites should be drawn on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.spriteList) {
            sprite.drawOn(d);
        }
    }
}
