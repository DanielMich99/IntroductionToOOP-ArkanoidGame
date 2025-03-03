package game.levels;

import game.collisionDetection.Velocity;
import game.sprites.Block;
import game.sprites.Sprite;
import game.sprites.Ball;
import java.util.List;

/**
 * @author Daniel Michaelshvili
 * The 'LevelInformation' inteface has been created so there will be an interface for all the leveles that can be in the
 * game.
 */
public interface LevelInformation {

    /**
     * A method that returns the number of ball in the level.
     * @return the number of ball in the level.
     */
    int numberOfBalls();

    /**
     * A method that returns a list of all the balls in the level.
     * @return a list of all the balls in the level.
     */
    List<Ball> balls();

    /**
     * A method that returns a list of all the initial velocities of the balls in the level.
     * @return a list of all the initial velocities of the balls in the level.
     */
    List<Velocity> initialBallVelocities();

    /**
     * A method that sets the initial velocities of all the balls in the level.
     */
    void setVelocities();

    /**
     * A method that returns how many pixels moves the paddle in the level per move.
     * @return how many pixels moves the paddle in the level per move.
     */
    int paddleSpeed();

    /**
     * A method that returns the width of the paddle in the level.
     * @return the width of the paddle in the level.
     */
    int paddleWidth();

    /**
     * A method that returns the level name.
     * @return the level name.
     */
    String levelName();

    /**
     * A method that returns a sprite with the background of the level.
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * A method that returns a list of all the blocks in the level.
     * @return a list of all the blocks in the level.
     */
    List<Block> blocks();

    /**
     * A method that returns the number of blocks that should be removed in the level.
     * @return the number of blocks that should be removed in the level.
     */
    int numberOfBlocksToRemove();
}
