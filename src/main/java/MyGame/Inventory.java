package MyGame;

import java.awt.*;

/**
 * This class is player's inventory
 */
public class Inventory implements DrawAndUpdate{

    private int B_WIDTH;
    private int B_HEIGHT;
    private boolean hasSword;
    private int arrows;
    private  int keys;

    /**
     * Inventory constructor
     */
    public Inventory() {
        B_WIDTH = 20 * 16;
        B_HEIGHT = 20 * 16;
    }

    /**
     * Writes status of player's inventory on the board
     * @param g component for drawing
     */
    @Override
    public void draw(Graphics2D g) {

        String msg = "Inventory: sword " + hasSword + ", arrows " + arrows + "x, keys " + keys + "x";
        Font font = new Font("Helvetica", Font.PLAIN, 12);
        FontMetrics fm = g.getFontMetrics();

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(msg, 3, B_HEIGHT + 14);
    }

    @Override
    public void update(State state) {

    }

    /**
     * Tells whether player has sword ot not.
     * @return true if player has sword, false otherwise
     */
    public boolean hasSword() {
        return hasSword;
    }

    /**
     * Sets whether player has sword.
     * @param hasSword is true if player has sword, false otherwise
     */
    public void setHasSword(boolean hasSword) {
        this.hasSword = hasSword;
    }

    /**
     * Tells how many arrows player has.
     * @return number of arrows
     */
    public int getArrows() {
        return arrows;
    }

    /**
     * Sets how many arrows player has.
     * @param arrows number of arrows
     */
    public void setArrows(int arrows) {
        this.arrows = arrows;
    }

    /**
     * Tells how many keys player has.
     * @return number of keys
     */
    public int getKeys() {
        return keys;
    }

    /**
     * Sets how many keys player has.
     * @param keys number of keys
     */
    public void setKeys(int keys) {
        this.keys = keys;
    }
}
