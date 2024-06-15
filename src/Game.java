import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * The Game class.
 */
public class Game {
    //private final SpriteCollection spriteCollection;
    private final GameEnvironment gameEnvironment;
    private final GUI gui;

    /**
     * Empty constructor.
     */
    public Game() {
        //sprites = new SpriteCollection();
        this.gameEnvironment = new GameEnvironment();
        this.gui = new GUI("Oop-assignment-3", Screen.WIDTH, Screen.HEIGHT);
        //this.spriteCollection = null;
    }

    /**
     * Getter of the gameEnvironment field.
     * @return The GameEnvironment field
     */
    public GameEnvironment getEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Getter of the GUI field.
     * @return The GUI field
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Adds a Collidable to the GameEnvironment.
     * @param c The Collidable we add
     */
    public void addCollidable(Collidable c) {
        this.gameEnvironment.addCollidable(c);
    }

    /**
     * Adds a Sprite to the SpriteCollection.
     * @param s the Sprite to be added.
     */
    public void addSprite(Sprite s) {
        //this.spriteCollection.addSprite(s);
    }

    /**
     * Initializes the Game by generating:
     * Blocks, a Ball and a Paddle proceeds to add them to the Game.
     */
    public void initialize() {
        Ball ball = new Ball(new Point(500, 100), 10, Color.BLACK, this.gameEnvironment);
        ball.setVelocity(2, 3);
        //this.addSprite(ball);
        //Paddle paddle = new Paddle();
        //this.paddle = paddle;
        Block topFrame = new Block(new Rectangle(new Point(0, -50), 800, 50), Color.BLACK);
        Block bottomFrame = new Block(new Rectangle(new Point(0, 600), 800, 50), Color.BLACK);
        Block leftFrame = new Block(new Rectangle(new Point(-50, 0), 50, 600), Color.BLACK);
        Block rightFrame = new Block(new Rectangle(new Point(800, 0), 50, 600), Color.BLACK);

        topFrame.addToGame(this);
        bottomFrame.addToGame(this);
        leftFrame.addToGame(this);
        rightFrame.addToGame(this);
    }

    /**
     * This method runs the game -- starts the animation loop.
     */
    public void run() {
        final int fps = 60;
        final int millisecondsPerFrame = 1000 / fps;
        Sleeper sleeper = new Sleeper();
        while (true) {
            long startTime = System.currentTimeMillis(); //Timing

            DrawSurface d = gui.getDrawSurface();
            //this.sprites.drawAllOn(d);
            gui.show(d);
            //this.sprites.notifyAllTimePassed();

            //Timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}