package nl.rug.oop.rts.Model.Graph;

import lombok.Getter;

/**
 * Allows the creation of events.
 * */
public class Event {

    @Getter
    private int type;
    @Getter
    private String name;
    @Getter
    private String description;

    /**
     * The constructor of the event.
     * @param type determines the type of the event.
     * 1 - Reinforcement, adds a random new faction to the battle
     * 2 - Natural disaster, kills a random faction
     * 3 - FURY, doubles all the damages
     * */
    public Event(int type) {
        this.type = type;
        if (type == 1) {
            name = "Reinforcement";
            description = "adds a random new faction to the battle";
        } else if (type == 2) {
            name = "Natural disaster";
            description = "kills a random faction";
        } else if (type == 3) {
            name = "FURY";
            description = "doubles all the damages";
        }
    }
}
