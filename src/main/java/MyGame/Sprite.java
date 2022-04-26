package MyGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Parent class of all game sprites.
 */
public class Sprite {

    private int x;
    private int y;
    private int width;
    private int height;
    private int last_x;
    private int last_y;

    private BufferedImage image;

    /**
     * Sprite constructor.
     * @param x coordinates of sprite on X axis
     * @param y coordinates of sprite on Y axis
     */
    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
    }

    /**
     * Gets dimensions of sprite.
     */
    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    /**
     * Sprite image loader.
     * @param imageName name of image
     */
    protected void loadImage(String imageName) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/" + imageName));
            //getClass().getResourcesAsStream("/" + imageName)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for image.
     * @return image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Getter for x coordinate.
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y coordinate.
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for x coordinate
     * @param x coordinate x
     */
    public void setX(int x) {
        this.last_x = this.x;
        this.x = x;
    }

    /**
     * Setter for x coordinate
     * @param y coordinate y
     */
    public void setY(int y) {
        this.last_y = this.y;
        this.y = y;
    }

    /**
     * Sets coordinate x and y as they were before the latest update
     */
    public void revert(){
        this.x = last_x;
        this.y = last_y;
    }

    /**
     * Tells bounds of sprite.
     * @return bounds rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}