package nl.rug.oop.rts.View.Map;

import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.Model.Graph.Edge;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.util.AppInfo;
import nl.rug.oop.rts.util.TextureLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class helps MapView draw the factions.
 * */
public class MapFactionsView {
    private MapView mapView;
    private Image menImage;
    private Image elvesImage;
    private Image dwarvesImage;
    private Image mordorImage;
    private Image isengardImage;

    public MapFactionsView(MapView mapView) {
        loadImages();
        this.mapView = mapView;
    }

    /**
     * Loads images from the TextureLoader util.
     * */
    private void loadImages() {
        menImage = TextureLoader.getInstance().getTexture("factionMen",
                AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE());
        elvesImage = TextureLoader.getInstance().getTexture("factionElves",
                AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE());
        dwarvesImage = TextureLoader.getInstance().getTexture("factionDwarves",
                AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE());
        mordorImage = TextureLoader.getInstance().getTexture("factionMordor",
                AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE());
        isengardImage = TextureLoader.getInstance().getTexture("factionIsengard",
                AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE());
    }

    /**
     * Draws a faction on edge.
     * @param g pointer to graphics.
     * @param edge pointer to edge.
     * */
    public void drawFactionOnEdge(Graphics g, Edge edge) {
        int xStartNode = edge.getStartNode().getX() + AppInfo.getNODESIZE()/2;
        int yStartNode = edge.getStartNode().getY() + AppInfo.getNODESIZE()/2;
        int xEndNode = edge.getEndNode().getX() + AppInfo.getNODESIZE()/2;
        int yEndNode = edge.getEndNode().getY() + AppInfo.getNODESIZE()/2;
        int i = 0;
        int xS = xStartNode - (xStartNode - xEndNode)/3;
        int yS = yStartNode - (yStartNode - yEndNode)/3;
        int j = 0;
        int xE = xEndNode - (xEndNode - xStartNode)/3;
        int yE = yEndNode - (yEndNode - yStartNode)/3;
        Font boldFont1 = new Font(g.getFont().getName(), Font.BOLD, 10);
        for(Army army : edge.getArmies()) {
            String option = army.getType();
            if(army.getCurrentNode() == edge.getStartNode()) {
                drawFactionImageOnEdge(g, i, xS, yS, option, army);
                g.setFont(boldFont1);
                String labelText = army.getHealth() + "";
                g.setColor(Color.BLACK);
                int x = xS - 20;
                int y = yS + i + 8;
                g.drawString(labelText, x, y);
                i += 20;
            } else {
                drawFactionImageOnEdge(g, j, xE, yE, option, army);
                g.setFont(boldFont1);
                String labelText = army.getHealth() + "";
                g.setColor(Color.BLACK);
                int x = xE - 20;
                int y = yE + j + 8;
                g.drawString(labelText, x, y);
                j += 20;
            }
            if(army.isActive()) {
                drawArmyRectangle(g, army);
            }
        }
    }

    /**
     * Draws a faction image on edge.
     * @param g pointer to graphics.
     * @param i helping parameter.
     * @param x coordinate.
     * @param y coordinate.
     * @param option of the image faction to draw.
     * @param army pointer to the faction's army.
     * */
    public void drawFactionImageOnEdge(Graphics g, int i, int x, int y, String option, Army army) {
        switch(option) {
            case "Men":
                g.drawImage(menImage, x - 5, y + i, AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE(), mapView);
                break;
            case "Elves":
                g.drawImage(elvesImage, x - 5, y + i, AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE(), mapView);
                break;
            case "Dwarves":
                g.drawImage(dwarvesImage, x - 5, y + i, AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE(), mapView);
                break;
            case "Mordor":
                g.drawImage(mordorImage, x - 5, y + i, AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE(), mapView);
                break;
            case "Isengard":
                g.drawImage(isengardImage, x - 5, y + i, AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE(), mapView);
                break;
            case "Good Guys Army":
                g.setColor(Color.GREEN);
                g.fillRect(x - 5, y + i, AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE());
                break;
            case "Bad Guys Army":
                g.setColor(Color.RED);
                g.fillRect(x - 5, y + i, AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE());
                break;
        }
        army.setX(x-5);
        army.setY(y+i);
    }

    /**
     * Draws a faction a node.
     * @param g pointer to graphics.
     * @param node pointer to node.
     * */
    public void drawFactionOnNode(Graphics g, Node node) {
        java.util.List<Integer> iJ = new ArrayList<>();
        iJ.add(-5);
        iJ.add(-5);
        Font boldFont1 = new Font(g.getFont().getName(), Font.BOLD, 10);
        for(Army army : node.getArmies()) {
            String option = army.getType();
            drawFactionImageOnNode(g, node, iJ, option);
            army.setXCoordinatesOnNode(node.getX());
            army.setYCoordinatesOnNode(node.getY(), iJ);
            if(army.isActive()) {
                drawArmyRectangle(g, army);
            }
            java.util.List<Integer> xY = new ArrayList<>();
            xY.add(0);
            xY.add(0);
            xY = army.setTextCoordinatesForFN(xY, iJ, node);
            g.setFont(boldFont1);
            String labelText = army.getHealth() + "";
            g.setColor(Color.BLACK);
            g.drawString(labelText, xY.get(0), xY.get(1));
            iJ = army.incrementPrintingPositionForFN(iJ);
        }
    }

    /**
     * Draws a faction image on a node.
     * @param g pointer to graphics.
     * @param node pointer to the node.
     * @param option of the image faction to draw.
     * @param iJ helping parameter.
     * */
    public void drawFactionImageOnNode(Graphics g, Node node, List<Integer> iJ, String option) {
        switch(option) {
            case "Men":
                g.drawImage(menImage, node.getX() - 10, node.getY() + iJ.get(0),
                        AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE(), mapView);
                break;
            case "Elves":
                g.drawImage(elvesImage, node.getX() - 10, node.getY() + iJ.get(0),
                        AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE(), mapView);
                break;
            case "Dwarves":
                g.drawImage(dwarvesImage, node.getX() - 10, node.getY() + iJ.get(0),
                        AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE(), mapView);
                break;
            case "Mordor":
                g.drawImage(mordorImage, node.getX() + AppInfo.getNODESIZE() - 20,
                        node.getY() + iJ.get(1), AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE(), mapView);
                break;
            case "Isengard":
                g.drawImage(isengardImage, node.getX() + AppInfo.getNODESIZE() - 20,
                        node.getY() + iJ.get(1), AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE(), mapView);
                break;
            case "Good Guys Army":
                g.setColor(Color.GREEN);
                g.fillRect(node.getX() - 10, node.getY() + iJ.get(0), AppInfo.getARMYSIZE(),
                        AppInfo.getARMYSIZE());
                break;
            case "Bad Guys Army":
                g.setColor(Color.RED);
                g.fillRect(node.getX() + AppInfo.getNODESIZE() - 20, node.getY() + iJ.get(1),
                        AppInfo.getARMYSIZE(), AppInfo.getARMYSIZE());
                break;
        }
    }

    private void drawArmyRectangle(Graphics g, Army army) {
        g.setColor(Color.RED);
        g.drawRect(army.getX() - 2, army.getY() - 2, AppInfo.getARMYSIZE() + 4, AppInfo.getARMYSIZE() + 4);
    }
}
