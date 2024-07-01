package nl.rug.oop.rts.View.SideBar;

import lombok.Getter;
import nl.rug.oop.rts.Controller.interfaces.Observer;
import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.Model.Graph.Edge;
import nl.rug.oop.rts.Model.Graph.Graph;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.util.AppInfo;

import javax.swing.*;
import java.awt.*;

/**
 * SidePanelView displays information about the node or edge.
 * */
public class SidePanelView extends JPanel implements Observer {

    private HeaderPanel headerPanel;
    @Getter
    private NodePanel nodePanel;
    private NodeArmyPanel nodeArmyPanel;
    @Getter
    private EdgePanel edgePanel;
    private EmptyPanel emptyPanel;
    private ArmyPanel armyPanel;
    @Getter
    private Edge selectedEdge;
    private Graph graph;

    /**
     * The constructor of the SidePanelView.
     * @param graph pointer to the map graph.
     * */
    public SidePanelView(Graph graph) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(AppInfo.getWINDOWX() / 7, AppInfo.getWINDOWY()));
        headerPanel = new HeaderPanel();
        add(headerPanel);
        headerPanel.setVisible(true);

        nodeArmyPanel = new NodeArmyPanel();
        add(nodeArmyPanel);
        nodeArmyPanel.setVisible(false);

        nodePanel = new NodePanel();
        add(nodePanel);
        nodePanel.setVisible(false);

        edgePanel = new EdgePanel();
        add(edgePanel);
        edgePanel.setVisible(false);

        armyPanel = new ArmyPanel();
        add(armyPanel);
        armyPanel.setVisible(false);

        emptyPanel = new EmptyPanel();
        add(emptyPanel);

        selectedEdge = null;
        this.graph = graph;
    }

    @Override
    public void triggerUpdate() {

        for (Node node : graph.getNodes()) {
            if (node.isActive()) {
                displayNodeInformation(node);
                return;
            }
        }

        for (Edge edge : graph.getEdges()) {
            if (edge.isActive()) {
                displayEdgeInformation(edge);
                return;
            }
        }

        for (Army army : graph.getGoodGuysArmies()) {
            if (army.isActive()) {
                displayArmyInformation(army);
                return;
            }
        }

        for (Army army : graph.getBadGuysArmies()) {
            if (army.isActive()) {
                displayArmyInformation(army);
                return;
            }
        }

        displayEmptyPanel();
    }

    /**
     * Displays the empty panel.
     * */
    public void displayEmptyPanel() {
        edgePanel.setVisible(false);
        nodePanel.setVisible(false);
        nodeArmyPanel.setVisible(false);
        armyPanel.setVisible(false);
        emptyPanel.setVisible(true);
    }

    /**
     * Displays information about the edge.
     * @param edge the edge whose information to display.
     * */
    public void displayEdgeInformation(Edge edge) {
        emptyPanel.setVisible(false);
        nodePanel.setVisible(false);
        nodeArmyPanel.setVisible(false);
        armyPanel.setVisible(false);
        edgePanel.setEdgeName(edge);
        edgePanel.setConnectingNodeNames(edge);
        edgePanel.displayEventsInformation(edge.getEvents());
        edgePanel.setVisible(true);
        selectedEdge = edge;
    }

    /**
     * Displays information about a node.
     * @param node the node whose information to display.
     * */
    public void displayNodeInformation(Node node) {
        emptyPanel.setVisible(false);
        edgePanel.setVisible(false);
        nodePanel.setNodeName(node);
        nodePanel.displayEventsInformation(node.getEvents());
        nodePanel.setVisible(true);
        nodeArmyPanel.updateNodeArmyPanel(node.getArmies());
        nodeArmyPanel.setVisible(true);
        armyPanel.setVisible(false);
    }

    /**
     * Displays Army information.
     * @param army slected army.
     */
    public void displayArmyInformation(Army army) {
        armyPanel.showInformation(army);
        armyPanel.setVisible(true);
        nodePanel.setVisible(false);
        nodeArmyPanel.setVisible(false);
        edgePanel.setVisible(false);
        emptyPanel.setVisible(false);
    }
}
