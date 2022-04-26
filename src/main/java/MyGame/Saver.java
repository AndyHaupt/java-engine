package MyGame;

import java.io.*;

/**
 * Game Save class
 */
public class Saver extends Thread {
    private int level;
    private boolean sword;
    private int arrows;
    private int keys;

    /**
     * Saver constructor
     * @param level level to save on
     * @param inventory inventory to be saved
     */
    public Saver(int level, MyGame.Inventory inventory) {
        this.level = level;

        sword = inventory.hasSword();
        arrows = inventory.getArrows();
        keys = inventory.getKeys();

    }

    /**
     * Creates save text file with all its data
     */
    public void run() {
        File file = new File("save.txt");
        FileWriter fr = null;
        String msg = level + "\n" + ((sword) ? "1" : "0") + " " + arrows + " " + keys;

        try {
            fr = new FileWriter(file);
            fr.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
