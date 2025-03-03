
package game.sprites;

import biuoop.DrawSurface;
import game.Counter;
import game.animations.GameLevel;
import game.geometry.Rectangle;
import java.awt.Color;

/**
 * @author Daniel Michaelshvili
 * The 'ScoreIndicator' class has been created for the option to show at the top of the gameboard the score of
 * the game.
 */
public class ScoreIndicator implements Sprite {

    /*represents the counter of the score of the game.*/
    private Counter score;

    /*represents the shape of the sprite in the game and how it will be shown in the game board.*/
    private Rectangle rectangle;

    /*Represents the name of the current level in the game.*/
    private String levelName;

    /**
     * A constructor to the 'ScoreIndicator' object, that sets him a counter and appropriate rectangle so it will be
     * be shown where it needs to be shown.
     * @param score - ('Counter' object) - the counter of the score of the game.
     * @param rectangle - ('Rectangle' object) - the rectangle that the score is going to be shown on the game screen.
     * @param levelName - ('String' type) - the name of the current level in the game.
     */
    public ScoreIndicator(Counter score, Rectangle rectangle, String levelName) {
        this.score = score;
        this.rectangle = rectangle;
        this.levelName = levelName;
    }

    @Override
    /**
     * A method that draws the sprite on the screen.
     * @param d ('DrawSurface' object) - the surface that the sprite will be drawn on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        this.rectangle.getLeftLine().drawOn(d);
        this.rectangle.getRightLine().drawOn(d);
        this.rectangle.getTopLine().drawOn(d);
        this.rectangle.getBottomLine().drawOn(d);
        d.drawText((int) this.rectangle.getWidth() / 2, (int) this.rectangle.getHeight() / 2,
                "SCORE: " + Integer.toString(this.score.getValue()), 10);
        d.drawText((int) (this.rectangle.getWidth() - (this.rectangle.getWidth() / 5)),
                (int) this.rectangle.getHeight() / 2, "Level Name: " + this.levelName, 10);
    }

    /**
     * A method that indicates the sprite that it is time to do his work in the loop of the game run.
     */
    @Override
    public void timePassed() {

    }

    /**
     * A method that add the score indicator to the game list of its sprites.
     * @param g ('Game' object) - the game that the score indicator is added to.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
