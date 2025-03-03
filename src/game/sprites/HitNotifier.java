
package game.sprites;

import game.hitListeners.HitListener;

/**
 * @author Daniel Michaelshvili
 * The 'HitNotifier' inteface has been created so there will be an interface for all the HitNotifier members that can be
 * inculuded in the game, so that they will be able to update the other HitListeners objects about certain
 * actions that happend in the game.
 */
public interface HitNotifier {

    /**
     * A method that add 'hl' as a 'HitListener' to the 'listeners' list of the sprite.
     * @param hl ('HitListener' object) - the 'HitListener' object that being added to the sprite listeners list.
     */
    void addHitListener(HitListener hl);

    /**
     * A method that removes 'hl' from the list of listeners to hit events of the sprite.
     * @param hl  ('HitListener' object) - the 'HitListener' object that being removed from the sprite listeners list.
     */
    void removeHitListener(HitListener hl);
}
