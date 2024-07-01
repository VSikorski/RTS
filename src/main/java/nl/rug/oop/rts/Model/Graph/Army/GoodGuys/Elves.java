package nl.rug.oop.rts.Model.Graph.Army.GoodGuys;

import nl.rug.oop.rts.Model.Graph.Army.Unit;
import nl.rug.oop.rts.Model.Graph.Node;

import java.util.Arrays;
import java.util.List;

/**
 * A type of the good guys faction.
 * */
public class Elves extends GoodGuys {

    /**
     * Constructor of the Elves Faction.
     * @param motherNode the node the army spawned on.
     */
    public Elves(Node motherNode) {
        super(motherNode, "Elves");
        super.setHealth(0);
        super.setDamage(0);
        int numberOfUnits = super.getRandom().nextInt(10, 41);
        List<String> elvesNames = Arrays.asList("Lorien Warrior", "Mirkwood Archer", "Rivendell Lancer",
                                                "Lorien Archer", "Mirkwood Scout", "Rivendell Warrior",
                                                "Elven Smith", "Elven Healer", "Elven Mage");

        for(int i = 0; i < numberOfUnits; i++) {
            int health = super.getRandom().nextInt(2, 3);
            int damage = super.getRandom().nextInt(1,2);
            int nameId = super.getRandom().nextInt(9);
            String name = elvesNames.get(nameId);

            super.getUnits().add(new Unit(this, name, health, damage));
            super.setHealth(super.getHealth() + health);
            super.setDamage(super.getDamage() + damage);
        }
    }

    @Override
    public String getType() {
        return "Elves";
    }
}
