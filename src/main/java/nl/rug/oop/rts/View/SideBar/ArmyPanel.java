package nl.rug.oop.rts.View.SideBar;

import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.Model.Graph.Army.Unit;
import nl.rug.oop.rts.util.AppInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The panel of the army information.
 */
public class ArmyPanel extends JPanel {
    private JLabel health;
    private JLabel damage;
    private JTextArea units;

    /**
     * Constructor of the army panel.
     */
    public ArmyPanel() {
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(AppInfo.getSIDEPABELCOLOR());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        // Title label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel title = new JLabel("Selected Army", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 14));
        add(title, gbc);
        // Health label
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        health = new JLabel();
        add(health, gbc);
        // Damage label
        gbc.gridy = 2;
        damage = new JLabel();
        add(damage, gbc);
        // Units label
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel unitsLabel = new JLabel("List of units:");
        add(unitsLabel, gbc);
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        units = new JTextArea();
        units.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(units);
        add(scrollPane, gbc);
    }

    /**
     * Updates the army panel according to the selected army.
     * @param army selected army.
     */
    public void showInformation(Army army) {
        health.setText("Health: " + army.getHealth());
        damage.setText("Damage: " + army.getDamage());
        units.setText("");
        for (Unit unit : army.getUnits()) {
            units.append(unit.getName() + "\n");
        }
    }
}