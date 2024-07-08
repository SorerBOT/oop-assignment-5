/**
 * The BlockRemover class.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor of the BlockRemover class.
     * @param game the Game instance
     * @param remainingBlocks the Counter measuring the remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    /**
     * Removes the Block from the Game and then decreases the Counter.
     * @param beingHit the Block which was hit
     * @param hitter the Ball responsible for the hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        remainingBlocks.decrease(1);
    }
}