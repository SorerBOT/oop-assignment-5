package game;

import java.awt.Color;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import geometry.Rectangle;
import geometry.Point;

import game.hitlisteners.BallRemover;
import game.hitlisteners.BlockRemover;
import game.hitlisteners.HitListener;
import game.hitlisteners.ScoreTrackingListener;

import game.miscellaneous.Counter;
import game.miscellaneous.GameEnvironment;
import game.miscellaneous.ScoreIndicator;
import game.miscellaneous.Screen;
import game.miscellaneous.SpriteCollection;

import game.objects.Block;
import game.objects.Ball;
import game.objects.Collidable;
import game.objects.Paddle;
import game.objects.Sprite;

/**
 * The Game class.
 */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment gameEnvironment;
    private final GUI gui;
    private Paddle paddle;
    private final Counter blockCounter;
    private final Counter ballCounter;
    private final Counter scoreCounter;
    private final BlockRemover blockRemover;
    private final BallRemover ballRemover;
    private final ScoreTrackingListener scoreTrackingListener;

    /**
     * Empty constructor of the Game class.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.gameEnvironment = new GameEnvironment();
        this.gui = new GUI("Gaming", Screen.WIDTH, Screen.HEIGHT);
        this.paddle = null;

        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.scoreCounter = new Counter();

        this.blockRemover = new BlockRemover(this, this.blockCounter);
        this.ballRemover = new BallRemover(this, this.ballCounter);
        this.scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);
    }
    /**
     * Getter of the gameEnvironment field.
     * @return the GameEnvironment of the Game
     */
    public GameEnvironment getEnvironment() {
        return this.gameEnvironment;
    }
    /**
     * Getter of the gui field.
     * @return the GUI of the Game
     */
    public GUI getGui() {
        return this.gui;
    }
    /**
     * Getter of the paddle field.
     * @return the Paddle of the game
     */
    public Paddle getPaddle() {
        return this.paddle;
    }
    /**
     * Adds a Collidable to the GameEnvironment.
     * @param c the Collidable to be added
     */
    public void addCollidable(Collidable c) {
        this.gameEnvironment.addCollidable(c);
    }
    /**
     * Adds a Sprite to the SpriteCollection.
     * @param s the Sprite to be added.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * This method initializes the game by generating:
        * The Paddle
        * The Ball
        * The Blocks
     * then adds them to the Game.
     */
    public void initialize() {
        Rectangle backgroundRectangle = new Rectangle(new Point(0, 0), 800, 600);
        Block backgroundBlock = new Block(backgroundRectangle, new Color(48, 132, 20));
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter);

        backgroundBlock.addHitListener(this.ballRemover);
        backgroundBlock.addToGame(this);

        this.paddle = new Paddle(new Color(255, 200, 0, 255));
        this.paddle.addToGame(this);

        this.addAllBalls();
        this.addAllBlocks();
        this.addSprite(scoreIndicator);
    }
    /**
     * Adds the Balls the to the Game.
     */
    public void addAllBalls() {
        Ball firstBall = new Ball(new Point(750, 500), 6, Color.WHITE);
        Ball secondBall = new Ball(new Point(650, 400), 6, Color.WHITE);
        Ball thirdBall = new Ball(new Point(550, 350), 6, Color.WHITE);

        firstBall.setVelocity(3, 2);
        firstBall.addToGame(this);

        secondBall.setVelocity(2, 3);
        secondBall.addToGame(this);

        thirdBall.setVelocity(1, 3);
        thirdBall.addToGame(this);

        this.ballCounter.increase(3);
    }
    /**
     * Generates and adds all the blocks required for the Game.
     */
    public void addAllBlocks() {
        Rectangle topFrameRectangle = new Rectangle(new Point(0, 20), 800, 25);
        Rectangle leftFrameRectangle = new Rectangle(new Point(0, 0), 25, 600);
        Rectangle rightFrameRectangle = new Rectangle(new Point(775, 0), 25, 800);

        Block topFrame = new Block(topFrameRectangle, Color.GRAY);
        Block leftFrame = new Block(leftFrameRectangle, Color.GRAY);
        Block rightFrame = new Block(rightFrameRectangle, Color.GRAY);

        topFrame.addToGame(this);
        leftFrame.addToGame(this);
        rightFrame.addToGame(this);

        addRowBlocks(10, 150, Color.GRAY);
        addRowBlocks(9, 180, Color.RED);
        addRowBlocks(8, 210, Color.YELLOW);
        addRowBlocks(7, 240, Color.BLUE);
        addRowBlocks(6, 270, Color.WHITE);
    }
    /**
     * Generates and adds a row of Blocks to the Game.
     * @param amountOfBlocks the number of Blocks to be generated and added
     * @param rowYValue the y value of the row
     * @param color the Color of the blocks
     */
    public void addRowBlocks(int amountOfBlocks, double rowYValue, Color color) {
        for (int i = 0; i < amountOfBlocks; i++) {
            Rectangle rectangle = new Rectangle(new Point(725 - 50 * i, rowYValue), 50, 30);
            Block block = new Block(rectangle, color);
            block.addHitListener(new HitListener[]{this.scoreTrackingListener, this.blockRemover});
            block.addToGame(this);
            blockCounter.increase(1);
        }
    }
    /**
     * Removes the provided Collidable from the GameEnvironment.
     * @param c the Collidable to be removed
     */
    public void removeCollidable(Collidable c) {
        this.gameEnvironment.removeCollidable(c);
    }
    /**
     * Removes the provided Sprite from the SpriteCollection.
     * @param s the Sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * Runs the Game by starting the animation loop.
     */
    public void run() {
        final int framesPerSecond = 60;
        final int millisecondsPerFrame = 1000 / framesPerSecond;
        final Sleeper sleeper = new Sleeper();
        boolean isFinishedBlocks;
        boolean isFinishedBalls;

        while (true) {
            isFinishedBlocks = this.blockCounter.getValue() == 0;
            isFinishedBalls = this.ballCounter.getValue() == 0;
            if (isFinishedBlocks) {
                this.scoreCounter.increase(100);
            }
            if (isFinishedBlocks || isFinishedBalls) {
                gui.close();
                return;
            }

            long startTime = System.currentTimeMillis(); //Timing

            DrawSurface d = gui.getDrawSurface();
            d.setColor(new Color(1, 1, 122));
            d.fillRectangle(0, 0, Screen.WIDTH, Screen.HEIGHT);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            //Timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}