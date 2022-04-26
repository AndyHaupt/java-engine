package MyGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Board handles that the game runs
 */
public class Board extends JPanel implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(Board.class.getName());

    private Timer timer;
    private State state;
    private CollisionHandler collision;
    private boolean inGame;
    private final int DELAY = 15;
    private Loader loader;
    private LoadSave gameLoader;
    private int B_WIDTH;
    private int B_HEIGHT;
    private int currentLevel;

    public int getB_WIDTH() {
        return B_WIDTH;
    }

    /**
     * Sets the size of B_WIDTH
     * @param b_WIDTH
     */
    public void setB_WIDTH(int b_WIDTH) {
        B_WIDTH = b_WIDTH;
    }

    public int getB_HEIGHT() {
        return B_HEIGHT;
    }

    /**
     * Sets the size of B_HEIGHT
     * @param b_HEIGHT
     */
    public void setB_HEIGHT(int b_HEIGHT) {
        B_HEIGHT = b_HEIGHT;
    }

    /**
     * Board constructor
     */
    public Board() {
        gameLoader = new LoadSave();
        currentLevel = gameLoader.getLevel();
        String levelFileName = "level" + gameLoader.getLevel() + ".txt";
        initBoard(levelFileName, gameLoader.getInventory());
    }

    /**
     * Loads level. Creates game window. Starts game thread.
     * @param levelFileName name of level .txt file
     * @param inv inventory of the player at the start of new level
     */
    private void initBoard(String levelFileName, Inventory inv) {
        try {
            loader = new Loader(levelFileName);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.INFO, "Game won");
            inGame = false;
            return;
        }

        setB_HEIGHT(loader.getB_HEIGHT() + 20);
        setB_WIDTH(loader.getB_WIDTH());

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        inGame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        collision = new CollisionHandler();

        state = loader.getState();
        state.setInventory(inv);

        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(DELAY, this);
        timer.start();
    }

    /**
     * Creates final screen indicating that the player have won.
     * @param g component for writing text on the screen
     */
    private void win(Graphics g) {
        String msg = "You won! Thanks for playing!";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
    }

    /**
     * Restarts level upon players death.
     */
    private void gameOver(){
        gameLoader = new LoadSave();
        currentLevel = gameLoader.getLevel();
        String levelFileName = "level" + gameLoader.getLevel() + ".txt";
        initBoard(levelFileName, gameLoader.getInventory());
    }

    /**
     * Checks the status of game and draws accordingly
     * @param g component for drawing
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame) {

            if(state.isGameOver()){
                gameOver();
                return;
            }

            drawObjects(g);

        }

        else {

            win(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Draws all drawable objects on board
     * @param g component for drawing
     */
    private void drawObjects(Graphics g) {

        for (DrawAndUpdate object : state.getObjects()) {
            object.draw((Graphics2D) g);
        }
    }

    /**
     * Action listener. Updates game. Checks for collisions.
     * @param e component for drawing
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        for (DrawAndUpdate object : state.getObjects()) {
            object.update(state);
        }

        collision.checkCollisions(state);
        repaint();

        if(state.getEnemies().size() == 0 || state.isGameWon()){
            currentLevel++;

            Saver saver = new Saver(currentLevel, state.getInventory());
            saver.start();

            String nextLevel = "level" + currentLevel + ".txt";
            initBoard(nextLevel, state.getInventory());
        }
    }

    /**
     * Handles the thread
     */
    private void inGame() {

        if (!inGame) {
            timer.stop();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            state.getPlayer().keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            state.getPlayer().keyPressed(e);
        }
    }
}