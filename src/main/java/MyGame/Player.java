package MyGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The player class
 */
public class Player extends Sprite implements DrawAndUpdate {

    private static final Logger LOGGER = Logger.getLogger(Player.class.getName());

    private int dx;
    private int dy;
    private Sword sword;
    private Gun arrow;
    private boolean swordUp;
    private boolean sword_set;
    private boolean arrUp;
    private boolean arr_set;
    int rot_x;
    int rot_y;


    private final int SPEED = 4;
    int radians;


    /**
     * Player constructor
     * @param x coordinates of player on the X axis
     * @param y coordinates of player on the Y axis
     */
    public Player(int x, int y) {
        super(x, y);
        initPlayer();
    }

    /**
     * Sets the player sprite and its rotation.
     */
    private void initPlayer() {
        loadImage("player.png");
        getImageDimensions();
        radians = 0;
        rot_x = 1;
        rot_y = 0;
        swordUp = false;
        sword_set = false;
        arrUp = false;
        arr_set = false;
    }

    /**
     * Moves player sprite
     */
    public void move() {

        setX(getX() + dx);
        setY(getY() + dy);
    }


    /**
     * Handles player input.
     * @param e key pressed
     */
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if(key == KeyEvent.VK_E){
            arrUp = true;
            swordUp = false;
            LOGGER.log(Level.INFO, "shooting...");
        }

        if (key == KeyEvent.VK_SPACE) {

            swordUp = true;
            arrUp = false;
        }
        if(!swordUp) {
            if (key == KeyEvent.VK_LEFT) {
                dx = -1 * SPEED;
                dy = 0;
                rot_x = -1;
                rot_y = 0;
            }

            if (key == KeyEvent.VK_RIGHT) {
                dx = 1 * SPEED;
                dy = 0;
                rot_x = 1;
                rot_y = 0;

            }

            if (key == KeyEvent.VK_UP) {
                dy = -1 * SPEED;
                dx = 0;
                rot_y = -1;
                rot_x = 0;
            }

            if (key == KeyEvent.VK_DOWN) {
                dy = 1 * SPEED;
                dx = 0;
                rot_y = 1;
                rot_x = 0;
            }
        }
    }

    /**
     * Sets new arrow
     */
    private void shoot() {
        int sw_x = getX();
        int sw_y = getY();
        if(rot_x < 0){
            sw_x -= 26;
        }
        if(rot_x > 0){
            sw_x += 5;
        }
        if(rot_y < 0){
            sw_y -= 16;
            sw_x -= 10;
        }
        if(rot_y > 0){
            sw_y += 16;
            sw_x -= 10;
        }
        arrow = new Gun(sw_x+10, sw_y, radians);
    }

    /**
     * Sets sword
     */
    public void attack(){
        int sw_x = getX();
        int sw_y = getY();
        if(rot_x < 0){
            sw_x -= 26;
        }
        if(rot_x > 0){
            sw_x += 5;
        }
        if(rot_y < 0){
            sw_y -= 16;
            sw_x -= 10;
        }
        if(rot_y > 0){
            sw_y += 16;
            sw_x -= 10;
        }
        sword = new Sword(sw_x+10, sw_y, radians);
    }

    /**
     * Sets back the values after player releases key
     * @param e key released
     */
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_E){
            arrUp = false;
            arr_set = false;
        }

        if (key == KeyEvent.VK_SPACE){
            swordUp = false;
            sword_set = false;
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    /**
     * Draws the sprite of player
     * @param g component for drawing
     */
    @Override
    public void draw(Graphics2D g) {
        LOGGER.log(Level.INFO, "vykresluje se...");
        if(dx<0){
            radians = 180;
        }
        if(dx>0){
            radians = 0;
        }
        if(dy<0){
            radians = 270;
        }
        if(dy>0){
            radians = 90;
        }
        double rotationRequired = Math.toRadians (radians);
        double locationX = getBounds().width / 2;
        double locationY = getBounds().height / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        g.drawImage(op.filter(getImage(), null), getX(), getY(), null);
    }

    /**
     * Updates state based on input
     * @param state State variable for changing state of objects
     */
    @Override
    public void update(State state) {

        if(swordUp && state.getInventory().hasSword()){
            if(sword == null) {
                attack();
            }
            LOGGER.log(Level.INFO, "sword is up");
            if(!sword_set){
                LOGGER.log(Level.INFO, "adding sword...");
                state.setSword(sword);
                sword_set = true;
            }
        }
        if(arrUp && state.getInventory().getArrows() > 0){
            if(!arr_set){
                shoot();
                state.getInventory().setArrows(state.getInventory().getArrows() - 1);
                state.addArrow(arrow);
                arr_set = true;
            }
        }
        if(!swordUp && !arrUp){
            if(state.getSword() != null){
                state.deleteSword(sword);
                sword = null;
            }
            move();
        }
    }
}
