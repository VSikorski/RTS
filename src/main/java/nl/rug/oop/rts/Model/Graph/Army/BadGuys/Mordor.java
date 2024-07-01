package nl.rug.oop.rts.Model.Graph.Army.BadGuys;

import nl.rug.oop.rts.Model.Graph.Army.Unit;
import nl.rug.oop.rts.Model.Graph.Node;

import java.util.Arrays;
import java.util.List;

/**
 * A type of the bad guys faction.
 * */
public class Mordor extends BadGuys {

    /**
     * Constructor of the Mordor Faction.
     * @param motherNode the node the army spawned on.
     */
    public Mordor(Node motherNode) {
        super(motherNode, "Mordor");
        super.setHealth(0);
        super.setDamage(0);
        int numberOfUnits = super.getRandom().nextInt(10, 41);
        List<String> mordorNames = Arrays.asList("Orc Warrior", "Orc Pikeman", "Haradrim Archer",
                                                 "Morgul Sorcerer", "Morgul Captain", "Haradrim Swordsman",
                                                 "Easterling Warrior", "Easterling Pikeman", "Olog-hai");

        for(int i = 0; i < numberOfUnits; i++) {
            int health = super.getRandom().nextInt(2, 3);
            int damage = super.getRandom().nextInt(1,2);
            int nameId = super.getRandom().nextInt(9);
            String name = mordorNames.get(nameId);

            super.getUnits().add(new Unit(this, name, health, damage));
            super.setHealth(super.getHealth() + health);
            super.setDamage(super.getDamage() + damage);
        }
    }

    @Override
    public String getType() {
        return "Mordor";
    }
}
