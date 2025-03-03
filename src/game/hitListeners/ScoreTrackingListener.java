
package game.hitListeners;

import game.Counter;
import game.sprites.Ball;
import game.sprites.Block;

/**
 * @author Daniel Michaelshvili
 * The 'ScoreTrackingListener' class has been created for the option to update the scoring counter of the game
 * while the game runs and hits occure.*/
public class ScoreTrackingListener implements HitListener {

    /*represents the counter of the score of the game.*/
    private Counter currentScore;

    /**
     * A constructor to the 'ScoreTrackingListener' object, that sets him the counter of the game.
     * @param scoreCounter ('Counter' object) - ('Counter' object) - the counter of the score of the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * A method that being called whenever the beingHit object is hit,
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit ('Block' object) - the block that being hit in the hitEvent.
     * @param hitter ('Ball' object) - the ball that hit the block in the HitEvent.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        this.currentScore.increase(5);
    }
}
