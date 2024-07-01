package nl.rug.oop.rts.View.Map;

import nl.rug.oop.rts.util.AppInfo;
import nl.rug.oop.rts.Model.Graph.Edge;
import nl.rug.oop.rts.Model.Graph.Graph;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.util.TextureLoader;
import nl.rug.oop.rts.Controller.interfaces.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * The view component of the map.
 * */
public class MapView extends JPanel implements Observer {

    private MapFactionsView mapFactionsView;
    private Graph graphCpy;
    private Image backgroundImage;
    private Image node1Image;

    /**
     * The constructor of the MapView.
     * @param graph reference to the map graph.
     * */
    public MapView(Graph graph) {
        setLayout(null);
        graphCpy = graph;
        mapFactionsView = new MapFactionsView(this);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                loadImages();
            }
        });
    }

    public void triggerUpdate() {
        repaint();
        revalidate();
    }

    /**
     * Loads images from the TextureLoader util.
     * */
    public void loadImages() {
        backgroundImage = TextureLoader.getInstance().getTexture("mapTexture", getWidth(), getHeight());
        node1Image = TextureLoader.getInstance().getTexture("node3", getWidth(), getHeight());
    }

    public void showPopUp(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        if (graphCpy != null) {
            graphCpy.getEdges().forEach(edge -> drawEdge(g, edge));
            graphCpy.getNodes().forEach(node -> drawNode(g, node));
        }
    }

    /**
     * This function draws an edge.
     *
     * @param g used for drawing.
     * @param edge Edge to be drawn.
     */
    private void drawEdge(Graphics g, Edge edge) {
        Graphics2D g2d = (Graphics2D) g;
        if (edge.isActive()) {
            g2d.setColor(Color.RED);
        } else {
            g2d.setColor(AppInfo.getTOOLBARCOLOR());
        }
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(edge.getStartNode().getX() + AppInfo.getNODESIZE() / 2,
                edge.getStartNode().getY() + AppInfo.getNODESIZE() / 2,
                edge.getEndNode().getX() + AppInfo.getNODESIZE() / 2,
                edge.getEndNode().getY() + AppInfo.getNODESIZE() / 2);

        // drawing army on edge
        if(edge.getArmies() != null) {
            mapFactionsView.drawFactionOnEdge(g, edge);
        }

        Font originalFont = g.getFont();
        g.setColor(AppInfo.getTOOLBARCOLOR());
        g.setFont(originalFont);
    }

    /**
     * This function draws a node.
     *
     * @param g used for drawing.
     * @param node node to be drawn.
     */
    private void drawNode(Graphics g, Node node) {
        g.drawImage(node1Image, node.getX(), node.getY(),
                AppInfo.getNODESIZE(), AppInfo.getNODESIZE(), this);
        Font originalFont = g.getFont();

        // Selected node
        if (node.isActive()) {
            drawNodeRectangle(g, node);
        }
        // Add node name
        g.setColor(AppInfo.getTOOLBARCOLOR());
        g.setFont(originalFont);
        Font boldFont2 = new Font(originalFont.getName(), Font.BOLD, originalFont.getSize());
        g.setFont(boldFont2);

        String labelText = node.getName();

        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(labelText);
        int textHeight = fm.getHeight();
        int x = node.getX() + (AppInfo.getNODESIZE() - textWidth) / 2;
        int y = node.getY() + (AppInfo.getNODESIZE() + textHeight / 3) + fm.getAscent();

        // Drawing army on node
        if(node.getArmies() != null) {
            mapFactionsView.drawFactionOnNode(g, node);
        }

        g.setColor(Color.BLACK);
        g.drawString(labelText, x, y);
        g.setColor(AppInfo.getTOOLBARCOLOR());
        g.setFont(originalFont);
    }

    private void drawNodeRectangle(Graphics g, Node node) {
        g.setColor(Color.DARK_GRAY);
        g.drawRect(node.getX() - 2, node.getY() - 2,
                AppInfo.getNODESIZE() + 4, AppInfo.getNODESIZE() + 4);
    }
}
