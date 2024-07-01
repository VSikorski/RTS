package nl.rug.oop.rts.Controller;

import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.Model.Graph.Army.BadGuys.Mordor;
import nl.rug.oop.rts.Model.Graph.Army.GoodGuys.Men;
import nl.rug.oop.rts.Model.Graph.Edge;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.Model.MapModel;

import java.util.Random;

/**
 * Class helps the SimulationController with the events joining.
 * */
public class SimulationEventsController {

    private MapController mapController;
    private MapModel mapModel;
    private Random random;
    private SidePanelController sidePanelController;

    /**
     * Constructor for SimulationEventsController.
     * @param mapController reference to MapController.
     * @param mapModel reference to MapModel.
     * @param random reference to Random instance.
     * */
    public SimulationEventsController(MapController mapController, MapModel mapModel, Random random) {
        this.mapController = mapController;
        this.mapModel = mapModel;
        this.random = random;
    }

    public void bindSidePanelController(SidePanelController sidePanelController) {
        this.sidePanelController = sidePanelController;
    }

    /**
     * Joins an event of type1 on a node.
     * @param node the node the event is applied to.
     * */
    public void joinEventType1(Node node) {
        if (node.getArmies().size() < 5) {
            int randomFaction = random.nextInt(2);
            if (randomFaction == 0) {
                Men men = new Men(node);
                node.addArmy(men);
                mapModel.getMapGraph().addArmy(men);
                mapController.showPopUp("Event: Reinforcement, more men are coming to help");
            } else {
                Mordor mordor = new Mordor(node);
                node.addArmy(mordor);
                mapModel.getMapGraph().addArmy(mordor);
                mapController.showPopUp("Event: Reinforcement, more mordors are coming to help");
            }
        } else {
            mapController.showPopUp("Event: There are too many armies already");
        }
    }

    /**
     * Joins an event of type1 on an edge.
     * @param edge the edge the event is applied to.
     * */
    public void joinEventType1(Edge edge) {
        if (edge.getArmies().size() < 5) {
            int randomFaction = random.nextInt(2);
            if (randomFaction == 0) {
                Men men = new Men(edge.getStartNode());
                edge.addArmy(men);
                mapModel.getMapGraph().addArmy(men);
                mapController.showPopUp("Event: Reinforcement, more men are coming to help");
            } else {
                Mordor mordor = new Mordor(edge.getStartNode());
                edge.addArmy(mordor);
                mapModel.getMapGraph().addArmy(mordor);
                mapController.showPopUp("Event: Reinforcement, more mordors are coming to help");
            }
        } else {
            mapController.showPopUp("Event: There are too many armies already, discarding the event");
        }
    }

    /**
     * Joins an event of type2 on an edge.
     * @param edge the edge the event is applied to.
     * */
    public void joinEventType2(Edge edge) {
        if (!edge.getArmies().isEmpty()) {
            int index = random.nextInt(edge.getArmies().size());
            Army army = edge.getArmies().get(index);
            mapController.showPopUp("Event: Natural disaster, " + army.getType() + " has been annihilated");
            edge.removeArmy(army);
            mapModel.getMapGraph().removeArmy(army);
            mapModel.disableAllActiveArmiesBesides(null);
            mapModel.notifyAllObservers();
        }
    }

    /**
     * Joins an event of type2 on a node.
     * @param node the node the event is applied to.
     * */
    public void joinEventType2(Node node) {
        if (!node.getArmies().isEmpty()) {
            int index = random.nextInt(node.getArmies().size());
            Army army = node.getArmies().get(index);
            mapController.showPopUp("Event: Natural disaster, " + army.getType() + " has been annihilated");
            node.removeArmy(army);
            mapModel.getMapGraph().removeArmy(army);
            mapModel.disableAllActiveArmiesBesides(null);
            mapModel.notifyAllObservers();
        }
    }

    /**
     * Joins an event of type3 on a node.
     * @param node the node the event is applied to.
     * */
    public void joinEventType3(Node node) {
        if (!node.getArmies().isEmpty()) {
            for (Army army : node.getArmies()) {
                army.setDamage(army.getDamage() * 2);
            }
            mapController.showPopUp("Event: FURY! Armies received double damage!");
        }
    }

    /**
     * Joins an event of type3 on an edge.
     * @param edge the edge the event is applied to.
     * */
    public void joinEventType3(Edge edge) {
        if (!edge.getArmies().isEmpty()) {
            for (Army army : edge.getArmies()) {
                army.setDamage(army.getDamage() * 2);
            }
            mapController.showPopUp("Event: FURY! Armies received double damage!");
        }
    }

}
