package MyGame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

/**
 * The Sword class
 */
public class Sword extends Sprite implements DrawAndUpdate {

    int radians;

    /**
     * Sword constructor
     * @param x coordinates of the sword on the X axis
     * @param y coordinates of the sword on the Y axis
     * @param radians rotation of sprite
     */
    public Sword(int x, int y, int radians) {
        super(x, y);
        this.radians = radians;
        initSword();
    }

    /**
     * Loads Sword sprite.
     */
    private void initSword() {

        loadImage("sword.png");
        getImageDimensions();
    }

    /**
     * Draws Sword.
     * @param g component for drawing
     */
    @Override
    public void draw(Graphics2D g) {
        double rotationRequired = Math.toRadians (radians);
        double locationX = getBounds().width / 2;
        double locationY = getBounds().height / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        g.drawImage(op.filter(getImage(), null), getX(), getY(), null);
    }

    /**
     * Updates Sword
     * @param state State variable for changing state of object
     */
    @Override
    public void update(State state) {

    }
}