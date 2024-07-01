package nl.rug.oop.rts.Controller;

import nl.rug.oop.rts.Model.Graph.Edge;
import nl.rug.oop.rts.Model.Graph.Event;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.Model.MapModel;

import javax.swing.*;

/**
 * Class helps the map controller with the event activities.
 * */
public class MapEventsController {
    private MapModel mapModel;
    private MapController mapController;
    private SidePanelController sidePanelController;

    /**
     * Constructor of MapEventsController.
     * @param mapController reference to MapController.
     * @param mapModel reference to MapModel.
     * @param spc reference to SidePanelController.
     * */
    public MapEventsController(MapModel mapModel, MapController mapController, SidePanelController spc) {
        this.mapModel = mapModel;
        this.mapController = mapController;
        sidePanelController = spc;
    }

    /**
     * Removes an event from a node.
     * @param node the node that gets an event removed.
     * */
    public void handleRemoveEvent(Node node) {
        if(!node.getEvents().isEmpty()) {
            int i = node.getEvents().size()-1;
            Event eventToBeRemoved = node.getEvents().get(i);
            node.removeEvent(eventToBeRemoved);
            mapModel.getMapGraph().removeEvent(eventToBeRemoved);
            mapModel.notifyAllObservers();
        } else {
            mapController.showPopUp("There are no events on this node");
        }
    }

    /**
     * Removes an event from an edge.
     * @param edge the edge that gets an event removed.
     * */
    public void handleRemoveEvent(Edge edge) {
        if(!edge.getEvents().isEmpty()) {
            int i = edge.getEvents().size()-1;
            Event eventToBeRemoved = edge.getEvents().get(i);
            edge.removeEvent(eventToBeRemoved);
            mapModel.getMapGraph().removeEvent(eventToBeRemoved);
            mapModel.notifyAllObservers();
        } else {
            mapController.showPopUp("There are no events on this edge");
        }
    }

    /**
     * Adds an event to a node.
     * @param node the node that gets an event.
     * */
    public void handleAddEvent(Node node) {
        if (node.getEvents().size() < 3) {
            String[] options = {"Reinforcement", "Natural Disaster", "Fury"};
            String choice = (String) JOptionPane.showInputDialog(
                    mapModel.getMapView(),
                    "Choose event to be added:",
                    "Events",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice != null) {
                int choiceInt = 1;
                switch (choice) {
                    case "Reinforcement":
                        choiceInt = 1;
                        break;
                    case "Natural Disaster":
                        choiceInt = 2;
                        break;
                    case "Fury":
                        choiceInt = 3;
                        break;
                }
                mapModel.createEvent(node, choiceInt);
                mapModel.notifyAllObservers();
            }
        } else {
            mapController.showPopUp("Too many events already!");
        }
    }

    /**
     * Adds an event to an edge.
     * @param edge the edge that gets an event.
     * */
    public void handleAddEvent(Edge edge) {
        if(edge.getEvents().size() < 3) {
            String[] options = {"Reinforcement", "Natural Disaster", "Fury"};
            String choice = (String) JOptionPane.showInputDialog(
                    mapModel.getMapView(),
                    "Choose event to be added:",
                    "Events",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            int choiceInt = 1;
            if(choice != null) {
                switch (choice) {
                    case "Reinforcement":
                        choiceInt = 1;
                        break;
                    case "Natural Disaster":
                        choiceInt = 2;
                        break;
                    case "Fury":
                        choiceInt = 3;
                        break;
                }
                mapModel.createEvent(edge, choiceInt);
                mapModel.notifyAllObservers();
            }
        } else {
            mapController.showPopUp("To many events already");
        }
    }
}
