/**
 * The Main class.
 */
public class Main {
    /**
     * Method responsible of creating the GUI and the general operation.
     * @param args
     */
    public static void main(String[] args) {
        Game game = new Game();
        if (game.initialize()) {
            game.run();
        }
    }
}