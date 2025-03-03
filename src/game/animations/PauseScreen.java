//207795030 Daniel Michaelshvili

package game.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Daniel Michaelshvili.
 * The 'PauseScreen' class has been created to make an animation for the case the player wants to pause the game.
 */
public class PauseScreen implements Animation {

    /*Represents the flag that alert whether the animation should stop or not.*/
    private boolean stop;

    /*Represents the object that should take care of the case that the user wants to exit from the pause screen.*/
    private KeyPressStoppableAnimation keyPressStoppableAnimation;

    /**
     * A constructor for a 'PauseScreen' object. sets his members that include the flag that alert whether
     * the animation should stop or not, the object that should take care of the case that the user wants to exit from
     * the pause screen.
     * @param k ('KeyboardSensor' object) - the keyboard sensor the 'KeyPressStoppableAnimation' will get to be built.
     */
    public PauseScreen(KeyboardSensor k) {
        this.stop = false;
        this.keyPressStoppableAnimation = new KeyPressStoppableAnimation(k, KeyboardSensor.SPACE_KEY, this);
    }

    /**
     * A method that run one frame of the animation in the game.
     * @param d ('DrawSurface' d) - represents the surface of the game where the animation is shown on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 5, d.getHeight() / 2, "paused -- press space to continue", 32);
        this.keyPressStoppableAnimation.doOneFrame(d);
        //checks if the animation should be stopped.

        this.stop = this.keyPressStoppableAnimation.shouldStop();
    }

    /**
     * A method returns if the animation should stop or not.
     * @return if the animation should stop or not.
     */
    public boolean shouldStop() {
        return this.stop; }
}
