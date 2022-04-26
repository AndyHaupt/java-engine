package MyGame;

import MyGame.*;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {

    State state;

    @Before
    public void setUp() throws Exception {
        Player player = new Player(0, 0);
        state = new State();
        state.setPlayer(player);
        Inventory inventory = new Inventory();
        state.setInventory(inventory);
    }

    @org.junit.Test
    public void attack() {
        state.getInventory().setHasSword(false);
        state.getPlayer().attack();
        assertEquals(null, state.getSword());
    }
}