package game.hitlisteners;

import game.objects.Block;
import game.objects.Ball;

/**
 * The HitListener interface.
 */
public interface HitListener {
    /**
     * Called when the beingHit object is hit.
     * @param beingHit the object that has been hit
     * @param hitter the object which hit the hitted object
     */
    void hitEvent(Block beingHit, Ball hitter);
}