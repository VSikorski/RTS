package nl.rug.oop.rts.Model.Graph.Army.GoodGuys;

import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.Model.Graph.Node;

import java.util.List;

/**
 * The good guys faction.
 * */
public class GoodGuys extends Army {

    public GoodGuys(Node motherNode, String type) {
        super(motherNode, 50, 15);
    }

    @Override
    public void setXCoordinatesOnNode(int x) {
        super.setX(x - 10);
    }

    @Override
    public void setYCoordinatesOnNode(int y, List<Integer> iJ) {
        super.setY(y + iJ.get(0));
    }

    @Override
    public void createBattleTeams(List<Army> goodGuysArmy, List<Army> badGuysArmy, Army army) {
        goodGuysArmy.add(army);
    }

    @Override
    public void removeTeamMember(List<Army> goodGuysArmy, List<Army> badGuysArmy, Army army) {
        goodGuysArmy.remove(army);
    }

    @Override
    public String getType() {
        return "Good Guys";
    }

    @Override
    public List<Integer> incrementPrintingPositionForFN(List<Integer> iJ) {
        iJ.set(0, iJ.get(0)+20);
        return iJ;
    }

    @Override
    public List<Integer> setTextCoordinatesForFN(List<Integer> xY, List<Integer> iJ, Node node) {
        xY.set(0, node.getX() - 25);
        xY.set(1, node.getY() + iJ.get(0) + 8);
        return xY;
    }
}
