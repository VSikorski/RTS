package nl.rug.oop.rts.Controller;

import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.Model.MapModel;

import javax.swing.*;

/**
 * Class helps the MapController with Army activities.
 * */
public class MapArmyController {
    private MapModel mapModel;
    private MapController mapController;
    private SidePanelController sidePanelController;

    /**
     * Constructor of MapArmyController.
     * @param mapController reference to MapController.
     * @param mapModel reference to MapModel.
     * @param spc reference to SidePanelController.
     * */
    public MapArmyController(MapModel mapModel, MapController mapController, SidePanelController spc) {
        this.mapModel = mapModel;
        this.mapController = mapController;
        sidePanelController = spc;
    }

    /**
     * Add Army to node.
     * @param node the node that gets an army.
     * */
    public void handleAddArmy(Node node) {
        if(node.getArmies().size() < 5) {
            String[] options = {"Men", "Elves", "Dwarves", "Mordor", "Isengard"};
            String choice = (String) JOptionPane.showInputDialog(
                    mapModel.getMapView(),
                    "Choose faction to be added:",
                    "Army Enforcement",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            mapModel.createFaction(node, choice);
            mapModel.notifyAllObservers();
        } else {
            mapController.showPopUp("Too many armies already!");
        }
    }

    /**
     * Removes Army from node.
     * @param node the node that is getting an army removed.
     * */
    public void handleRemoveArmy(Node node) {
        if(!node.getArmies().isEmpty()) {
            int i = node.getArmies().size()-1;
            Army armyToBeRemoved = node.getArmies().get(i);
            node.removeArmy(armyToBeRemoved);
            mapModel.removeArmy(armyToBeRemoved);
            mapModel.notifyAllObservers();
        } else {
            mapController.showPopUp("There are no armies on this node");
        }
    }
}
