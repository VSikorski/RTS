package nl.rug.oop.rts.Controller;

import lombok.Getter;
import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.Model.MapModel;
import nl.rug.oop.rts.Model.Graph.Edge;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.View.Map.MapView;

/**
 * The controller of all the map activities.
 * */
public class MapController {

    @Getter
    private MapModel mapModel;
    private MapView mapView;
    private ToolBarController toolBarController;
    private SidePanelController sidePanelController;
    private boolean addingEdge;
    private boolean removingEdge;
    @Getter
    private MapEventsController mapEventsController;
    @Getter
    private MapArmyController mapArmyController;

    /**
     * Constructor of the MapController.
     * @param mapModel reference to Map Model.
     * @param mapView reference to Map View.
     * @param sidePanelController reference to Side Panel Controller.
     * */
    public MapController(MapView mapView, MapModel mapModel, SidePanelController sidePanelController) {
        this.mapModel = mapModel;
        this.sidePanelController = sidePanelController;
        this.mapView = mapView;
        addingEdge = false;
        removingEdge = false;
        mapEventsController = new MapEventsController(mapModel, this, sidePanelController);
        mapArmyController = new MapArmyController(mapModel, this, sidePanelController);
    }

    public void bindToolBarController(ToolBarController toolBarController) {
        this.toolBarController = toolBarController;
    }

    /**
     * Handles the click on an edge.
     * @param edge the edge that got clicked.
     * */
    public void edgeClick(Edge edge) {
        edge.setActive(!edge.isActive());
        mapModel.disableAllActiveEdgesBesides(edge);
        mapModel.disableAllActiveArmiesBesides(null);
        mapModel.disableAllActiveNodes();
        if (removingEdge) {
            mapModel.removeEdge(edge);
            removingEdge = false;
            edge = null;
        }
        mapModel.notifyAllObservers();
    }

    /**
     * Handles the click on a node.
     * @param node the node that got clicked.
     * */
    public void nodeClick(Node node) {
        if (node.isActive()) {
            node.setActive(false);
            mapModel.setActiveNode(null);
        } else if (addingEdge && mapModel.existsActiveNode()) {
            mapModel.setSecondNode(node);
            if (mapModel.uniqueEdgePair(mapModel.getActiveNode(), mapModel.getSecondNode())) {
                mapModel.addEdge(mapModel.getActiveNode(), mapModel.getSecondNode());
            }
            addingEdge = false;
        } else {
            mapModel.disableAllActiveNodes();
            mapModel.disableAllActiveEdgesBesides(null);
            mapModel.disableAllActiveArmiesBesides(null);
            node.setActive(true);
            mapModel.setActiveNode(node);
        }
        mapModel.notifyAllObservers();
    }

    /**
     * Handles the click on an army.
     * @param army the army that got clicked.
     * */
    public void armyClick(Army army) {
        if (army.isActive()) {
            army.setActive(false);
        } else {
            mapModel.disableAllActiveEdgesBesides(null);
            mapModel.disableAllActiveArmiesBesides(army);
            mapModel.disableAllActiveNodes();
            mapModel.notifyAllObservers();
            army.setActive(true);
        }

    }

    /**
     * Remove Edge Btn from Toolbar.
     * */
    public void handleRemoveEdge() {
        if(mapModel.getMapGraph().getEdges() != null) {
            for (Edge edge : mapModel.getMapGraph().getEdges()) {
                if (edge.isActive()) {
                    mapModel.removeEdge(edge);
                    mapModel.notifyAllObservers();
                    return;
                }
            }
        }
        removingEdge = true;
    }

    /**
     * Add Edge Btn from Toolbar.
     * */
    public void handleAddEdge() {
        addingEdge = true;
    }

    /**
     * Remove Node Btn from Toolbar.
     * */
    public void handleRemoveNode() {
        if (mapModel.existsActiveNode()) {
            mapModel.removeActiveNode();
            mapModel.notifyAllObservers();
        } else {
            System.out.println("Debug: No active node selected");
        }
    }

    /**
     * Add Node Btn from Toolbar.
     * */
    public void handleAddNewNode() {
        mapModel.addNewNode();
        mapModel.notifyAllObservers();
    }

    /**
     * Renaming the node through the Side Node Panel.
     * @param node the node to be renamed.
     * @param newName the new name of the node.
     * */
    public void renameNode(Node node, String newName) {
        mapModel.renameNode(node, newName);
        mapModel.notifyAllObservers();
    }

    /**
     * Shows a pop-up message to the user.
     * @param message the message to show.
     * */
    public void showPopUp(String message) {
        if (message != null && !message.isEmpty()) {
            mapView.showPopUp(message);
        }
    }
}
