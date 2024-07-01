package nl.rug.oop.rts.Controller.interfaces;

import java.util.List;

/**
 * Observable Interface for observable classes.
 */
public interface Observable {
    List<Observer> getObservers();

    default void addObserver(Observer observer) {
        getObservers().add(observer);
    }

    /**
     * Function that notifies all the observers of an observable class.
     */
    default void notifyAllObservers() {
        for(Observer observer : getObservers()) {
            observer.triggerUpdate();
        }
    }
}
