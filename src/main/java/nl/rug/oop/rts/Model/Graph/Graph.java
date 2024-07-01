package nl.rug.oop.rts.Model.Graph;

import lombok.Getter;
import nl.rug.oop.rts.Model.Graph.Army.Army;

import java.util.ArrayList;
import java.util.List;

/**
 * The class to create graphs.
 * */
public class Graph {
    @Getter
    private List<Edge> edges;
    @Getter
    private List<Node> nodes;
    @Getter
    private List<Army> goodGuysArmies;
    @Getter
    private List<Army> badGuysArmies;
    @Getter
    private List<Event> events;
    @Getter
    private int nodesIndex = 0;
    @Getter
    private int edgesIndex = 0;

    /**
     * The constructor of the Graph.
     * */
    public Graph() {
        edges = new ArrayList<>();
        nodes = new ArrayList<>();
        goodGuysArmies = new ArrayList<>();
        badGuysArmies = new ArrayList<>();
        events = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
        nodesIndex++;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
        edgesIndex++;
    }

    public void addArmy(Army army) {
        army.createBattleTeams(goodGuysArmies, badGuysArmies, army);
    }

    /**
     * Remove a node from the graph.
     * @param node the node to be removed.
     * */
    public void removeNode(Node node) {
        if (node != null) {
            List<Edge> edgesToRemove = new ArrayList<>(node.getEdges());
            for (Edge edge : edgesToRemove) {
                removeEdge(edge);
            }
            if (!node.getArmies().isEmpty()) {
                List<Army> armiesToRemove = new ArrayList<>(node.getArmies());
                for (Army army : armiesToRemove) {
                    removeArmy(army);
                }
            }
            if (!node.getEvents().isEmpty()) {
                List<Event> eventsToRemove = new ArrayList<>(node.getEvents());
                for (Event event : eventsToRemove) {
                    removeEvent(event);
                }
            }
            nodes.remove(node);
        }
    }

    /**
     * Remove an edge from the graph.
     * @param edge the edge to be removed.
     * */
    public void removeEdge(Edge edge) {
        edge.getStartNode().getEdges().remove(edge);
        edge.getEndNode().getEdges().remove(edge);
        edge.setStartNode(null);
        edge.setEndNode(null);
        if (!edge.getArmies().isEmpty()) {
            List<Army> armiesToRemove = new ArrayList<>(edge.getArmies());
            for (Army army : armiesToRemove) {
                removeArmy(army);
            }
        }
        if (!edge.getEvents().isEmpty()) {
            List<Event> eventsToRemove = new ArrayList<>(edge.getEvents());
            for (Event event : eventsToRemove) {
                removeEvent(event);
            }
        }
        edges.remove(edge);
    }

    public void removeArmy(Army army) {
        army.removeTeamMember(goodGuysArmies, badGuysArmies, army);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }
}
