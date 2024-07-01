package nl.rug.oop.rts.Model;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.Model.Graph.Army.BadGuys.Isengard;
import nl.rug.oop.rts.Model.Graph.Army.BadGuys.Mordor;
import nl.rug.oop.rts.Model.Graph.Army.GoodGuys.Dwarves;
import nl.rug.oop.rts.Model.Graph.Army.GoodGuys.Elves;
import nl.rug.oop.rts.Model.Graph.Army.GoodGuys.Men;
import nl.rug.oop.rts.Model.Graph.Edge;
import nl.rug.oop.rts.Model.Graph.Event;
import nl.rug.oop.rts.Model.Graph.Graph;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.View.Map.MapView;
import nl.rug.oop.rts.Controller.interfaces.Observable;
import nl.rug.oop.rts.Controller.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The model of the map.
 * */
public class MapModel implements Observable {

    @Getter
    private Graph mapGraph;
    @Getter
    private MapView mapView;
    @Getter @Setter
    private Node activeNode;
    @Getter @Setter
    private Node secondNode;
    @Getter
    private List<Observer> observers;

    /**
     * The constructor of the MapModel.
     * @param mapView the reference to MapView.
     * @param graph the pointer to the graph.
     * */
    public MapModel(MapView mapView, Graph graph) {
        this.mapView = mapView;
        observers = new ArrayList<>();
        mapGraph = graph;
        activeNode = null;
        secondNode = null;
        notifyAllObservers();
    }

    public void addNewNode() {
        Node newNode = new Node("Node" + mapGraph.getNodesIndex(), mapGraph.getNodesIndex());
        mapGraph.addNode(newNode);
    }

    /**
     * Removes the active node.
     * */
    public void removeActiveNode() {
        if (activeNode != null) {
            mapGraph.removeNode(activeNode);
            activeNode = null;
        }
    }

    public boolean existsActiveNode() {
        return activeNode != null;
    }

    /**
     * Adds a new edge to the map.
     * @param node1 the starting node of the edge.
     * @param node2 the ending node of the edge.
     * */
    public void addEdge(Node node1, Node node2) {
        Edge edge = new Edge("Edge" + mapGraph.getEdgesIndex(), node1, node2, mapGraph.getEdgesIndex());
        node1.addEdge(edge);
        node2.addEdge(edge);
        mapGraph.addEdge(edge);
    }

    /**
     * Determines if an edge pair is unique.
     * @param node1 the starting or ending node to check.
     * @param node2 the starting or ending node to check.
     * @return true if the pair is unique.
     * */
    public boolean uniqueEdgePair(Node node1, Node node2) {
        boolean unique = true;
        if (mapGraph.getEdges() != null) {
            for (Edge edge : mapGraph.getEdges()) {
                if (edge.getStartNode() == node1 && edge.getEndNode() == node2
                        || edge.getStartNode() == node2 && edge.getEndNode() == node1) {
                    unique = false;
                }
            }
        }
        return unique;
    }

    /**
     * Disables all the active edges with the passed exception.
     * @param exception the edge to remain active.
     * */
    public void disableAllActiveEdgesBesides(Edge exception) {
        for(Edge edge : mapGraph.getEdges()) {
            if(edge.isActive() && edge != exception) {
                edge.setActive(false);
            }
        }
    }

    /**
     * Disables all the active armies with the passed exception.
     * @param exception the army to remain active.
     */
    public void disableAllActiveArmiesBesides(Army exception) {
        for(Army army : mapGraph.getGoodGuysArmies()) {
            if(army.isActive() && army != exception) {
                army.setActive(false);
            }
        }
        for(Army army : mapGraph.getBadGuysArmies()) {
            if(army.isActive() && army != exception) {
                army.setActive(false);
            }
        }
    }

    /**
     * Disables all the active nodes.
     * */
    public void disableAllActiveNodes() {
        for (Node node : mapGraph.getNodes()) {
            if (node.isActive()) {
                node.setActive(false);
            }
        }
    }

    public void removeEdge(Edge edge) {
        mapGraph.removeEdge(edge);
    }

    /**
     * Creates a new faction on the edge.
     * @param node the node the faction belongs to.
     * @param choice the type of the faction to create.
     * */
    public void createFaction(Node node, String choice) {
        if (choice != null) {
            switch (choice) {
                case "Men":
                    Men men = new Men(node);
                    node.addArmy(men);
                    mapGraph.addArmy(men);
                    break;
                case "Elves":
                    Elves elves = new Elves(node);
                    node.addArmy(elves);
                    mapGraph.addArmy(elves);
                    break;
                case "Dwarves":
                    Dwarves dwarves = new Dwarves(node);
                    node.addArmy(dwarves);
                    mapGraph.addArmy(dwarves);
                    break;
                case "Mordor":
                    Mordor mordor = new Mordor(node);
                    node.addArmy(mordor);
                    mapGraph.addArmy(mordor);
                    break;
                case "Isengard":
                    Isengard isengard = new Isengard(node);
                    node.addArmy(isengard);
                    mapGraph.addArmy(isengard);
                    break;
            }
        }
    }

    public void removeArmy(Army army) {
        mapGraph.removeArmy(army);
    }

    public void renameNode(Node node, String newName) {
        node.setName(newName);
    }

    /**
     * Creates a new event on the node.
     * @param node the node the event belongs to.
     * @param choice the type of the event.
     * */
    public void createEvent(Node node, int choice) {
        Event event = new Event(choice);
        node.addEvent(event);
        mapGraph.addEvent(event);
    }

    /**
     * Creates an event on an edge.
     * @param edge the edge the event belongs to.
     * @param choice the type of the event.
     * */
    public void createEvent(Edge edge, int choice) {
        Event event = new Event(choice);
        edge.addEvent(event);
        mapGraph.addEvent(event);
    }
}
