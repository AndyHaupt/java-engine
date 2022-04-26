package MyGame;


import java.awt.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Resolves all sprite collisions
 */
public class CollisionHandler {

    private static final Logger LOGGER = Logger.getLogger(CollisionHandler.class.getName());

    public CollisionHandler() {
    }

    /**
     * Checks for collisions for:
     * <p><ul>
     * <li>walls
     * <li>enemies
     * <li>player
     * <li>arrows
     * <li>sword
     * <li>keys
     * <li>chests
     * </ul><p>MyGame.
     * @param state State variable for changing state of objects
     */
    public void checkCollisions(State state) {
        /*
        r1 = wall
        r2 = player
        r3 = enemy1
        r4 = enemy2
        r5 = sword
        r6 = arrow
        r7 = key
        r8 = chest
        r9 = swordIm
        r10 = arrowIm
         */
        Player player = state.getPlayer();
        List<Wall> walls = state.getWalls();
        List<Enemy> enemies = state.getEnemies();
        Sword sword = state.getSword();
        List<Gun> arrows = state.getArrows();
        List<Key> keys = state.getKeys();
        List<Chest> chests = state.getChests();

        Rectangle r3 = player.getBounds();

        /*
        wall collisions with:
            player
            enemy
         */
        Gun arrToBeDeleted = null;
        for (Wall wall : walls) {
            //wall x player
            Rectangle r1 = wall.getBounds();
            if (r3.intersects(r1)) {
                LOGGER.log(Level.INFO, "player hit wall...");
                player.revert();
            }
            //wall x enemies
            for (Enemy enemy : enemies) {

                Rectangle r2 = enemy.getBounds();


                if (r3.intersects(r2)) {
                    LOGGER.log(Level.INFO, "enemy hit player...");
                    enemy.revert();
                    player.revert();
                    state.setGameOver(true);
                }


                if (r1.intersects(r2)) {
                    LOGGER.log(Level.INFO, "enemy hit wall...");
                    enemy.revert();
                    //change direction
                    int rand = (int) Math.round(Math.random());
                    if (rand == 0) {
                        if (enemy.getDx() == 0) {
                            enemy.setDy(enemy.getDy() * (-1));
                        } else {
                            enemy.setDx(enemy.getDx() * (-1));
                        }
                    } else {
                        double dir = Math.random();
                        if (dir < 0.5) {
                            dir = -1;
                        } else {
                            dir = 1;
                        }

                        if (enemy.getDx() == 0) {
                            enemy.setDx((int) dir);
                            enemy.setDy(0);
                        } else {
                            enemy.setDx(0);
                            enemy.setDy((int) dir);
                        }
                    }
                }
            }
            for(Gun arrow : arrows){
                Rectangle r6 = arrow.getBounds();
                if(r1.intersects(r6)){
                    arrToBeDeleted = arrow;
                }
            }
        }


        Enemy EnToBeDeleted = null;

        //enemy collisions with sword, arrow and enemy
        for (Enemy enemy1 : enemies) {
            Rectangle r2 = enemy1.getBounds();

            if(sword != null){
                Rectangle r5 = sword.getBounds();
                if(r2.intersects((r5))){
                    LOGGER.log(Level.INFO, "dleting enemy...");
                    EnToBeDeleted = enemy1;
                }
            }
            for(Gun arrow : arrows){
                Rectangle r6 = arrow.getBounds();
                if(r2.intersects(r6)){
                    EnToBeDeleted = enemy1;
                    arrToBeDeleted = arrow;
                }
            }
            for(Enemy enemy2 : enemies){
                if(enemy1 == enemy2){
                    continue;
                }
                Rectangle r4 = enemy2.getBounds();

                if(r2.intersects(r4)){
                    LOGGER.log(Level.INFO, "enemy hit enemy...");
                    enemy1.revert();
                    enemy2.revert();
                    int rand = (int) Math.round(Math.random());
                    if(rand ==0) {
                        if (enemy1.getDx() == 0) {
                            enemy1.setDy(enemy1.getDy() * (-1));
                        } else {
                            enemy1.setDx(enemy1.getDx() * (-1));
                        }
                    }
                    else{
                        double dir = Math.random();
                        if(dir < 0.5){
                            dir = -1;
                        }
                        else{
                            dir = 1;
                        }

                        if(enemy1.getDx() == 0){
                            enemy1.setDx((int)dir);
                            enemy1.setDy(0);
                        }
                        else{
                            enemy1.setDx(0);
                            enemy1.setDy((int) dir);
                        }
                    }

                    int rand2 = (int) Math.round(Math.random());
                    if(rand2 == 0) {
                        if (enemy2.getDx() == 0) {
                            enemy2.setDy(enemy2.getDy() * (-1));
                        } else {
                            enemy2.setDx(enemy2.getDx() * (-1));
                        }
                    }
                    else{
                        double dir = Math.random();
                        if(dir < 0.5){
                            dir = -1;
                        }
                        else{
                            dir = 1;
                        }

                        if(enemy2.getDx() == 0){
                            enemy2.setDx((int)dir);
                            enemy2.setDy(0);
                        }
                        else{
                            enemy2.setDx(0);
                            enemy2.setDy((int) dir);
                        }
                    }
                }
            }
        }

        if(arrToBeDeleted != null){
            state.deleteArrow(arrToBeDeleted);
        }

        if(EnToBeDeleted != null){
            state.deleteEnemy(EnToBeDeleted);
        }

        Key keyToBeDeleted = null;
        for(Key key: keys){
            Rectangle r7 = key.getBounds();
            if(r7.intersects(r3)){
                keyToBeDeleted = key;
                state.addKeyToInventory();
            }
        }
        if(keyToBeDeleted != null){
            state.deleteKey(keyToBeDeleted);
        }

        for(Chest chest : chests){
            Rectangle r8 = chest.getBounds();
            if(r8.intersects(r3)){
                if(state.getInventory().getKeys() > 0){
                    state.setGameWon(true);
                }
            }
        }


    }
}
