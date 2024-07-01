package nl.rug.oop.rts.Model.Graph;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.Controller.interfaces.HasArmy;
import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.util.AppInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to create new nodes.
 * */
public class Node implements HasArmy {
    @Getter @Setter
    private String name;
    @Getter
    private List<Edge> edges;
    @Getter
    private List<Event> events;
    @Getter
    private List<Army> armies;
    @Getter @Setter
    private int x;
    @Getter @Setter
    private int y;
    @Getter
    private boolean isActive;
    @Getter
    private int id;

    /**
     * The constructor of the node.
     * @param name the name of the newly created node.
     * @param id the id of the node.
     * */
    public Node(String name, int id) {
        edges = new ArrayList<>();
        armies = new ArrayList<>();
        events = new ArrayList<>();
        this.name = name;
        x = AppInfo.getNODESPAWNX();
        y = AppInfo.getNODESPAWNY();
        isActive = false;
        this.id = id;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void addArmy(Army a) {
        armies.add(a);
    }

    public void removeArmy(Army a) {
        armies.remove(a);
    }
}
