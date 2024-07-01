package nl.rug.oop.rts.Controller.interfaces;

import nl.rug.oop.rts.Model.Graph.Army.Army;

/**
 * Interface that defines the methods an object with armies should have.
 * */
public interface HasArmy {

    void addArmy(Army a);

    void removeArmy(Army a);
}
