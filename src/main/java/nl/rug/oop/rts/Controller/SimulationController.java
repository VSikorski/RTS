package nl.rug.oop.rts.Controller;

import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.Model.Graph.Edge;
import nl.rug.oop.rts.Model.Graph.Event;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.Model.MapModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The controller for time step simulation.
 * */
public class SimulationController {
    private MapModel mapModel;
    private MapController mapController;
    private SidePanelController sidePanelController;
    private Random random;
    private SimulationFightController simulationFightController;
    private SimulationEventsController simulationEventsController;

    /**
     * The constructor of the SimulationController.
     * @param mapController reference to MapController.
     * @param sidePanelController reference to SidePanelController.
     * */
    public SimulationController(MapController mapController, SidePanelController sidePanelController) {
        this.mapController = mapController;
        mapModel = mapController.getMapModel();
        this.sidePanelController = sidePanelController;
        random = new Random();
        simulationFightController = new SimulationFightController(mapModel, random, sidePanelController);
        simulationEventsController = new SimulationEventsController(mapController, mapModel, random);
        simulationEventsController.bindSidePanelController(sidePanelController);
    }

    /**
     * Simulates one single time step.
     * */
    public void simulateOneTimeStep() {
        boolean stepComplete = false;
        if (joinEvent()) {
            stepComplete = true;
        }
        if (!stepComplete && simulationFightController.fightOnNode()) {
            stepComplete = true;
        }
        if (!stepComplete && moveToEdge()) {
            stepComplete = true;
        }
        if (!stepComplete && simulationFightController.fightOnEdge()) {
            stepComplete = true;
        }
        if (!stepComplete) {
            moveToNode();
        }
        mapModel.notifyAllObservers();
        winLosePopUp();
    }

    private boolean joinEvent() {
        boolean joinedEvent = joinEventOnNode();
        if (!joinedEvent) {
            joinedEvent = joinEventOnEdge();
        }
        return joinedEvent;
    }

    private boolean joinEventOnEdge() {
        boolean joinedEvent = false;
        for (Edge edge : mapModel.getMapGraph().getEdges()) {
            if (!edge.getEvents().isEmpty() && !edge.getArmies().isEmpty()) {
                int joinEvent = random.nextInt(2);
                if (joinEvent == 0) {
                    int index = random.nextInt(edge.getEvents().size());
                    Event event = edge.getEvents().get(index);
                    if (event.getType() == 1) {
                        simulationEventsController.joinEventType1(edge);
                    } else if (event.getType() == 2) {
                        simulationEventsController.joinEventType2(edge);
                    } else if (event.getType() == 3) {
                        simulationEventsController.joinEventType3(edge);
                    }
                    edge.removeEvent(event);
                    mapModel.getMapGraph().removeEvent(event);
                    mapModel.notifyAllObservers();
                    joinedEvent = true;
                    break;
                }
            }
        }
        return joinedEvent;
    }

    private boolean joinEventOnNode() {
        boolean joinedEvent = false;
        for (Node node : mapModel.getMapGraph().getNodes()) {
            if (!node.getEvents().isEmpty() && !node.getArmies().isEmpty()) {
                int joinEvent = random.nextInt(2);
                if (joinEvent == 0) {
                    int index = random.nextInt(node.getEvents().size());
                    Event event = node.getEvents().get(index);
                    if (event.getType() == 1) {
                        simulationEventsController.joinEventType1(node);
                    } else if (event.getType() == 2) {
                        simulationEventsController.joinEventType2(node);
                    } else if (event.getType() == 3) {
                        simulationEventsController.joinEventType3(node);
                    }
                    node.removeEvent(event);
                    mapModel.getMapGraph().removeEvent(event);
                    mapModel.notifyAllObservers();
                    joinedEvent = true;
                    break;
                }
            }
        }
        return joinedEvent;
    }

    private boolean moveToNode() {
        boolean movingOccurred = false;
        for (Edge edge : mapModel.getMapGraph().getEdges()) {
            List<Army> armiesToMove = new ArrayList<>(edge.getArmies());
            Node destinationNode = edge.getEndNode();
            for (Army army : armiesToMove) {
                if (army.getCurrentNode() == edge.getEndNode()) {
                    destinationNode = edge.getStartNode();
                }
                army.setCurrentNode(destinationNode);
                destinationNode.addArmy(army);
                edge.removeArmy(army);
                movingOccurred = true;
            }
        }
        return movingOccurred;
    }

    private boolean moveToEdge() {
        boolean movingOccured = false;
        for(Node node : mapModel.getMapGraph().getNodes()) {
            if (!node.getArmies().isEmpty() && !node.getEdges().isEmpty()) {
                List<Army> nodeArmy = new ArrayList<>(node.getArmies());
                for (Army army : nodeArmy) {
                    if (army.getCurrentNode() == node) {
                        int edgeCount = node.getEdges().size();
                        Random random = new Random();
                        int randomEdge = random.nextInt(edgeCount);
                        node.removeArmy(army);
                        node.getEdges().get(randomEdge).addArmy(army);
                        movingOccured = true;
                    }
                }
            }
        }
        return movingOccured;
    }

    /**
     * Shows a pop-up if the player wins/loses the game.
     */
    public void winLosePopUp() {
        if(mapModel.getMapGraph().getBadGuysArmies().isEmpty()) {
            mapModel.getMapView().showPopUp("You won!! :)\n" +
                    "You can continue playing by adding more opponents for even more challenges!");
        } else if(mapModel.getMapGraph().getGoodGuysArmies().isEmpty()){
            mapModel.getMapView().showPopUp("You lost :(\n" +
                    "Try to create another strategy to get rid of the bad guys on the map!");
        }
    }

}
