package nl.rug.oop.rts.Model.Graph.Army;

import lombok.Getter;

/**
 * Class Unit that represent soldiers of every army/faction.
 */
public class Unit {
    @Getter
    private String name;
    @Getter
    private int health;
    @Getter
    private int damage;
    @Getter
    private Army army;

    /**
     * The constructor of the Unit.
     * @param army the army it belongs to.
     * @param name the unit name.
     * @param health the unit health.
     * @param damage the unit damage.
     */
    public Unit(Army army, String name, int health, int damage) {
        this.army = army;
        this.name = name;
        this.health = health;
        this.damage = damage;
    }
}
