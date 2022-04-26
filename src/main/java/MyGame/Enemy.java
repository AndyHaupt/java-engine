package MyGame;

import java.awt.*;

/**
 * The Enemy class
 */
public class Enemy extends Sprite implements DrawAndUpdate {
    private boolean alive;
    private int dx;
    private int dy;
    private int SPEED = 2;

    /**
     * Gets direction on X axis.
     * @return direction on X axis
     */
    public int getDx() {
        return dx;
    }

    /**
     * Sets direction on X axis.
     * @param dx direction on X axis
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

    /**
     * Gets direction on Y axis.
     * @return direction on Y axis
     */
    public int getDy() {
        return dy;
    }


    /**
     * Sets direction on Y axis.
     * @param dy direction on Y axis
     */
    public void setDy(int dy) {
        this.dy = dy;
    }

    /**
     * Enemy constructor.
     * @param x coordinates of the enemy on the X axis
     * @param y coordinates of the enemy on the Y axis
     */
    public Enemy(int x, int y) {
        super(x, y);
        initEnemy();
    }

    /**
     * Sets enemy sprite and direction
     */
    private void initEnemy() {
        loadImage("enemy.png");
        getImageDimensions();
        alive = true;

        int select = (int) Math.round(Math.random());
        if(select == 0){
            dx = 0;
            dy = 1;
        }
        else{
            dx = 1;
            dy = 0;
        }

    }

    /**
     * Moves location of enemy
     */
    public void move() {
        setX(getX() + dx * SPEED);
        setY(getY() + dy * SPEED);
    }

    /**
     * Draws enemy
     * @param g component for drawing
     */
    @Override
    public void draw(Graphics2D g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }

    /**
     * Updates enemy
     * @param state State variable for changing state of object
     */
    @Override
    public void update(State state) {
        move();
    }
}
