package nl.rug.oop.rts.Controller;

import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.util.AppInfo;
import nl.rug.oop.rts.Model.Graph.Edge;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.Model.MapModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Helps the MapController handle mouse actions.
 * */
public class MouseController {
    private MapModel mapModel;
    private MapController mapController;
    private Node draggedNode;
    private ClickValidator clickValidator;

    /**
     * The constructor of MouseController.
     * @param mapModel reference to MapModel.
     * @param mapController regerence to MapController.
     * */
    public MouseController(MapModel mapModel, MapController mapController) {
        this.mapController = mapController;
        this.mapModel = mapModel;
        draggedNode = null;
        clickValidator = new ClickValidator();
        listenForMouse();
    }

    /**
     * Begins the listening for mouse events.
     * */
    public void listenForMouse() {
        mapModel.getMapView().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                handleMouseClick(e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                handleMousePress(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                handleMouseRelease();
            }
        });
        mapModel.getMapView().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                handleMouseDrag(e.getX(), e.getY());
            }
        });
    }

    /**
     * All Map clicks.
     * @param x catches the location of the mouse click
     * @param y catches the location of the mouse click
     * */
    private void handleMouseClick(int x, int y) {
        Edge edge = clickValidator.validateEdgeClick(x, y, mapModel.getMapGraph().getEdges());
        Node node = clickValidator.validateNodeClick(x, y, mapModel.getMapGraph().getNodes());
        Army army = clickValidator.validateArmyClick(x, y, mapModel.getMapGraph().getGoodGuysArmies(),
                                                     mapModel.getMapGraph().getBadGuysArmies());

        if (army != null) {
            mapController.armyClick(army);
        } else if (node != null) {
            mapController.nodeClick(node);
        } else if (edge != null) {
            mapController.edgeClick(edge);
        }
        mapModel.notifyAllObservers();
    }

    /**
     * Handles mouse presses.
     * @param x catches location of mouse press on the map
     * @param y catches location of mouse press on the map
     */
    private void handleMousePress(int x, int y) {
        if (mapModel.getMapGraph() != null) {
            for (Node node : mapModel.getMapGraph().getNodes()) {
                if (clickValidator.isWithinNodeBounds(x, y, node)) {
                    draggedNode = node;
                    break;
                }
            }
        }
    }

    /**
     * places the node at the location pointed by the mouse.
     */
    private void handleMouseRelease() {
        draggedNode = null;
    }

    /**
     * Handles the drag of the mouse cursor.
     * @param x catches the initial location before the node is being dragged
     * @param y catches the initial location before the node is being dragged
     */
    private void handleMouseDrag(int x, int y) {
        if (draggedNode != null) {
            draggedNode.setX(x - AppInfo.getNODESIZE() / 2);
            draggedNode.setY(y - AppInfo.getNODESIZE() / 2);
            if(draggedNode.getX() < 0) {
                draggedNode.setX(0);
            }
            if(draggedNode.getY() < 0) {
                draggedNode.setY(0);
            }
            if(draggedNode.getX() + AppInfo.getNODESIZE() > mapModel.getMapView().getWidth()) {
                draggedNode.setX(mapModel.getMapView().getWidth() - AppInfo.getNODESIZE());
            }
            if(draggedNode.getY() + AppInfo.getNODESIZE() > mapModel.getMapView().getHeight()) {
                draggedNode.setY(mapModel.getMapView().getHeight() - AppInfo.getNODESIZE());
            }
            mapModel.notifyAllObservers();
        }
    }
}
