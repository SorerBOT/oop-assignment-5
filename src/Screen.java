/**
 * This class is going to be used as a way of preserving
 * miscellaneous information of the screen
 */
public class Screen {
    private int width;
    private int height;
    /**
     * Constructor of the Screen class.
     * @param width the width of the screen
     * @param height the height of the screen
     */
    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
    }
    /**
     * Copy constructor of the Screen class.
     * @param screen the screen to be copied
     */
    public Screen(Screen screen) {
        this.width = screen.getWidth();
        this.height = screen.getHeight();
    }
    /**
     * Getter of the width field.
     * @return the width of the screen.
     */
    public int getWidth() {
        return this.width;
    }
    /**
     * Getter of the height field.
     * @return the height of the screen.
     */
    public int getHeight() {
        return this.height;
    }
}