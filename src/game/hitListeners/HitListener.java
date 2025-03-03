//207795030 Daniel Michaelshvili

package game.hitListeners;

import game.sprites.Ball;
import game.sprites.Block;

/**
 * @author Daniel Michaelshvili
 * The 'HitListener' inteface has been created so there will be an interface for all the HitListener members that can be
 * inculuded in the game, so that they will be able to be updated from the other HitNotifiers objectד about certain
 * actions that happend in the game.
 */
public interface HitListener {

    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.

    /**
     * A method that being called whenever the beingHit object is hit,
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit ('Block' object) - the block that being hit in the hitEvent.
     * @param hitter ('Ball' object) - the ball that hit the block in the HitEvent.
     */
    void hitEvent(Block beingHit, Ball hitter);

}
