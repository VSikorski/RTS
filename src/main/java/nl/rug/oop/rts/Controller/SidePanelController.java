package nl.rug.oop.rts.Controller;

import nl.rug.oop.rts.View.SideBar.SidePanelView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The controller of the side panel.
 * */
public class SidePanelController {

    private SidePanelView sidePanelView;
    private MapController mapController;
    private MapEventsController mapEventsController;
    private MapArmyController mapArmyController;

    /**
     * Constructor of SidePanelController.
     * @param sidePanelView the reference to SidePanelController.
     * */
    public SidePanelController(SidePanelView sidePanelView) {
        this.sidePanelView = sidePanelView;
        listenRenameNode();
        listenAddRemoveArmy();
        listenAddRemoveEvent();
    }

    private void listenRenameNode() {
        sidePanelView.getNodePanel().getRenameBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapController.renameNode(sidePanelView.getNodePanel().getCurrentNode(),
                        sidePanelView.getNodePanel().getRenameTextField().getText());
            }
        });
    }

    private void listenAddRemoveArmy() {
        sidePanelView.getNodePanel().getAddArmyBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapArmyController.handleAddArmy(sidePanelView.getNodePanel().getCurrentNode());
            }
        });

        sidePanelView.getNodePanel().getRemoveArmyBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapArmyController.handleRemoveArmy(sidePanelView.getNodePanel().getCurrentNode());
            }
        });
    }

    private void listenAddRemoveEvent() {
        sidePanelView.getNodePanel().getAddEventBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapEventsController.handleAddEvent(sidePanelView.getNodePanel().getCurrentNode());
            }
        });

        sidePanelView.getNodePanel().getRemoveEventBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapEventsController.handleRemoveEvent(sidePanelView.getNodePanel().getCurrentNode());
            }
        });

        sidePanelView.getEdgePanel().getAddEventBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapEventsController.handleAddEvent(sidePanelView.getSelectedEdge());
            }
        });

        sidePanelView.getEdgePanel().getRemoveEventBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapEventsController.handleRemoveEvent(sidePanelView.getSelectedEdge());
            }
        });
    }

    /**
     * Binds the MapController pointer placeholder to the actual map controller.
     * @param mapController pointer to map controller.
     * */
    public void bindMapController(MapController mapController) {
        this.mapController = mapController;
        mapEventsController = mapController.getMapEventsController();
        mapArmyController = mapController.getMapArmyController();
    }
}
