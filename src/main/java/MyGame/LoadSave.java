package MyGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Load level from saved position
 */
public class LoadSave {

    private int level;
    private boolean hasSword;
    private int numOfArrows;
    private int numOfKeys;

    /**
     * Constructor of the class
     */
    public LoadSave() {
        startReading();
    }

    /**
     * Opens save file and reads its content
     */
    private void startReading() {
        try {
            File myObj = new File("save.txt");
            Scanner myReader = new Scanner(myObj);
            level = myReader.nextInt();
            if(myReader.nextInt() == 1) {
                hasSword = true;
            }
            else {
                hasSword = false;
            }
            numOfArrows = myReader.nextInt();
            numOfKeys = myReader.nextInt();
            myReader.close();
        } catch (FileNotFoundException ex) {
            level = 1;
            hasSword = true;
            numOfArrows = 1;
            numOfKeys = 0;
        }
    }

    /**
     * Getter for Level
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /*public boolean hasSword() {
        return hasSword;
    }*/

    /**
     * Getter for arrows
     * @return number of arrows
     */
    public int getNumOfArrows() {
        return numOfArrows;
    }

    /**
     * Getter for keys
     * @return number of keys
     */
    public int getNumOfKeys() {
        return numOfKeys;
    }

    /**
     * Getter for inventory
     * @return inventory
     */
    public Inventory getInventory(){
        Inventory inventory = new Inventory();
        inventory.setHasSword(hasSword);
        inventory.setArrows(getNumOfArrows());
        inventory.setKeys(getNumOfKeys());
        return inventory;
    }
}
