
package game.hitListeners;

import game.Counter;
import game.animations.GameLevel;
import game.sprites.Ball;
import game.sprites.Block;


/**
 * @author Daniel Michaelshvili
 * The 'BallRemover' class has been created for the option to remove a ball from the game if it hitts the bottom block,
 * usses the 'listener' pattern and when it gets updated that a hit occured it does what need to be done for removing
 * the ball that made the hit.
 */
public class BallRemover implements HitListener {

    /*represents the game that the listener is removing the ball from.*/
    private GameLevel game;

    /*represents the amount of the balls are in the game.*/
    private Counter remainingBalls;

    /**
     * A constructor to the 'BallRemover' object.
     * @param game ('Game' object) - the game that the listener is removing the ball from.
     * @param remainingBalls ('Counter' object) - the amount of the balls are in the game.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * A method that being called whenever the beingHit object is hit,
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit ('Block' object) - the block that being hit in the hitEvent.
     * @param hitter ('Ball' object) - the ball that hit the block in the HitEvent.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        hitter.removeHitListener(this);
        this.remainingBalls.decrease(1);
    }
}
