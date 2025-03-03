//207795030 Daniel Michaelshvili

package game.hitListeners;

import game.Counter;
import game.animations.GameLevel;;
import game.sprites.Block;
import game.sprites.Ball;

/**
 * @author Daniel Michaelshvili
 * The 'BlockRemover' class has been created for the option to remove a block from the game if its getting hit from a
 * ball during the game. */
public class BlockRemover implements HitListener {

    /*represents the game that the listener is removing the block from.*/
    private GameLevel game;

    /*represents the amount of the blocks are in the game, (not including the limits of the game).*/
    private Counter remainingBlocks;

    /**
     * A constructor to the 'BlockRemover' object.
     * @param game ('Game' object) - the game that the listener is removing the block from.
     * @param remainingBlocks ('Counter' object) - the amount of the blocks are in the game.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * A method that being called whenever the beingHit object is hit,
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit ('Block' object) - the block that being hit in the hitEvent.
     * @param hitter ('Ball' object) - the ball that hit the block in the HitEvent.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
