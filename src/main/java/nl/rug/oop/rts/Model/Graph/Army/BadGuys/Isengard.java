package nl.rug.oop.rts.Model.Graph.Army.BadGuys;

import nl.rug.oop.rts.Model.Graph.Army.Unit;
import nl.rug.oop.rts.Model.Graph.Node;

import java.util.Arrays;
import java.util.List;

/**
 * A type of the bad guys faction.
 * */
public class Isengard extends BadGuys {

    /**
     * Constructor of the Isengard Faction.
     * @param motherNode the node the army spawned on.
     */
    public Isengard(Node motherNode) {
        super(motherNode, "Isengard");
        super.setHealth(0);
        super.setDamage(0);
        int numberOfUnits = super.getRandom().nextInt(10, 41);
        List<String> isengardNames = Arrays.asList("Uruk-hai", "Uruk Crossbowman", "Warg Rider",
                                                   "Uruk Berserker", "Uruk Scout", "Uruk Pikeman",
                                                   "Dunlending Warrior", "Dunlending Archer", "Saruman's Servant");

        for(int i = 0; i < numberOfUnits; i++) {
            int health = super.getRandom().nextInt(2, 3);
            int damage = super.getRandom().nextInt(1,2);
            int nameId = super.getRandom().nextInt(9);
            String name = isengardNames.get(nameId);

            super.getUnits().add(new Unit(this, name, health, damage));
            super.setHealth(super.getHealth() + health);
            super.setDamage(super.getDamage() + damage);
        }
    }

    @Override
    public String getType() {
        return "Isengard";
    }
}
