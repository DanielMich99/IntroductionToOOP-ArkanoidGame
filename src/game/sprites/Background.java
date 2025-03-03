
package game.sprites;

import biuoop.DrawSurface;
import game.animations.GameLevel;
import game.geometry.Point;
import game.geometry.Rectangle;
import java.awt.Color;

/**
 * @author Daniel Michaelshvili.
 * The 'Background' class has been created for the option to have a background to the game in each level.
 */
public class Background implements Sprite {

    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int BOARDER_BLOCK_WIDTH = 10;
    private static final int SCORE_INDICATOR_HEIGHT = 20;

    /*Represents the color of the background.*/
    private Color color;

    /*Represents the shape of the background in the game.*/
    private Rectangle rectangle;

    /**
     * A constructor for a 'Background' object.
     * @param color ('Color' object) - the color of the background.
     */
    public Background(Color color) {
        this.color = color;
        this.rectangle = new Rectangle(new Point(BOARDER_BLOCK_WIDTH, BOARDER_BLOCK_WIDTH + SCORE_INDICATOR_HEIGHT),
                GUI_WIDTH - 2 * BOARDER_BLOCK_WIDTH, GUI_HEIGHT - BOARDER_BLOCK_WIDTH - SCORE_INDICATOR_HEIGHT);
    }

    /**
     * A method that draws the sprite on the screen.
     * @param d ('DrawSurface' object) - the surface that the sprite will be drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        this.rectangle.getLeftLine().drawOn(d);
        this.rectangle.getRightLine().drawOn(d);
        this.rectangle.getTopLine().drawOn(d);
        this.rectangle.getBottomLine().drawOn(d);
    }

    /**
     * A method that indicates the sprite that it is time to do his work in the loop of the game run.
     */
    @Override
    public void timePassed() {

    }

    /**
     * A method that add the ball to the game list of its sprites.
     * @param g ('Game' object) - the game that the ball is added to.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
