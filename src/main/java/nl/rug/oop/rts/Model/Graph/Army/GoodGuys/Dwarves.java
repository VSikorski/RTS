package nl.rug.oop.rts.Model.Graph.Army.GoodGuys;

import nl.rug.oop.rts.Model.Graph.Army.Unit;
import nl.rug.oop.rts.Model.Graph.Node;

import java.util.Arrays;
import java.util.List;

/**
 * A type of the good guys faction.
 * */
public class Dwarves extends GoodGuys {

    /**
     * Constructor of the Dwarves Faction.
     * @param motherNode the node the army spawned on.
     */
    public Dwarves(Node motherNode) {
        super(motherNode, "Dwarves");
        super.setHealth(0);
        super.setDamage(0);
        int numberOfUnits = super.getRandom().nextInt(10, 41);
        List<String> dwarfNames = Arrays.asList("Guardian", "Phalanx", "Axe Thrower",
                                                "Dwarven Miner", "Dwarven Warrior", "Dwarven Engineer",
                                                "Dwarven Scout", "Dwarven Berserker", "Dwarven Blacksmith");

        for(int i = 0; i < numberOfUnits; i++) {
            int health = super.getRandom().nextInt(2, 3);
            int damage = super.getRandom().nextInt(1,2);
            int nameId = super.getRandom().nextInt(9);
            String name = dwarfNames.get(nameId);

            super.getUnits().add(new Unit(this, name, health, damage));
            super.setHealth(super.getHealth() + health);
            super.setDamage(super.getDamage() + damage);
        }
    }

    @Override
    public String getType() {
        return "Dwarves";
    }
}
