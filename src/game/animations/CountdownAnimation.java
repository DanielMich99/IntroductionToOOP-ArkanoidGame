
package game.animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.SpriteCollection;
import java.awt.Color;

/**
 * @author Daniel Michaelshvili.
 * The 'CountdownAnimation' class has been created to make an animation of a contdown before the start of every level
 * in the game.
 */
public class CountdownAnimation implements Animation {
    private static final double NUM_OF_SECONDS = 2;
    private static final int COUNT_FROM = 3;

    /*Represents how many seconds the countdown should take.*/
    private double numOfSeconds;

    /*Represents the number where the countdown start from.*/
    private int countFrom;

    /*Represents the flag that alert whether the animation should stop or not.*/
    private boolean stop;

    /*Represents the collection of sprites that will be shown on the screen while the countdown run.*/
    private SpriteCollection gameScreen;

    /**
     * A constructor for a 'CountdownAnimation' object. sets the number of seconds the animation should take,
     * the number where the countdown start from, the collection of sprites that will be shown on the screen while the
     * countdown run and the flag that alert whether the animation should stop or not.
     * @param gameScreen - ('SpriteCollection' object) - the collection of sprites that will be shown on the screen
     * while the countdown run.
     */
    public CountdownAnimation(SpriteCollection gameScreen) {
        this.numOfSeconds = NUM_OF_SECONDS;
        this.countFrom = COUNT_FROM;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    /**
     * A method that run one frame of the animation in the game.
     * @param d ('DrawSurface' d) - represents the surface of the game where the animation is shown on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        double millisecondsPerFrame = this.numOfSeconds * 1000 / COUNT_FROM;
        long startTime = System.currentTimeMillis(); // timing
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.GREEN);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(this.countFrom), 32);
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = (long) millisecondsPerFrame - usedTime;
        if (milliSecondLeftToSleep > 0) {
            new Sleeper().sleepFor(milliSecondLeftToSleep);
        }
        this.countFrom--;
        if (this.countFrom < 0) {
            this.stop = true;
        }
    }

    /**
     * A method returns if the animation should stop or not.
     * @return if the animation should stop or not.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
