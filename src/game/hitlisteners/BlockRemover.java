package game.hitlisteners;

import game.Game;
import game.miscellaneous.Counter;
import game.objects.Block;
import game.objects.Ball;

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

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.ballColorMatch(hitter)) {
            return;
        }
        hitter.setColor(beingHit.getColor());
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        remainingBlocks.decrease(1);
    }
}