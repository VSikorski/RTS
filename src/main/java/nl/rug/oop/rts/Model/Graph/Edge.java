package nl.rug.oop.rts.Model.Graph;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.Controller.interfaces.HasArmy;
import nl.rug.oop.rts.Model.Graph.Army.Army;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows the creation of edges.
 * */
public class Edge implements HasArmy {
    @Getter
    private String name;
    @Getter @Setter
    private List<Army> armies;
    @Getter
    private List<Event> events;
    @Getter @Setter
    private Node startNode;
    @Getter @Setter
    private Node endNode;
    @Getter @Setter
    private boolean isActive;
    @Getter
    private int id;

    /**
     * The constructor of the Edge.
     * @param name the name of the edge.
     * @param startNode the staring node of the edge.
     * @param endNode the ending node of the edge.
     * @param id the id of the edge.
     * */
    public Edge(String name, Node startNode, Node endNode, int id) {
        this.name = name;
        armies = new ArrayList<>();
        events = new ArrayList<>();
        this.startNode = startNode;
        this.endNode = endNode;
        this.isActive = false;
        this.id = id;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public void addArmy(Army a) {
        armies.add(a);
    }

    public void removeArmy(Army a) {
        armies.remove(a);
    }
}