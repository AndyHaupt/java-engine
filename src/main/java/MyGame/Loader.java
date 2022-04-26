package MyGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sets specific game level.
 */
public class Loader {

    private static final Logger LOGGER = Logger.getLogger(Loader.class.getName());

    private int B_WIDTH;
    private int B_HEIGHT;
    private List<Wall> walls;
    private Player player;
    private List<Enemy> enemies;
    private State state;
    private List<Key> keys;
    private List<Chest> chests;
    private String fileName;
    private List<DrawAndUpdate> objects;

    /**
     * Loader constructor.
     * @param fileName name of level file
     * @throws FileNotFoundException file not found exception
     */
    public Loader(String fileName) throws FileNotFoundException {
        objects = new ArrayList<>();
        enemies = new ArrayList<>();
        walls = new ArrayList<>();
        keys = new ArrayList<>();
        chests = new ArrayList<>();
        state = new State();
        this.fileName = fileName;
        startReading(fileName);
    }

    /**
     * Reads level text file. Sets state.
     * @param fileName name of level file
     * @throws FileNotFoundException file not found exception
     */
    private void startReading(String fileName) throws FileNotFoundException {

        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        LOGGER.log(Level.INFO, String.valueOf(myReader.hasNext()));

        B_HEIGHT = myReader.nextInt();
        B_WIDTH = myReader.nextInt();

        String data;
        data = myReader.nextLine();

        for(int i = 0; i < B_HEIGHT; i++){
                data = myReader.nextLine();
                LOGGER.log(Level.INFO, data);
                setter(data, B_WIDTH, i);
        }
        myReader.close();

        state.setWalls(walls);
        state.setPlayer(player);
        state.setEnemies(enemies);
        state.setKeys(keys);
        state.setChests(chests);


    }

    /**
     * Sets the level.
     * @param data read test from file
     * @param length length of level
     * @param height height of level
     */
    private void setter(String data, int length, int height) {
        height *= 16;
        for(int i = 0; i < length; i++){
            char block = data.charAt(i);
            if(block == '#'){
                Wall wall = new Wall(i * 16, height);
                walls.add(wall);
                LOGGER.log(Level.INFO, "Wall added at x: " + i*16 + " y: " + height);
            }
            else if(block =='P'){

                player = new Player(i * 16, height);
            }
            else if(block =='E'){
                Enemy enemy = new Enemy(i * 16, height);
                enemies.add(enemy);
            }
            else if(block == 'C'){
                Chest chest = new Chest(i * 16, height);
                chests.add(chest);
            }
            else if(block == 'K'){
                Key key = new Key(i * 16, height);
                keys.add(key);
            }
        }
    }

    /**
     * Tells width of level.
     * @return width of level
     */
    public int getB_WIDTH() {
        return B_WIDTH * 16;
    }

    /**
     * Tells height of level.
     * @return height of level
     */
    public int getB_HEIGHT() {
        return B_HEIGHT * 16;
    }


    public State getState() {
        return state;
    }
}
