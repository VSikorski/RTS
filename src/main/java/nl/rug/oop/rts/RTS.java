package nl.rug.oop.rts;

import nl.rug.oop.rts.Controller.*;
import nl.rug.oop.rts.Model.Graph.Graph;
import nl.rug.oop.rts.Model.MapModel;
import nl.rug.oop.rts.View.Map.MapView;
import nl.rug.oop.rts.View.SaveToJson.SaveView;
import nl.rug.oop.rts.View.SideBar.SidePanelView;
import nl.rug.oop.rts.View.ToolBar.ToolBarView;
import nl.rug.oop.rts.util.AppInfo;

import java.awt.*;

/**
 * Class to build and launch the game.
 * */
public class RTS {
    private Graph graph;
    private GameFrame gameframe;

    public RTS() {
        initialize();
    }

    private void initialize() {
        graph = new Graph();

        gameframe = new GameFrame(AppInfo.getAPPNAME(), AppInfo.getWINDOWX(), AppInfo.getWINDOWY());

        SidePanelView sidePanelView = new SidePanelView(graph);
        SidePanelController sidePanelController = new SidePanelController(sidePanelView);

        MapView mapView = new MapView(graph);
        MapModel mapModel = new MapModel(mapView, graph);
        MapController mapController = new MapController(mapView, mapModel, sidePanelController);

        SimulationController simulationController = new SimulationController(mapController, sidePanelController);

        SaveView saveView = new SaveView(mapView);
        SaveController saveController = new SaveController(saveView, graph, mapView);

        ToolBarView toolBarView = new ToolBarView(graph);
        ToolBarController toolBarController = new ToolBarController(toolBarView, mapController, simulationController);
        toolBarController.bindSaveController(saveController);

        mapController.bindToolBarController(toolBarController);
        sidePanelController.bindMapController(mapController);

        new MouseController(mapModel, mapController);

        mapModel.addObserver(mapView);
        mapModel.addObserver(toolBarView);
        mapModel.addObserver(sidePanelView);

        gameframe.add(mapView, BorderLayout.CENTER);
        gameframe.add(sidePanelView, BorderLayout.WEST);
        gameframe.add(toolBarView, BorderLayout.NORTH);

        gameframe.revalidate();
        gameframe.setVisible(false);
    }

    public void start() {
        gameframe.setVisible(true);
    }
}
