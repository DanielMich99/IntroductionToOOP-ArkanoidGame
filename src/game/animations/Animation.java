
package game.animations;

import biuoop.DrawSurface;

/**
 * @author Daniel Michaelshvili
 * The 'Animation' inteface has been created so there will be an interface for all the Animations that should be
 * represented during the run of the game.
 */
public interface Animation {

    /**
     * A method that run one frame of the animation in the game.
     * @param d ('DrawSurface' d) - represents the surface of the game where the animation is shown on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * A method returns if the animation should stop or not.
     * @return if the animation should stop or not.
     */
    boolean shouldStop();
}
