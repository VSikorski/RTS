package nl.rug.oop.rts.Controller;

import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.Model.Graph.Edge;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.util.AppInfo;
import java.util.List;

/**
 * Helps the MapController validate a user click.
 * */
public class ClickValidator {

    /**
     * Method determines if the click was on a node.
     * @param x coordinates of the mouse click.
     * @param y coordinates.
     * @param node the node to be checked.
     * @return true if it's withing the bounds of the node.
     * */
    public boolean isWithinNodeBounds(int x, int y, Node node) {
        int nodeX = node.getX();
        int nodeY = node.getY();
        int nodeSize = AppInfo.getNODESIZE();
        return x >= nodeX && x <= nodeX + nodeSize && y >= nodeY && y <= nodeY + nodeSize;
    }

    /**
     * Validates a click and returns the node that got clicked.
     * @param x coordinates.
     * @param y coordinates.
     * @param nodes the list of nodes to check.
     * @return the node that got clicked (if any), or null.
     * */
    public Node validateNodeClick(int x, int y, List<Node> nodes) {
        if (!nodes.isEmpty()) {
            for (Node node : nodes) {
                int nodeX = node.getX();
                int nodeY = node.getY();
                int nodeSize = AppInfo.getNODESIZE();
                if (x >= nodeX && x <= nodeX + nodeSize && y >= nodeY && y <= nodeY + nodeSize) {
                    return node;
                }
            }
        }
        return null;
    }

    /**
     * Validates a click on the edge.
     * @param x coordinates.
     * @param y coordinates.
     * @param edges the list of edges to verify.
     * @return the edge that got clicked (if any), or null.
     * */
    public Edge validateEdgeClick(int x, int y, List<Edge> edges) {
        if (!edges.isEmpty()) {
            for (Edge edge : edges) {
                Node startNode = edge.getStartNode();
                Node endNode = edge.getEndNode();
                int x1 = startNode.getX() + AppInfo.getNODESIZE() / 2;
                int y1 = startNode.getY() + AppInfo.getNODESIZE() / 2;
                int x2 = endNode.getX() + AppInfo.getNODESIZE() / 2;
                int y2 = endNode.getY() + AppInfo.getNODESIZE() / 2;
                if (isPointNearLine(x, y, x1, y1, x2, y2, 5)) {
                    return edge;
                }
            }
        }
        return null;
    }

    /**
     * Validates a click on the army.
     * @param x coordinates
     * @param y coordinates
     * @param goodGuysArmies the list of good guys to verify.
     * @param badGuysArmies the list of bad guys to verify.
     * @return the army that got clicked (if any), or null.
     */
    public Army validateArmyClick(int x, int y, List<Army> goodGuysArmies, List<Army> badGuysArmies) {
        if(!goodGuysArmies.isEmpty()) {
            for (Army army : goodGuysArmies) {
                int armyX = army.getX();
                int armyY = army.getY();
                int armySize = AppInfo.getARMYSIZE();
                if (x >= armyX && x <= armyX + armySize && y >= armyY && y <= armyY + armySize) {
                    return army;
                }
            }
        }
        if (!badGuysArmies.isEmpty()) {
            for(Army army : badGuysArmies) {
                int armyX = army.getX();
                int armyY = army.getY();
                int armySize = AppInfo.getARMYSIZE();
                if (x >= armyX && x <= armyX + armySize && y >= armyY && y <= armyY + armySize) {
                    return army;
                }
            }
        }
        return null;
    }

    private boolean isPointNearLine(int px, int py, int x1, int y1, int x2, int y2, int threshold) {
        double distance = pointToLineDistance(px, py, x1, y1, x2, y2);
        return distance <= threshold;
    }

    private double pointToLineDistance(int px, int py, int x1, int y1, int x2, int y2) {
        double A = px - x1;
        double B = py - y1;
        double C = x2 - x1;
        double D = y2 - y1;
        double dot = A * C + B * D;
        double len_sq = C * C + D * D;
        double param = -1;
        if (len_sq != 0) {
            param = dot / len_sq;
        }
        double xx, yy;
        if (param < 0) {
            xx = x1;
            yy = y1;
        } else if (param > 1) {
            xx = x2;
            yy = y2;
        } else {
            xx = x1 + param * C;
            yy = y1 + param * D;
        }
        double dx = px - xx;
        double dy = py - yy;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
