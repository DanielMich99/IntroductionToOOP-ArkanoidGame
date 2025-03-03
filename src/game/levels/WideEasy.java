package game.levels;

import game.collisionDetection.Velocity;
import game.geometry.Point;
import game.geometry.Rectangle;
import game.sprites.Background;
import game.sprites.Ball;
import game.sprites.Block;
import game.sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Michaelshvili.
 * The 'WideEasy' class has been created to make a certain level in the game named 'Wide Easy'.
 */
public class WideEasy implements LevelInformation {

    private static final int NUMBER_OF_BALLS = 10;
    private static final int BALL_RADIUS = 5;
    private static final double LEFT_BALL_X_POSITION = 150;
    private static final double LEFT_BALL_Y_POSITION = 400;
    private static final double RIGHT_BALL_X_POSITION = 650;
    private static final double RIGHT_BALL_Y_POSITION = 400;
    private static final double SPACE_BETWEEN_BALLS = 20;
    private static final int NUMBER_OF_BLOCKS = 15;
    private static final double Y_COMPONENT_VELOCITY = -6;
    private static final double X_COMPONENT_VELOCITY = 4;
    private static final int PADDLE_SPEED = 6;
    private static final int PADDLE_WIDTH = 600;
    private static final int BOARDER_BLOCK_WIDTH = 10;
    private static final int BLOCK_WIDTH = 52;
    private static final int BLOCK_HEIGHT = 30;
    private static final int BLOCK_TOP_LIMIT = 250;
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int SCORE_INDICATOR_HEIGHT = 20;

    /*Represents the list of the balls in the level.*/
    private ArrayList<Ball> balls;

    /*Represents the list of the velocities of the balls in the level.*/
    private ArrayList<Velocity> velocities;

    /**
     * A constructor for the levelInformation object.
     */
    public WideEasy() {
        this.balls = new ArrayList<>();
        this.velocities = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_BALLS / 2; i++) {
            this.balls.add(new Ball(new Point(LEFT_BALL_X_POSITION + SPACE_BETWEEN_BALLS * i,
                    LEFT_BALL_Y_POSITION - SPACE_BETWEEN_BALLS * i), BALL_RADIUS, Color.BLACK));

            velocities.add(new Velocity(-X_COMPONENT_VELOCITY, Y_COMPONENT_VELOCITY));
        }
        for (int i = 0; i < NUMBER_OF_BALLS / 2; i++) {
            this.balls.add(new Ball(new Point(RIGHT_BALL_X_POSITION - SPACE_BETWEEN_BALLS * i,
                    RIGHT_BALL_Y_POSITION - SPACE_BETWEEN_BALLS * i), BALL_RADIUS, Color.BLACK));
            velocities.add(new Velocity(X_COMPONENT_VELOCITY, Y_COMPONENT_VELOCITY));
        }
    }

    @Override
    /**
     * A method that returns the number of ball in the level.
     * @return the number of ball in the level.
     */
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    /**
     * A method that returns a list of all the balls in the level.
     * @return a list of all the balls in the level.
     */
    public List<Ball> balls() {
        return this.balls;
    }

    @Override
    /**
     * A method that returns a list of all the initial velocities of the balls in the level.
     * @return a list of all the initial velocities of the balls in the level.
     */
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    @Override
    /**
     * A method that sets the initial velocities of all the balls in the level.
     */
    public void setVelocities() {
        for (int i = 0; i < NUMBER_OF_BALLS / 2; i++) {
            this.balls.get(i).setVelocity(this.velocities.get(i));
        }
        for (int i = 0; i < NUMBER_OF_BALLS / 2; i++) {
            this.balls.get(i + NUMBER_OF_BALLS / 2).setVelocity(this.velocities.get(i + NUMBER_OF_BALLS / 2));
        }
    }

    @Override
    /**
     * A method that returns how many pixels moves the paddle in the level per move.
     * @return how many pixels moves the paddle in the level per move.
     */
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    /**
     * A method that returns the width of the paddle in the level.
     * @return the width of the paddle in the level.
     */
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    /**
     * A method that returns the level name.
     * @return the level name.
     */
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    /**
     * A method that returns a sprite with the background of the level.
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return new Background(Color.MAGENTA);
    }

    @Override
    /**
     * A method that returns a list of all the blocks in the level.
     * @return a list of all the blocks in the level.
     */
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        Color[] colorArr = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW,
                Color.GREEN, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK,
                Color.CYAN, Color.CYAN};
        for (int k = 0; k < NUMBER_OF_BLOCKS; k++) {
            blocks.add(new Block(new Rectangle(new Point(BOARDER_BLOCK_WIDTH + k * BLOCK_WIDTH, BLOCK_TOP_LIMIT),
                    BLOCK_WIDTH, BLOCK_HEIGHT), colorArr[k]));
        }
        blocks.add(new Block(new Rectangle(new Point(0, 0), BOARDER_BLOCK_WIDTH, GUI_HEIGHT), Color.GRAY));
        blocks.add(new Block(new Rectangle(new Point(GUI_WIDTH - BOARDER_BLOCK_WIDTH, 0),
                BOARDER_BLOCK_WIDTH, GUI_HEIGHT), Color.GRAY));
        blocks.add(new Block(new Rectangle(new Point(0, SCORE_INDICATOR_HEIGHT),
                GUI_WIDTH, BOARDER_BLOCK_WIDTH), Color.GRAY));
        blocks.add(new Block(new Rectangle(new Point(0, GUI_HEIGHT),
                GUI_WIDTH, BOARDER_BLOCK_WIDTH), Color.GRAY));
        return blocks;
    }

    @Override
    /**
     * A method that returns the number of blocks that should be removed in the level.
     * @return the number of blocks that should be removed in the level.
     */
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }
}
