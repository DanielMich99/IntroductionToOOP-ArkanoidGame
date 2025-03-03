package game;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.animations.Animation;

/**
 * @author Daniel Michaelshvili.
 * The 'AnimationRunner' class has been created for the option to run diffrent animations in the game with the help of
 * one object from type 'AnimationRunner'.
 */
public class AnimationRunner {
    private static final int FRAMES_PER_SECOND = 60;

    /*Represents the GUI the animation will be shown on.*/
    private GUI gui;

    /*Represents the number of frames of the animations that will be shown in one second.*/
    private int framesPerSecond;

    /*Represents a sleeper object that its task is to pause the run of the frame on the animation for certain amount
      of time.*/
    private Sleeper sleeper;

    /**
     * A constructor to the 'AnimationRunner'.
     * @param gui ('GUI' object) - the GUI the animation will be shown on.
     * @param sleeper ('Sleeper' object) - a sleeper object that its task is to pause the run of the frame on the
     * animation for certain amount of time.
     */
    public AnimationRunner(GUI gui, Sleeper sleeper) {
        this.gui = gui;
        this.sleeper = sleeper;
        this.framesPerSecond = FRAMES_PER_SECOND;
    }

    /**
     * A method that returns the GUI of the 'AnimationRunner' object.
     * @return the GUI of the 'AnimationRunner' object.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * A method that gets certain animation that supported for running in the game and make the adjsments for showing it
     * in the game in aspect of timing.
     * @param animation ('Animation' object) - the animation that 'this' animation runner should run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
