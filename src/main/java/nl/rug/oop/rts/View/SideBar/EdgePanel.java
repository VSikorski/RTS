package nl.rug.oop.rts.View.SideBar;

import lombok.Getter;
import nl.rug.oop.rts.Model.Graph.Edge;
import nl.rug.oop.rts.Model.Graph.Event;
import nl.rug.oop.rts.util.AppInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The panel of the edge information.
 * */
public class EdgePanel extends JPanel {

    private JLabel edgeName;
    private JLabel firstNode;
    private JLabel secondNode;
    private List<JLabel> eventLabelsList;
    @Getter
    private JButton addEventBtn;
    @Getter
    private JButton removeEventBtn;

    /**
     * The constructor of the edge panel.
     * */
    public EdgePanel() {
        setLayout(new GridLayout(18, 1));
        setBorder(new EmptyBorder(0, 10, 0, 10));

        setBackground(AppInfo.getSIDEPABELCOLOR());

        edgeName = new JLabel("Edge", SwingConstants.CENTER);
        edgeName.setFont(new Font("Arial", Font.PLAIN, 14));
        add(edgeName);

        firstNode = new JLabel("Node 1: " + "Node", SwingConstants.CENTER);
        add(firstNode);

        secondNode = new JLabel("Node 2: " + "Node", SwingConstants.CENTER);
        add(secondNode);

        JLabel eventsLabel = new JLabel("Events", SwingConstants.CENTER);
        add(eventsLabel);

        addEventBtn = new JButton("+");
        add(addEventBtn);

        removeEventBtn = new JButton("-");
        add(removeEventBtn);

        eventLabelsList = new ArrayList<>();
    }

    /**
     * Displays the information about the events on the edge.
     * @param events the list of events.
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

    public void setEdgeName(Edge edge) {
        edgeName.setText(edge.getName());
    }

    public void setConnectingNodeNames(Edge edge) {
        firstNode.setText("Node 1: " + edge.getStartNode().getName());
        secondNode.setText("Node 2: " + edge.getEndNode().getName());
    }
}
