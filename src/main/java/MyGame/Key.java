package MyGame;

import java.awt.*;

/**
 * The Key class
 */
public class Key extends Sprite implements DrawAndUpdate {

    /**
     * Key constructor
     * @param x coordinates of the key on the X axis
     * @param y coordinates of the key on the Y axis
     */
    public Key(int x, int y) {
        super(x, y);
        initKey();
    }

    /**
     * Sets key sprite.
     */
    private void initKey() {
        loadImage("key.png");
        getImageDimensions();
    }

    /**
     * Draws key.
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
