
package game.animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import game.AnimationRunner;
import game.Counter;
import game.SpriteCollection;
import game.geometry.Point;
import game.geometry.Rectangle;
import game.hitListeners.BallRemover;
import game.hitListeners.BlockRemover;
import game.hitListeners.ScoreTrackingListener;
import game.sprites.Sprite;
import game.sprites.Ball;
import game.sprites.Block;
import game.sprites.Collidable;
import game.sprites.Paddle;
import game.sprites.GameEnvironment;
import game.sprites.ScoreIndicator;
import game.levels.LevelInformation;

/**
 * @author Daniel Michaelshvili
 * The 'Game' class has been created to organize all the setups to running the game in only two method calls -
 * initialize() and run().
 */
public class GameLevel implements Animation {

    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int PADDLE_UPPER_LEFT_Y = 570;
    private static final int PADDLE_HEIGHT = 20;
    private static final int BOARDER_BLOCK_WIDTH = 10;
    private static final int SCORE_INDICATOR_HEIGHT = 20;

    /*Represents the sprite collection of the game.*/
    private SpriteCollection sprites;

    /*Represents the environment of the game.*/
    private GameEnvironment environment;

    /*A counter for the number of blocks is in current time during the game.*/
    private Counter blocksCounter;

    /*A counter for the number of balls is in current time during the game.*/
    private Counter ballsCounter;

    /*A counter for the score in current time during the game.*/
    private Counter score;

    /*Represents the animation runner that will run all the animations throughout the game.*/
    private AnimationRunner runner;

    /*Represents a flag that alerts whether the animation should continue to run or not.*/
    private boolean running;

    /*Represents the keyboard sensor that will react to every press on the keyboard during the run of the game.*/
    private KeyboardSensor keyboard;

    /*Represents the level the game will run.*/
    private LevelInformation levelInfo;

    /**
     * A constructor to a 'GameLevel' object that set to him all his members.
     * @param runner ('AnimationRunner' object) - the animation runner that will run the animations throughout the game.
     * @param ks ('KeyboardSensor' object) - the keyboard sensor that will react to every press on the keyboard during
     * the run of the game.
     * @param levelInformation ('LevelInformation' object) - the level the game will run.
     * @param score ('Counter' object) - A counter for the score in current time during the game.
     */
    public GameLevel(AnimationRunner runner, KeyboardSensor ks, LevelInformation levelInformation, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.runner = runner;
        this.running = true;
        this.keyboard = ks;
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.score = score;
        this.levelInfo = levelInformation;
    }

    /**
     * A method that run one frame of the animation in the game.
     * @param d ('DrawSurface' d) - represents the surface of the game where the animation is shown on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        //draws all the sprites of the game.

        this.sprites.notifyAllTimePassed();
        //notify all the sprites to their part in the frame loop.

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        //checks if the user want to pause the game.

        if (this.blocksCounter.getValue() == 0) {
            this.score.increase(100);
            //bonus for passing a level.

            this.running = false;
        }
        //checks if no more blocks are in the game, if this is the case, the current level run should be over.

        if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
        //checks if no more balls are in the game, if this is the case, the game should be over.
    }

    /**
     * A method returns if the animation should stop or not.
     * @return if the animation should stop or not.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * A method that adds a certain 'Collidable' interface object to the game environment list of collidables.
     * @param c ('Collidable' object) - the object that is added to the list.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * A method that adds a certain 'Sprite' interface object to the game list of sprites.
     * @param s ('Sprite' object) - the object that is added to the list.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * A method that removes 'c' Collidable from the game environment.
     * @param c ('Collidable' object) - the collidable that being removed from the game environment.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * A method that removes 's' Sprite from the sprites list of the game.
     * @param s ('Sprite' object) - the Sprite that being removed from the sprites list of the game.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * A method that returns the number of balls left in the game.
     * @return the number of balls left in the game.
     */
    public int remindingBalls() {
        return this.ballsCounter.getValue();
    }

    /**
     * A method that Initialize a new game. create the Blocks, the Balls and the Paddle, and add them to the game.
     */
    public void initialize() {
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        //creates the listeners for a hit event during the run of the game.

        this.levelInfo.getBackground().addToGame(this);
        //adds the background of the level to the game screen.

        Paddle paddle = new Paddle(this.runner.getGui().getKeyboardSensor(),
                new Rectangle(new Point(GUI_WIDTH / 2.0 - this.levelInfo.paddleWidth() / 2.0, PADDLE_UPPER_LEFT_Y),
                        this.levelInfo.paddleWidth(), PADDLE_HEIGHT),
                this.levelInfo.balls(), this.levelInfo.paddleSpeed());
        paddle.addToGame(this);
        //adds the paddle to the game.

        this.ballsCounter.increase(this.levelInfo.numberOfBalls());
        this.levelInfo.setVelocities();
        for (Ball ball : this.levelInfo.balls()) {
            ball.addHitListener(ballRemover);
            ball.setGameEnvironment(environment);
            ball.setLimits(0, GUI_WIDTH, 0, GUI_HEIGHT + BOARDER_BLOCK_WIDTH);
            ball.fixFirstFrame();
            ball.setTrajectoryAttributes();
            ball.addToGame(this);
        }
        //adds the balls to the game.

        ArrayList<Block> blocks = new ArrayList<>(this.levelInfo.blocks());
        for (int i = 0; i < this.levelInfo.numberOfBlocksToRemove(); i++) {
            blocks.get(i).addToGame(this);
            blocks.get(i).addHitListener(blockRemover);
            blocks.get(i).addHitListener(scoreTrackingListener);
            this.blocksCounter.increase(1);
        }
        for (int i = this.levelInfo.numberOfBlocksToRemove(); i < blocks.size(); i++) {
            blocks.get(i).addToGame(this);
        }
        blocks.get(blocks.size() - 1).addHitListener(ballRemover);
        //adds the blocks to the game.

        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score,
                new Rectangle(new Point(0, 0), GUI_WIDTH, SCORE_INDICATOR_HEIGHT), this.levelInfo.levelName());
        scoreIndicator.addToGame(this);
        //adds the score and level name indicator to the game.

        /*BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        Ball ball1 = new Ball((new Random().nextInt(GUI_WIDTH - 2 * BOARDER_BLOCK_WIDTH) + BOARDER_BLOCK_WIDTH),
                (new Random().nextInt(PADDLE_UPPER_LEFT_Y - (FIRST_COLUMN_Y_LIMIT + BLOCK_HEIGHT * BLOCK_ROWS))
                        + (FIRST_COLUMN_Y_LIMIT + BLOCK_HEIGHT * BLOCK_ROWS)), BALL_RADIUS, Color.BLACK);
        Ball ball2 = new Ball((new Random().nextInt(GUI_WIDTH - 2 * BOARDER_BLOCK_WIDTH) + BOARDER_BLOCK_WIDTH),
                (new Random().nextInt(PADDLE_UPPER_LEFT_Y - (FIRST_COLUMN_Y_LIMIT + BLOCK_HEIGHT * BLOCK_ROWS))
                        + (FIRST_COLUMN_Y_LIMIT + BLOCK_HEIGHT * BLOCK_ROWS)), BALL_RADIUS, Color.BLACK);
        Ball ball3 = new Ball((new Random().nextInt(GUI_WIDTH - 2 * BOARDER_BLOCK_WIDTH) + BOARDER_BLOCK_WIDTH),
                (new Random().nextInt(PADDLE_UPPER_LEFT_Y - (FIRST_COLUMN_Y_LIMIT + BLOCK_HEIGHT * BLOCK_ROWS))
                        + (FIRST_COLUMN_Y_LIMIT + BLOCK_HEIGHT * BLOCK_ROWS)), BALL_RADIUS, Color.BLACK);
        java.util.List<Ball> balls = new ArrayList<>();
        balls.add(ball1);
        balls.add(ball2);
        balls.add(ball3);
        this.ballsCounter.increase(NUMBER_OF_BALLS);
        ball1.addHitListener(ballRemover);
        ball2.addHitListener(ballRemover);
        ball3.addHitListener(ballRemover);
        ball1.setGameEnvironment(environment);
        ball2.setGameEnvironment(environment);
        ball3.setGameEnvironment(environment);
        ball1.setLimits(0, GUI_WIDTH, 0, GUI_HEIGHT + BOARDER_BLOCK_WIDTH);
        ball2.setLimits(0, GUI_WIDTH, 0, GUI_HEIGHT + BOARDER_BLOCK_WIDTH);
        ball3.setLimits(0, GUI_WIDTH, 0, GUI_HEIGHT + BOARDER_BLOCK_WIDTH);
        ball1.fixFirstFrame();
        ball2.fixFirstFrame();
        ball3.fixFirstFrame();
        //makes sure the position of the balls in their first appearance makes sense.

        ball1.setVelocity(BALL_DX, BALL_DY);
        ball2.setVelocity(BALL_DX, BALL_DY);
        ball3.setVelocity(BALL_DX, BALL_DY);
        ball1.setTrajectoryAttributes();
        ball2.setTrajectoryAttributes();
        ball3.setTrajectoryAttributes();
        Paddle paddle = new Paddle(this.runner.getGui().getKeyboardSensor(),
                new Rectangle(new Point(PADDLE_UPPER_LEFT_X, PADDLE_UPPER_LEFT_Y), PADDLE_WIDTH, PADDLE_HEIGHT), balls);
        paddle.addToGame(this);
        ball1.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
        Color[] colorArr = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};
        for (int k = 0; k < BLOCK_ROWS; k++) {
                for (int i = 0; i < BLOCK_COLUMNS - k; i++) {
                    Block block = new Block(new Rectangle(new Point(FIRST_COLUMN_X_LIMIT - i * BLOCK_WIDTH,
                            FIRST_COLUMN_Y_LIMIT + k * BLOCK_HEIGHT), BLOCK_WIDTH, BLOCK_HEIGHT), colorArr[k]);
                    block.addToGame(this);
                    block.addHitListener(blockRemover);
                    block.addHitListener(scoreTrackingListener);
                    this.blocksCounter.increase(1);

                }
        }
        Block leftBlock = new Block(new Rectangle(new Point(0, 0), BOARDER_BLOCK_WIDTH, GUI_HEIGHT), Color.GRAY);
        Block rightBlock = new Block(new Rectangle(new Point(GUI_WIDTH - BOARDER_BLOCK_WIDTH, 0),
                BOARDER_BLOCK_WIDTH, GUI_HEIGHT), Color.GRAY);
        Block topBlock = new Block(new Rectangle(new Point(0, SCORE_INDICATOR_HEIGHT),
                GUI_WIDTH, BOARDER_BLOCK_WIDTH), Color.GRAY);
        Block bottomBlock = new Block(new Rectangle(new Point(0, GUI_HEIGHT),
                GUI_WIDTH, BOARDER_BLOCK_WIDTH), Color.GRAY);
        leftBlock.addToGame(this);
        rightBlock.addToGame(this);
        topBlock.addToGame(this);
        bottomBlock.addToGame(this);
        bottomBlock.addHitListener(ballRemover);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score,
                new Rectangle(new Point(0, 0), GUI_WIDTH, SCORE_INDICATOR_HEIGHT));
        scoreIndicator.addToGame(this);*/
    }

    /**
     * A method that runs the game and start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(this.sprites));
        this.running = true;
        this.runner.run(this);
    }
}
