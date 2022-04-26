package MyGame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;


/**
 * The Gun class
 */
public class Gun extends Sprite implements DrawAndUpdate{

    int radians;
    int SPEED = 8;

    /**
     * Gun constructor
     * @param x coordinates of the arrow on the X axis
     * @param y coordinates of the arrow on the Y axis
     * @param radians rotation of sprite
     */
    public Gun(int x, int y, int radians) {
        super(x, y);
        this.radians = radians;
        initGun();
    }

    /**
     * sets arrow sprite
     */
    private void initGun() {
        loadImage("arrow.png");
        getImageDimensions();
    }

    /**
     * Moves location of arrow
     */
    private void move() {
        if(radians == 0){
            setX(getX() + SPEED);
        }
        if(radians == 90){
            setY(getY() + SPEED);
        }
        if(radians == 180){
            setX(getX() - SPEED);
        }
        if(radians == 270){
            setY(getY() - SPEED);
        }
    }

    /**
     * Draws arrow
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
     * Updates arrow
     * @param state State variable for changing state of object
     */
    @Override
    public void update(State state) {
        move();
    }
}
