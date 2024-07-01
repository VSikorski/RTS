package nl.rug.oop.rts.Controller;

import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.Model.Graph.Edge;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.Model.MapModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class helps the SimulationController with the fight activities.
 * */
public class SimulationFightController {

    private SidePanelController sidePanelController;
    private MapModel mapModel;
    private Random random;

    /**
     * Constructor for SimulationFightController.
     * @param mapModel reference to MapModel.
     * @param random reference to Random instance.
     * @param sidePanelController reference to SidePanelController.
     * */
    public SimulationFightController(MapModel mapModel, Random random, SidePanelController sidePanelController) {
        this.mapModel = mapModel;
        this.random = random;
        this.sidePanelController = sidePanelController;
    }

    /**
     * Method attempts to perform a fight on the node.
     * @return true if a fight occurred.
     * */
    public boolean fightOnNode() {
        boolean fightOccured = false;
        for (Node node : mapModel.getMapGraph().getNodes()) {
            List<Army> goodGuysArmy = new ArrayList<>();
            List<Army> badGuysArmy = new ArrayList<>();
            for(Army army : node.getArmies()) {
                army.createBattleTeams(goodGuysArmy, badGuysArmy, army);
            }
            if (!goodGuysArmy.isEmpty() && !badGuysArmy.isEmpty()) {
                int randomGoodGuys = random.nextInt(goodGuysArmy.size());
                int randomBadGuys = random.nextInt(badGuysArmy.size());
                badGuysArmy.get(randomBadGuys).takeDamage(goodGuysArmy.get(randomGoodGuys).getDamage());
                goodGuysArmy.get(randomGoodGuys).takeDamage(badGuysArmy.get(randomBadGuys).getDamage());
                if (badGuysArmy.get(randomBadGuys).getHealth() <= 0) {
                    node.removeArmy(badGuysArmy.get(randomBadGuys));
                    mapModel.removeArmy(badGuysArmy.get(randomBadGuys));
                    mapModel.notifyAllObservers();
                }
                if (goodGuysArmy.get(randomGoodGuys).getHealth() <= 0) {
                    node.removeArmy(goodGuysArmy.get(randomGoodGuys));
                    mapModel.removeArmy(goodGuysArmy.get(randomGoodGuys));
                    mapModel.notifyAllObservers();
                }
                fightOccured = true;
            }
        }
        return fightOccured;
    }

    /**
     * Method attempts to perform a fight on the edge.
     * @return true if a fight occurred.
     * */
    public boolean fightOnEdge() {
        boolean fightOccured = false;
        for (Edge edge : mapModel.getMapGraph().getEdges()) {
            List<Army> goodGuysArmy = new ArrayList<>();
            List<Army> badGuysArmy = new ArrayList<>();
            for(Army army : edge.getArmies()) {
                army.createBattleTeams(goodGuysArmy, badGuysArmy, army);
            }
            if (!goodGuysArmy.isEmpty() && !badGuysArmy.isEmpty()) {
                int randomGoodGuys = random.nextInt(goodGuysArmy.size());
                int randomBadGuys = random.nextInt(badGuysArmy.size());
                badGuysArmy.get(randomBadGuys).takeDamage(goodGuysArmy.get(randomGoodGuys).getDamage());
                goodGuysArmy.get(randomGoodGuys).takeDamage(badGuysArmy.get(randomBadGuys).getDamage());

                if (badGuysArmy.get(randomBadGuys).getHealth() <= 0) {
                    edge.removeArmy(badGuysArmy.get(randomBadGuys));
                    mapModel.removeArmy(badGuysArmy.get(randomBadGuys));
                    mapModel.disableAllActiveArmiesBesides(null);
                    mapModel.notifyAllObservers();
                }
                if (goodGuysArmy.get(randomGoodGuys).getHealth() <= 0) {
                    edge.removeArmy(goodGuysArmy.get(randomGoodGuys));
                    mapModel.removeArmy(goodGuysArmy.get(randomGoodGuys));
                    mapModel.disableAllActiveArmiesBesides(null);
                    mapModel.notifyAllObservers();
                }
                fightOccured = true;
            }
        }
        return fightOccured;
    }
}
