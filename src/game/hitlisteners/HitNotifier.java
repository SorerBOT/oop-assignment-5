package game.hitlisteners;

/**
 * The HitNotifier interface.
 */
public interface HitNotifier {
    /**
     * Add the provided HitListener.
     * @param hl the HitListener to be added
     */
    void addHitListener(HitListener hl);
    /**
     * Remove the provided HitListener.
     * @param hl the HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}