package nl.rug.oop.rts.View.ToolBar;

import lombok.Getter;
import nl.rug.oop.rts.Controller.interfaces.Observer;
import nl.rug.oop.rts.Model.Graph.Graph;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.util.AppInfo;
import javax.swing.*;
import java.awt.*;

/**
 * The view of the ToolBar.
 * */
public class ToolBarView extends JPanel implements Observer {
    @Getter
    private JButton addNodeBtn;
    @Getter
    private JButton removeNodeBtn;
    @Getter
    private JButton addEdgeBtn;
    @Getter
    private JButton removeEdgeBtn;
    @Getter
    private JButton simulationBtn;
    @Getter
    private JButton toJsonBtn;
    private Graph graph;

    /**
     * The constructor of the ToolBarView.
     * @param graph pointer to the map graph.
     * */
    public ToolBarView(Graph graph) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(AppInfo.getTOOLBARCOLOR());
        addToolBarButtons();
        this.graph = graph;
        setEnabled(removeNodeBtn, false);
        setEnabled(addEdgeBtn, false);
        setEnabled(removeEdgeBtn, false);
        setEnabled(simulationBtn, false);
    }

    /**
     * Adds the buttons to the toolbar.
     * */
    public void addToolBarButtons() {
        addAddNodeBtn();
        addRemoveNodeBtn();
        addAddEdgeBtn();
        addRemoveEdgeBtn();
        add(new JSeparator(SwingConstants.VERTICAL));
        addSimulationBtn();
        addToJsonBtn();
    }

    public void setEnabled(JButton btn, boolean enabled) {
        btn.setEnabled(enabled);
    }

    private void addToJsonBtn() {
        toJsonBtn = new ToolBarBtn("To JSON");
        add(toJsonBtn);
    }

    private void addAddNodeBtn() {
        addNodeBtn = new ToolBarBtn("Add Node");
        add(addNodeBtn);
    }

    private void addRemoveNodeBtn() {
        removeNodeBtn = new ToolBarBtn("Remove Node");
        add(removeNodeBtn);
    }

    private void addAddEdgeBtn() {
        addEdgeBtn = new ToolBarBtn("Add Edge");
        add(addEdgeBtn);
    }

    private void addRemoveEdgeBtn() {
        removeEdgeBtn = new ToolBarBtn("Remove Edge");
        add(removeEdgeBtn);
    }

    private void addSimulationBtn() {
        simulationBtn = new ToolBarBtn("Simulate Time Step");
        add(simulationBtn);
    }

    @Override
    public void triggerUpdate() {
        boolean existsActiveNode = false;
        for (Node node : graph.getNodes()) {
            if (node.isActive()) {
                existsActiveNode = true;
                break;
            }
        }
        if (existsActiveNode) {
            setEnabled(removeNodeBtn, true);
        } else {
            setEnabled(removeNodeBtn, false);
        }

        if (existsActiveNode && graph.getNodes().size() >= 2) {
            setEnabled(addEdgeBtn, true);
        } else {
            setEnabled(addEdgeBtn, false);
        }

        if (!graph.getEdges().isEmpty()) {
            setEnabled(removeEdgeBtn, true);
        } else {
            setEnabled(removeEdgeBtn, false);
        }

        if (!graph.getGoodGuysArmies().isEmpty() &&
                      !graph.getBadGuysArmies().isEmpty() && !graph.getEdges().isEmpty()) {
            setEnabled(simulationBtn, true);
        } else {
            setEnabled(simulationBtn, false);
        }
    }
}
