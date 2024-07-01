package nl.rug.oop.rts.View.SideBar;

import nl.rug.oop.rts.util.AppInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The empty placeholder panel.
 * */
public class EmptyPanel extends JPanel {

    /**
     * The constructor of the EmptyPanel.
     * */
    public EmptyPanel() {
        setLayout(new GridLayout(18, 1));
        setBorder(new EmptyBorder(0, 10, 0, 10));

        setBackground(AppInfo.getSIDEPABELCOLOR());

        JLabel nodeName = new JLabel(" ", SwingConstants.CENTER);
        nodeName.setFont(new Font("Arial", Font.PLAIN, 14));
        add(nodeName);
    }
}
