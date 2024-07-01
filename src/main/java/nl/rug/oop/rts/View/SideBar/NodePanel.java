package nl.rug.oop.rts.View.SideBar;

import lombok.Getter;
import nl.rug.oop.rts.Model.Graph.Event;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.util.AppInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * NodePanel displays the information of the node on the SidePanel.
 * */
public class NodePanel extends JPanel {

    @Getter
    private Node currentNode;
    @Getter
    private JLabel nodeName;
    @Getter
    private JButton renameBtn;
    @Getter
    private JTextField renameTextField;
    @Getter
    private JLabel armies;
    @Getter
    private JButton addArmyBtn;
    @Getter
    private JButton removeArmyBtn;
    private JLabel eventsLabel;
    @Getter
    private JButton addEventBtn;
    @Getter
    private JButton removeEventBtn;
    private List<JLabel> eventLabelsList;

    /**
     * The constructor of the NodePanel.
     * */
    public NodePanel() {
        setLayout(new GridLayout(18, 1));
        setBorder(new EmptyBorder(0, 10, 0, 10));
        setBackground(AppInfo.getSIDEPABELCOLOR());

        nodeName = new JLabel("Node", SwingConstants.CENTER);
        add(nodeName);

        renameTextField = new JTextField();
        add(renameTextField);

        renameBtn = new JButton("Rename");
        add(renameBtn);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        add(separator);

        armies = new JLabel("Armies", SwingConstants.CENTER);
        add(armies);

        addArmyBtn = new JButton("+");
        add(addArmyBtn);

        removeArmyBtn = new JButton("-");
        add(removeArmyBtn);

        eventsLabel = new JLabel("Events", SwingConstants.CENTER);
        add(eventsLabel);

        addEventBtn = new JButton("+");
        add(addEventBtn);

        removeEventBtn = new JButton("-");
        add(removeEventBtn);

        eventLabelsList = new ArrayList<>();
    }

    /**
     * Displays the information about the events of the node.
     * @param events the list of the events on the node.
     * */
    public void displayEventsInformation(List<Event> events) {
        if (eventLabelsList != null) {
            for (JLabel label : eventLabelsList) {
                remove(label);
            }
        }
        for (Event event : events) {
            JLabel label = new JLabel("", SwingConstants.CENTER);
            if (event.getType() == 1) {
                label.setText("Reinforcement");
            } else if (event.getType() == 2) {
                label.setText("Natural disaster");
            } else if (event.getType() == 3) {
                label.setText("FURY");
            }
            eventLabelsList.add(label);
            add(label);
        }
        revalidate();
        repaint();
    }

    /**
     * Displays the node name.
     * @param node the node which name to display.
     * */
    public void setNodeName(Node node) {
        currentNode = node;
        nodeName.setText(node.getName());
        renameTextField.setText(node.getName());
    }
}
