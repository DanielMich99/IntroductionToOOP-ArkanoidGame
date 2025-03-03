//207795030 Daniel Michaelshvili

package game;

import biuoop.KeyboardSensor;
import game.animations.GameLevel;
import game.animations.GameOverScreen;
import game.animations.YouWinScreen;
import game.levels.LevelInformation;
import java.util.List;

/**
 * @author Daniel Michaelshvili
 * The 'GameFlow' class has been created for the option to run severall levels sequentially in the game.
 * */
public class GameFlow {

    /*Represents the animation runner that will run all the animations throughout the game.*/
    private AnimationRunner animationRunner;

    /*Represents the keyboard sensor that will react to every press on the keyboard during the run of the game.*/
    private KeyboardSensor keyboardSensor;

    /*A counter for the score in current time during the game.*/
    private Counter score;

    /**
     * A constructor to 'GameFlow' object.
     * @param animationRunner ('AnimationRunner' object) - the animation runner that will run the animations throughout
     * the game.
     * @param keyboardSensor ('KeyboardSensor' object) - the keyboard sensor that will react to every press on the
     * keyboard during the run of the game.
     * @param score ('Counter' object) - A counter for the score in current time during the game.
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor, Counter score) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.score = score;
    }

    /**
     * A method that actually activate the run of the game with several levels in it.
     * @param levels ('List<>' object) - a list of all the levels that will run in the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(this.animationRunner, this.keyboardSensor, levelInfo, this.score);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            if (level.remindingBalls() == 0) {
                this.animationRunner.run(new GameOverScreen(keyboardSensor, score.getValue()));
                return;
            }
            //checks if there are any remaining balls in the game that are not 'dead'.

        }
        this.animationRunner.run(new YouWinScreen(keyboardSensor, score.getValue()));
        //if the user succeed to pass all the levels he won and suitable animation will be shown for that.

    }
}
