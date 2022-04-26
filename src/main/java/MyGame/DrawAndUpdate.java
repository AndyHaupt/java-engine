package MyGame;

import java.awt.*;

/**
 * Interface for all objects visible on the game board.
 */
public interface DrawAndUpdate {

    /**
     * Draws object.
     * @param g component for drawing
     */
    public void draw(Graphics2D g);

    /**
     * Updates object.
     * @param state State variable for changing state of object
     */
    public void update(State state);
}
