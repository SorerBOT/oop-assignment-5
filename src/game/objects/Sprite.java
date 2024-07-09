package game.objects;

import biuoop.DrawSurface;
/**
 * The Sprite interface.
 */
public interface Sprite {
    /**
     * Draws the Sprite on the GUI.
     * @param d the DrawSurface on which the Sprite is to be drawn.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the Sprite that time passed.
     */
    void timePassed();
}