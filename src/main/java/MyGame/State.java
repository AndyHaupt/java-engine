package MyGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Groups all objects visible on board.
 */
public class State {
    private List<DrawAndUpdate> objects;
    private Player player;
    private List<Enemy> enemies;
    private List<Wall> walls;
    private Sword sword;
    private List<Gun> arrows;
    private List<Key> keys;
    private List<Chest> chests;
    private  Inventory inventory;
    private boolean gameOver;
    private boolean gameWon;

    /**
     * State constructor.
     */
    public State() {
        objects = new ArrayList<>();
        enemies = new ArrayList<>();
        walls = new ArrayList<>();
        arrows = new ArrayList<>();
        keys = new ArrayList<>();
        chests = new ArrayList<>();
        inventory = new Inventory();
        objects.add(inventory);
        gameOver = false;
        gameWon = false;

    }

    /**
     * Gets all objects to be drawn and updated.
     * @return list of objects to be drawn and updated
     */
    public List<DrawAndUpdate> getObjects() {
        ArrayList<DrawAndUpdate> copy = new ArrayList<>();
        copy.addAll(objects);
        return copy;
    }

    /**
     * Sets list of DrawAndUpdate objects.
     * @param objects list of DrawAndUpdate objects
     */
    public void setObjects(List<DrawAndUpdate> objects) {
        this.objects = objects;
    }

    public void addObjects(List<DrawAndUpdate> objects){
        this.objects.addAll(objects);
    }

    /**
     * Getter for Player.
     * @return returns player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Setter for Player.
     * @param player Player to be set
     */
    public void setPlayer(Player player) {
        this.player = player;
        objects.add(player);
    }

    /**
     * Getter for list of Enemy.
     * @return list of Enemy
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Setter for list of Enemy.
     * @param enemies list to be set
     */
    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
        objects.addAll(enemies);
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
        objects.add(enemy);
    }

    /**
     * Deletes one instance of Enemy.
     * @param enemy Enemy to be deleted
     */
    public void deleteEnemy(Enemy enemy){
        enemies.remove(enemy);
        objects.remove(enemy);
    }

    /**
     * Getter for list of Wall.
     * @return list of Wall
     */
    public List<Wall> getWalls() {
        return walls;
    }

    /**
     * Setter for list of Wall.
     * @param walls list to be set
     */
    public void setWalls(List<Wall> walls) {
        objects.removeAll(this.walls);
        this.walls = walls;
        objects.addAll(walls);
    }

    /**
     * Getter for Sword.
     * @return Sword instance
     */
    public Sword getSword() {
        return sword;
    }

    /**
     * Setter for Sword.
     * @param sword to be set
     */
    public void setSword(Sword sword) {
        this.sword = sword;
        objects.add(sword);
    }

    /**
     * Deletes Sword instance.
     * @param sword to be deleted
     */
    public void deleteSword(Sword sword){
        this.sword = null;
        objects.remove(sword);
    }

    /**
     * Getter for list of Gun.
     * @return list of Gun
     */
    public List<Gun> getArrows() {
        return arrows;
    }

    /**
     * Adds instance of Gun.
     * @param arrow Gun to be added
     */
    public void addArrow(Gun arrow){
        arrows.add(arrow);
        objects.addAll(arrows);
    }

    /**
     * Deletes Gun instance.
     * @param arrow Gun instance to be deleted
     */
    public void deleteArrow(Gun arrow){
        arrows.remove(arrow);
        objects.remove(arrow);
    }

    /**
     * Getter for list of Key.
     * @return list of Key
     */
    public List<Key> getKeys() {
        return keys;
    }

    /**
     * Setter for list of Key.
     * @param keys list to be set
     */
    public void setKeys(List<Key> keys) {
        this.keys = keys;
        objects.addAll(keys);
    }

    /**
     * Adds 1 Key to inventory
     */
    public void addKeyToInventory(){
        inventory.setKeys(inventory.getKeys() + 1);
    }

    /**
     * Deletes Key instance from board.
     * @param key Key instance to be deleted
     */
    public  void deleteKey(Key key){
        keys.remove(key);
        objects.remove(key);
    }

    /**
     * Getter for list of Chest.
     * @return list of Chest
     */
    public List<Chest> getChests() {
        return chests;
    }

    /**
     * Setter for list of Chest.
     * @param chests list to be set
     */
    public void setChests(List<Chest> chests) {
        this.chests = chests;
        objects.addAll(chests);
    }

    /**
     * Getter for Inventory.
     * @return current Inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Setter for Inventory.
     * @param inventory to be set
     */
    public void setInventory(Inventory inventory) {
        objects.remove(this.inventory);
        this.inventory = inventory;
        objects.add(inventory);
    }

    /**
     * Getter for gameOver.
     * @return true for yes, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Setter fot gameOver
     * @param gameOver boolean that tells if game is over
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Getter for gameWon
     * @return true for yes, false otherwise
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Setter for gameWon
     * @param gameWon boolean that tells if game is won
     */
    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
}
