package nl.rug.oop.rts.View.ToolBar;

import nl.rug.oop.rts.util.AppInfo;

import javax.swing.*;

/**
 * A custom button for the toolbar.
 * */
public class ToolBarBtn extends JButton {

    /**
     * The constructor of the ToolBarBtn.
     * @param text the text of the button.
     * */
    public ToolBarBtn(String text) {
        setText(text);
        setBackground(AppInfo.getTOOLBARCOLOR());
        setForeground(AppInfo.getTOOLBARTEXTCOLOR());
        setFocusable(false);
    }
}
