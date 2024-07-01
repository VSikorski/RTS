package nl.rug.oop.rts.Controller;

import nl.rug.oop.rts.View.ToolBar.ToolBarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Controller of the ToolBar.
 * */
public class ToolBarController {

    private ToolBarView toolBarView;
    private MapController mapController;
    private SimulationController simulationController;
    private SaveController saveController;

    /**
     * The constructor of the ToolBarController.
     * @param tbw reference to ToolBarView.
     * @param mc reference to MapController.
     * @param sc reference to SimulationController.
     * */
    public ToolBarController(ToolBarView tbw, MapController mc, SimulationController sc) {
        toolBarView = tbw;
        mapController = mc;
        simulationController = sc;
        listenForBtnClicks();
    }

    public void bindSaveController(SaveController saveController) {
        this.saveController = saveController;
    }

    /**
     * Begins listening for toolbar button clicks.
     * */
    public void listenForBtnClicks() {
        toolBarView.getAddNodeBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapController.handleAddNewNode();
            }
        });
        toolBarView.getRemoveNodeBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapController.handleRemoveNode();
            }
        });
        toolBarView.getAddEdgeBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapController.handleAddEdge();
            }
        });
        toolBarView.getRemoveEdgeBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapController.handleRemoveEdge();
            }
        });
        toolBarView.getSimulationBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulationController.simulateOneTimeStep();
            }
        });
        toolBarView.getToJsonBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveController.handleSaveToJson();
            }
        });
    }
}
