import java.awt.Color;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
/**
 * The Game class.
 */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment gameEnvironment;
    private final GUI gui;
    private Paddle paddle;
    /**
     * Empty constructor of the Game class.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.gameEnvironment = new GameEnvironment();
        this.gui = new GUI("Gaming", Screen.WIDTH, Screen.HEIGHT);
        this.paddle = null;
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
    public boolean initialize() {
        Ball firstBall = new Ball(new Point(500, 400), 8, Color.WHITE);
        Ball secondBall = new Ball(new Point(600, 400), 8, Color.WHITE);

        this.paddle = new Paddle(Color.YELLOW);
        this.paddle.addToGame(this);

        firstBall.setVelocity(2, 3);
        secondBall.setVelocity(2, 3);
        firstBall.addToGame(this);
        secondBall.addToGame(this);

        this.addAllBlocks();
        return this.test();
    }
    /**
     * Generates and adds all the blocks required for the Game.
     */
    public void addAllBlocks() {
        Rectangle topFrameRectangle = new Rectangle(new Point(0, -50), 800, 50);
        Rectangle bottomFrameRectangle = new Rectangle(new Point(0, 600), 800, 50);
        Rectangle leftFrameRectangle = new Rectangle(new Point(-50, 0), 50, 600);
        Rectangle rightFrameRectangle = new Rectangle(new Point(800, 0), 50, 600);

        Block topFrame = new Block(topFrameRectangle);
        Block bottomFrame = new Block(bottomFrameRectangle);
        Block leftFrame = new Block(leftFrameRectangle);
        Block rightFrame = new Block(rightFrameRectangle);

        topFrame.addToGame(this);
        bottomFrame.addToGame(this);
        leftFrame.addToGame(this);
        rightFrame.addToGame(this);

        addRowBlocks(12, 80, Color.GRAY);
        addRowBlocks(11, 110, Color.RED);
        addRowBlocks(10, 140, Color.YELLOW);
        addRowBlocks(9, 170, Color.BLUE);
        addRowBlocks(8, 200, Color.PINK);
        addRowBlocks(7, 230, Color.GREEN);
    }
    /**
     * Generates and adds a row of Blocks to the Game.
     * @param amountOfBlocks the number of Blocks to be generated and added
     * @param rowYValue the y value of the row
     * @param color the Color of the blocks
     */
    public void addRowBlocks(int amountOfBlocks, double rowYValue, Color color) {
        for (int i = 0; i < amountOfBlocks; i++) {
            Rectangle rectangle = new Rectangle(new Point(740 - 60 * i, rowYValue), 60, 30);
            Block block = new Block(rectangle, color);
            block.addToGame(this);
        }
    }
    /**
     * Runs the Game by starting the animation loop.
     */
    public void run() {
        final int framesPerSecond = 60;
        final int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (true) {
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
    public boolean test() {
        boolean addedPaddle = false;
        boolean passedAllTests = true;
        if (this.gui == null) {
            System.out.println("GUI is null.");
            passedAllTests = false;
        }
        if (this.gameEnvironment == null) {
            System.out.println("GameEnvironment is null.");
            passedAllTests = false;
        }
        if (this.paddle == null) {
            System.out.println("Paddle is null.");
            passedAllTests = false;
        }
        if (this.sprites == null) {
            System.out.println("Sprites is null.");
            passedAllTests = false;
        }
        if (this.gameEnvironment.getCollidables().size() != 62) {
            System.out.println("Amount of Collidables is incorrect: " + this.gameEnvironment.getCollidables().size());
            passedAllTests = false;
        }
        for (Collidable collidable : this.gameEnvironment.getCollidables()) {
            if (collidable == null) {
                System.out.println("Some collidable is null");
                passedAllTests = false;
                continue;
            }
            if (collidable instanceof Paddle) { // using instanceof, but only for the sake of TESTING
                addedPaddle = true;
            }
        }
        if (!addedPaddle) {
            System.out.println("Paddle was not added.");
            passedAllTests = false;
        }
        return passedAllTests;
    }
}