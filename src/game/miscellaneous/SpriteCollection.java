package game.miscellaneous;

import java.util.ArrayList;

import biuoop.DrawSurface;

import game.objects.Sprite;

/**
 * The SpriteCollection class.
 */
public class SpriteCollection {
    private final ArrayList<Sprite> sprites;
    /**
     * Empty constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * Adds Sprite to sprites array.
     * @param s The sprite to be added
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s); //Encapsulation?
    }
    /**
     * Calls timePassed on all the Sprites in sprites array.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> spritesClone = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : spritesClone) {
            s.timePassed();
        }
    }
    /**
     * Calls drawOn on all the Sprites in sprites array.
     * @param d The DrawSurface we draw the Sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
    /**
     * Removes the provided Sprite from the sprites list.
     * @param s the Sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
}