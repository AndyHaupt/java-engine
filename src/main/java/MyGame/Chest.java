package MyGame;

import java.awt.*;

/**
 * The Chest class
 */
public class Chest extends Sprite implements DrawAndUpdate {

    /**
     * Chest constructor
     * @param x coordinates of the chest on the X axis
     * @param y coordinates of the chest on the Y axis
     */
    public Chest(int x, int y) {
        super(x, y);
        initChest();
    }

    /**
     * Sets chest sprite
     */
    private void initChest() {
        loadImage("chest.png");
        getImageDimensions();

    }

    /**
     * Draws chest
     * @param g component for drawing
     */
    @Override
    public void draw(Graphics2D g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }


    @Override
    public void update(State state) {

    }
}
