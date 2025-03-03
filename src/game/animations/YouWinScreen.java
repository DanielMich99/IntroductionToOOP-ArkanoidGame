
package game.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Daniel Michaelshvili.
 * The 'YouWinScreen' class has been created to make an animation for the case the player wins the game.
 */
public class YouWinScreen implements Animation {

    /*Represents the flag that alert whether the animation should stop or not.*/
    private boolean stop;

    /*Represents the score of the user in the game.*/
    private int score;

    /*Represents the object that should take care of the case that the user wants to exit from the game over screen.*/
    private KeyPressStoppableAnimation keyPressStoppableAnimation;

    /**
     * A constructor for a 'YouWinScreen' object. sets his members that include the flag that alert whether the
     * animation should stop or not, the score of the user in the game, the object that should take care of the case
     * that the user wants to exit from the "you win" screen.
     * @param k ('KeyboardSensor' object) - the keyboard sensor the 'KeyPressStoppableAnimation' will get to be built.
     * @param score ('int' type) - the score number of the user in the game.
     */
    public YouWinScreen(KeyboardSensor k, int score) {
        this.stop = false;
        this.score = score;
        this.keyPressStoppableAnimation = new KeyPressStoppableAnimation(k, KeyboardSensor.SPACE_KEY, this);
    }

    /**
     * A method that run one frame of the animation in the game.
     * @param d ('DrawSurface' d) - represents the surface of the game where the animation is shown on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 5, d.getHeight() / 2, "You Win! Your score is: "
                + Integer.toString(this.score), 32);
        this.keyPressStoppableAnimation.doOneFrame(d);
        this.stop = this.keyPressStoppableAnimation.shouldStop();
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
