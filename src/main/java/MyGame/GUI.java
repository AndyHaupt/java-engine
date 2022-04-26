package MyGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * GUI gui is the starting point of the whole project. It contains the main function which initializes the GUI of the game.
 */
public class GUI extends JFrame implements ActionListener, WindowListener {

    private static final Logger LOGGER = Logger.getLogger(GUI.class.getName());


    private JButton start;
    private JButton instructions;
    private JFrame instructionFrame;
    private JFrame startGame;
    private Board board;

    /**
     * GUI constructor
     */
    public GUI() {
        JFrame menu = new JFrame();
        menu.setTitle("Chonker II: Electric boogaloo");  // "super" JFrame sets title
        menu.setSize(500, 500);   // "super" JFrame sets initial size
        menu.setVisible(true);    // "super" JFrame shows
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = menu.getContentPane();
        container.setLayout(new FlowLayout());   // The content-pane sets its layout

        start = new JButton("Start");
        instructions = new JButton("Instructions");

        container.add(start);
        container.add(instructions);

        start.addActionListener(this);
        instructions.addActionListener(this);
    }

    /**
     *Main method
     * @param args String of command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();  // Let the constructor do the job
            }
        });
    }

    /**
     * This is the action listener method. It listens if certain button has been pressedů
     * @param e register component to get method invoked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start){
            LOGGER.log(Level.INFO, "clicked start");
            startGame();
        }
        if(e.getSource() == instructions){
            LOGGER.log(Level.INFO, "clicked instructions");
            instructions();
        }
    }

    /**
     * Creates new frame with game board in it.
     */
    private void startGame() {
        startGame = new JFrame();
        startGame.add(new Board());

        startGame.setResizable(false);
        startGame.pack();

        startGame.setTitle("Chonker II: Electric boogaloo");
        startGame.setLocationRelativeTo(null);
        startGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startGame.setVisible(true);
    }

    /**
     * Creates new frame with instructions for the player.
     */
    private void instructions() {
        if (instructionFrame != null && instructionFrame.isDisplayable()) {
            instructionFrame.toFront();
            return;
        }
        instructionFrame = new JFrame();
        instructionFrame.setTitle("Chongus maximus");  // "super" JFrame sets title
        instructionFrame.setSize(500, 500);   // "super" JFrame sets initial size
        instructionFrame.setVisible(true);    // "super" JFrame shows
        instructionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container container = instructionFrame.getContentPane();
        container.setLayout(new FlowLayout());   // The content-pane sets its layout

        container.add(new JLabel("Move using W A S D keys."));
        container.add(new JLabel("Goal: kill all enemies or open all chest."));
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
