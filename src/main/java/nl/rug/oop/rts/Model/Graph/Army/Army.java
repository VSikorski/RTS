package nl.rug.oop.rts.Model.Graph.Army;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.Model.Graph.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstract class Army to create other types of armies/factions.
 * */
public abstract class Army {
    @Getter @Setter
    private Node currentNode;
    @Getter @Setter
    private int health;
    @Getter @Setter
    private int damage;
    @Getter @Setter
    private int x;
    @Getter @Setter
    private int y;
    @Getter @Setter
    private boolean isActive;
    @Getter
    private List<Unit> units;
    @Getter
    private Random random;

    /**
     * The constructor of the Army.
     * @param currentNode the node the army is currently on.
     * @param damage the army damage.
     * @param health the army health.
     * */
    public Army(Node currentNode, int health, int damage) {
        this.currentNode = currentNode;
        this.health = health;
        this.damage = damage;
        isActive = false;
        units = new ArrayList<>();
        random = new Random();
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public abstract void setXCoordinatesOnNode(int x);

    public abstract void setYCoordinatesOnNode(int y, List<Integer> iJ);

    public abstract void createBattleTeams(List<Army> goodGuysArmy, List<Army> badGuysArmy, Army army);

    public abstract void removeTeamMember(List<Army> goodGuysArmy, List<Army> badGuysArmy, Army army);

    public abstract String getType();

    public abstract List<Integer> incrementPrintingPositionForFN(List<Integer> iJ);

    public abstract List<Integer> setTextCoordinatesForFN(List<Integer> xY, List<Integer> iJ, Node node);

}
