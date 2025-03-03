
package game.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Daniel Michaelshvili.
 * The 'KeyPressStoppableAnimation' class has been created to stop animations that needed to be stop when the user press
 * certain key in the keyboard.
 */
public class KeyPressStoppableAnimation implements Animation {

    /*Represents the keyboard sensor that will let the object know if certain key is being pressed.*/
    private KeyboardSensor keyboard;

    /*Represents the key that should be pressed to stop the animation.*/
    private String key;

    /*Represents the animation that checked whether it should be stopped or not.*/
    private Animation animation;

    /*Represents the flag that alert whether the animation should stop or not.*/
    private boolean stop;

    /*Represents a flag that symbols that no checks have been done on the object before (with 'true' value), or not
    (with 'false' value).
     */
    private boolean isAlreadyPressed;

    /**
     * A constructor for a 'KeyPressStoppableAnimation' object.
     * @param keyboard ('KeyboardSensor' object) - the keyboard sensor that will let the object know if certain key
     * is being pressed.
     * @param key ('String' type) - the key that should be pressed to stop the animation.
     * @param animation ('Animation' object) - the animation that checked whether it should be stopped or not.
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String key, Animation animation) {
        this.keyboard = keyboard;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    /**
     * A method that run one frame of the animation in the game.
     * @param d ('DrawSurface' d) - represents the surface of the game where the animation is shown on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
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
