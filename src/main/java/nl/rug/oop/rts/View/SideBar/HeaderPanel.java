package nl.rug.oop.rts.View.SideBar;

import nl.rug.oop.rts.util.AppInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The Header Panel of the Side Panel.
 * */
public class HeaderPanel extends JPanel {
    /**
     * The constructor of the HeaderPanel.
     * */
    public HeaderPanel() {
        setLayout(new GridLayout(2, 1));
        setBorder(new EmptyBorder(10, 10, 0, 10));
        setBackground(AppInfo.getSIDEPABELCOLOR());

        JLabel title = new JLabel("Options Menu", SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(0, 10, 10, 10));
        title.setForeground(AppInfo.getTOOLBARTEXTCOLOR());
        add(title);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        add(separator);
    }
}
