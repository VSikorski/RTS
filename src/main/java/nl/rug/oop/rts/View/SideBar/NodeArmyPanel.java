package nl.rug.oop.rts.View.SideBar;

import nl.rug.oop.rts.util.AppInfo;
import nl.rug.oop.rts.Model.Graph.Army.Army;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The panel to display information about the armies of the node.
 * */
public class NodeArmyPanel extends JPanel {
    private List<JLabel> armyToBeDisplayed;
    private JLabel title;
    private JSeparator separator;

    /**
     * The constructor of the NodeArmyPanel.
     * */
    public NodeArmyPanel() {
        setLayout(new GridLayout(0, 1));
        setBorder(new EmptyBorder(0, 10, 0, 10));
        setBackground(AppInfo.getSIDEPABELCOLOR());

        armyToBeDisplayed = new ArrayList<>();
        title = new JLabel("Army: no army");
        title.setFont(new Font("Arial", Font.PLAIN, 12));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        add(title);
        separator = new JSeparator(SwingConstants.HORIZONTAL);
        add(separator);
    }

    /**
     * Updates the information of the armies of the node.
     * @param nodeArmy the armies of the node.
     * */
    public void updateNodeArmyPanel(List<Army> nodeArmy) {
        remove(separator);
        if (!armyToBeDisplayed.isEmpty()) {
            for (JLabel armyLabel : armyToBeDisplayed) {
                remove(armyLabel);
            }
            armyToBeDisplayed.clear();
            title.setText("Army: no army");
        }

        if (!nodeArmy.isEmpty()) {
            title.setText("Army:");
            for (Army army : nodeArmy) {
                JLabel j = new JLabel(army.getType() +
                        " (H:" + army.getHealth() + ", D:" + army.getDamage() + ")");
                armyToBeDisplayed.add(j);
                add(j);
            }
        }
        add(separator);
        revalidate();
        repaint();
    }
}
