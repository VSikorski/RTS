package nl.rug.oop.rts.Model.Graph.Army.BadGuys;

import nl.rug.oop.rts.util.AppInfo;
import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.Model.Graph.Node;

import java.util.List;

/**
 * The faction of the bad guys.
 * */
public class BadGuys extends Army {

    public BadGuys(Node motherNode, String type) {
        super(motherNode, 30, 30);
    }

    @Override
    public void setXCoordinatesOnNode(int x) {
        super.setX(x + AppInfo.getNODESIZE() - 20);
    }

    @Override
    public void setYCoordinatesOnNode(int y, List<Integer> iJ) {
        super.setY(y + iJ.get(1));
    }

    @Override
    public void createBattleTeams(List<Army> goodGuysArmy, List<Army> badGuysArmy, Army army) {
        badGuysArmy.add(army);
    }

    @Override
    public void removeTeamMember(List<Army> goodGuysArmy, List<Army> badGuysArmy, Army army) {
        badGuysArmy.remove(army);
    }

    @Override
    public String getType() {
        return "Bad Guys";
    }

    @Override
    public List<Integer> incrementPrintingPositionForFN(List<Integer> iJ) {
        iJ.set(1, iJ.get(1)+20);
        return iJ;
    }

    @Override
    public List<Integer> setTextCoordinatesForFN(List<Integer> xY, List<Integer> iJ, Node node) {
        xY.set(0, node.getX() + AppInfo.getNODESIZE() + 15);
        xY.set(1, node.getY() + iJ.get(1) + 8);
        return xY;
    }
}
