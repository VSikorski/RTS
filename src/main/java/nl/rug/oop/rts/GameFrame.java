package nl.rug.oop.rts;

import javax.swing.*;
import java.awt.*;

/**
 * The main frame of the application.
 * */
public class GameFrame extends JFrame {

    /**
     * The constructor of the GameFrame.
     * @param title the title of the application.
     * @param windowX the size X of the window.
     * @param windowY the size Y of the window.
     * */
    public GameFrame(String title, int windowX, int windowY) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(windowX, windowY);
        setLocationRelativeTo(null);
        setTitle(title);
    }
}
