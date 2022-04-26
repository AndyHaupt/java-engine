package MyGame;

import MyGame.*;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class CollisionHandlerTest {

    private static final Logger LOGGER = Logger.getLogger(CollisionHandler.class.getName());

    CollisionHandler collisionHandler;
    Loader loader;
    State state;

    @Before
    public void setUp() throws Exception {
        collisionHandler = new CollisionHandler();
        loader = new Loader("testlvl.txt");
        state = loader.getState();
    }

    @org.junit.Test
    public void playerToWallCollision() {
        Player player = state.getPlayer();
        LOGGER.log(Level.INFO, String.valueOf(player.getBounds()));
        player.setX(15);
        player.setY(16);
        LOGGER.log(Level.INFO, String.valueOf(player.getBounds()));
        collisionHandler.checkCollisions(state);
        LOGGER.log(Level.INFO, String.valueOf(player.getBounds()));
        assertEquals(16, player.getX());
    }

    @org.junit.Test
    public void enemyStruckWithArrow() {
        LOGGER.log(Level.INFO, String.valueOf(state.getEnemies().size()));
        Gun arrow = new Gun(32, 16, 0);
        state.addArrow(arrow);
        collisionHandler.checkCollisions(state);
        LOGGER.log(Level.INFO, String.valueOf(state.getEnemies().size()));
        assertEquals(1, state.getEnemies().size());
        assertEquals(0, state.getArrows().size());
    }

    @org.junit.Test
    public void enemyStruckWithSword() {
        LOGGER.log(Level.INFO, String.valueOf(state.getEnemies().size()));
        Sword sword = new Sword(48, 16, 0);
        state.setSword(sword);
        collisionHandler.checkCollisions(state);
        LOGGER.log(Level.INFO, String.valueOf(state.getEnemies().size()));
        assertEquals(1, state.getEnemies().size());
    }

    @org.junit.Test
    public void enemyToEnemyCollision() {
        List<Enemy> enemies = state.getEnemies();
        LOGGER.log(Level.INFO, String.valueOf(enemies.get(0).getBounds()));
        enemies.get(0).setX(33);
        enemies.get(0).setY(16);
        LOGGER.log(Level.INFO, String.valueOf(enemies.get(0).getBounds()));
        collisionHandler.checkCollisions(state);
        LOGGER.log(Level.INFO, String.valueOf(enemies.get(0).getBounds()));
        assertEquals(32, enemies.get(0).getX());
    }

    @org.junit.Test
    public void enemyToWallCollision() {
        List<Enemy> enemies = state.getEnemies();
        LOGGER.log(Level.INFO, String.valueOf(enemies.get(1).getBounds()));
        enemies.get(1).setX(48);
        enemies.get(1).setY(15);
        LOGGER.log(Level.INFO, String.valueOf(enemies.get(1).getBounds()));
        collisionHandler.checkCollisions(state);
        LOGGER.log(Level.INFO, String.valueOf(enemies.get(1).getBounds()));
        assertEquals(16, enemies.get(1).getY());
    }

    @org.junit.Test
    public void keyPickedUp() {
        List<Key> keys = state.getKeys();
        LOGGER.log(Level.INFO, String.valueOf(keys.get(0).getBounds()));

        Player player = state.getPlayer();
        LOGGER.log(Level.INFO, String.valueOf(player.getBounds()));
        player.setX(16);
        player.setY(32);
        collisionHandler.checkCollisions(state);
        assertEquals(0, state.getKeys().size());
        assertEquals(1, state.getInventory().getKeys());
    }

}