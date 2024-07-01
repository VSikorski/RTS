package nl.rug.oop.rts.Model.Graph.Army.GoodGuys;

import nl.rug.oop.rts.Model.Graph.Army.Unit;
import nl.rug.oop.rts.Model.Graph.Node;

import java.util.Arrays;
import java.util.List;

/**
 * A type of the good guys faction.
 * */
public class Men extends GoodGuys {

    /**
     * Constructor of the Men Faction.
     * @param motherNode the node the army spawned on.
     */
    public Men(Node motherNode) {
        super(motherNode, "Men");
        super.setHealth(0);
        super.setDamage(0);
        int numberOfUnits = super.getRandom().nextInt(10, 41);
        List<String> menNames = Arrays.asList("Gondor Soldier", "Tower Guard", "Ithilien Ranger",
                                              "Rohan Rider", "Rohan Archer", "Gondor Knight",
                                              "Faramir's Ranger", "Gondor Captain", "Dol Amroth Knight");

        for(int i = 0; i < numberOfUnits; i++) {
            int health = super.getRandom().nextInt(2, 3);
            int damage = super.getRandom().nextInt(1, 2);
            int nameId = super.getRandom().nextInt(9);
            String name = menNames.get(nameId);

            super.getUnits().add(new Unit(this, name, health, damage));
            super.setHealth(super.getHealth() + health);
            super.setDamage(super.getDamage() + damage);
        }
    }

    @Override
    public String getType() {
        return "Men";
    }
}
