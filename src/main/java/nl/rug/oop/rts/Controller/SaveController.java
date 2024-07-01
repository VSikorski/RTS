package nl.rug.oop.rts.Controller;

import nl.rug.oop.rts.Model.Graph.Army.Army;
import nl.rug.oop.rts.Model.Graph.Army.Unit;
import nl.rug.oop.rts.Model.Graph.Edge;
import nl.rug.oop.rts.Model.Graph.Event;
import nl.rug.oop.rts.Model.Graph.Graph;
import nl.rug.oop.rts.Model.Graph.Node;
import nl.rug.oop.rts.View.Map.MapView;
import nl.rug.oop.rts.View.SaveToJson.SaveView;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Controller to save the progress to JSON.
 */
public class SaveController {

    private static final String NEWLINE = "\n";
    private static final String COMA = ",";
    private SaveView saveView;
    private Graph graph;
    private MapView mapView;

    /**
     * SaveController constructor.
     * @param graph reference to the current graph.
     * @param mapView reference to MapView.
     * @param saveView reference to SaveView.
     * */
    public SaveController(SaveView saveView, Graph graph, MapView mapView) {
        this.saveView = saveView;
        this.graph = graph;
        this.mapView = mapView;
    }

    /**
     * Handles the "Save to Json" button click from the toolbar.
     * */
    public void handleSaveToJson() {
        if (graph != null) {
            String path = saveView.showFileChooser();
            if (path != null && !path.isEmpty()) {
                if (!path.endsWith(".json")) {
                    path += ".json";
                }
                saveToJson(path);
            }
        } else {
            System.out.println("Graph is null");
        }
    }

    private void saveToJson(String path) {
        String json = serializeGraph(graph);
        saveJsonToFile(json, path);
    }

    private String serializeGraph(Graph graph) {
        StringBuilder sb = new StringBuilder();
        sb.append("{" + NEWLINE);
        sb.append("  \"Nodes\": [\n");
        for (Node node : graph.getNodes()) {
            sb.append("    ").append(serializeNode(node)).append(COMA + NEWLINE);
        }
        removeTrailingComma(sb);
        sb.append("  ],\n");
        sb.append("  \"Edges\": [\n");
        for (Edge edge : graph.getEdges()) {
            sb.append("    ").append(serializeEdge(edge)).append(COMA + NEWLINE);
        }
        removeTrailingComma(sb);
        sb.append("  ],\n");
        sb.append("  \"Nodes Index\": ").append(graph.getNodesIndex()).append(COMA + NEWLINE);
        sb.append("  \"Edges Index\": ").append(graph.getEdgesIndex()).append(NEWLINE);
        sb.append("}");

        return sb.toString();
    }

    private String serializeNode(Node node) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("      \"Id\": ").append(node.getId()).append(COMA + NEWLINE);
        sb.append("      \"Name\": \"").append(node.getName()).append("\"," + NEWLINE);
        sb.append("      \"Armies\": [\n");
        for (Army army : node.getArmies()) {
            sb.append("        ").append(serializeArmy(army)).append(COMA + NEWLINE);
        }
        removeTrailingComma(sb);
        sb.append("      ],\n");
        sb.append("      \"Events\": [\n");
        for (Event event : node.getEvents()) {
            sb.append("        ").append(serializeEvent(event)).append(COMA + NEWLINE);
        }
        removeTrailingComma(sb);
        sb.append("      ]\n");
        sb.append("    }");

        return sb.toString();
    }

    private String serializeEvent(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("          \"Type\": ").append(event.getType()).append(COMA + NEWLINE);
        sb.append("          \"Name\": \"").append(event.getName()).append("\"" + NEWLINE);
        sb.append("        }");

        return sb.toString();
    }

    private String serializeEdge(Edge edge) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("      \"Id\": ").append(edge.getId()).append("," + NEWLINE);
        sb.append("      \"Name\": \"").append(edge.getName()).append("\"," + NEWLINE);
        sb.append("      \"StartNode\": ").append(edge.getStartNode().getName()).append("," + NEWLINE);
        sb.append("      \"EndNode\": ").append(edge.getEndNode().getName()).append(",\n");
        sb.append("      \"Armies\": [\n");
        for (Army army : edge.getArmies()) {
            sb.append("        ").append(serializeArmy(army)).append(",\n");
        }
        removeTrailingComma(sb);
        sb.append("      ],\n");

        sb.append("      \"Events\": [\n");
        for (Event event : edge.getEvents()) {
            sb.append("        ").append(serializeEvent(event)).append(",\n");
        }
        removeTrailingComma(sb);
        sb.append("      ]\n"); // No trailing comma after the last array
        sb.append("    }");

        return sb.toString();
    }

    private String serializeArmy(Army army) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("          \"Type\": \"").append(army.getClass().getSimpleName()).append("\"," + NEWLINE);
        sb.append("          \"Current Node\": \"").append(army.getCurrentNode().getName()).append("\",\n");
        sb.append("          \"Health\": \"").append(army.getHealth()).append(",\n");
        sb.append("          \"Damage\": \"").append(army.getDamage()).append("\n");
        sb.append("          \"Units\": [\n");
        for (Unit unit : army.getUnits()) {
            sb.append("        ").append(serializeUnit(unit)).append(",\n");
        }
        removeTrailingComma(sb);
        sb.append("      ],\n");
        sb.append("          \"Random\": \"").append(army.getRandom()).append("\n");
        sb.append("        }");

        return sb.toString();
    }

    private String serializeUnit(Unit unit) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("          \"Name\": \"").append(unit.getName()).append("\",\n");
        sb.append("          \"Health\": \"").append(unit.getHealth()).append("\",\n");
        sb.append("          \"Damage\": \"").append(unit.getDamage()).append("\",\n");
        sb.append("          \"Army\": \"").append(unit.getArmy()).append("\",\n");
        sb.append("        }");

        return sb.toString();
    }

    private void saveJsonToFile(String json, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(json);
            mapView.showPopUp("JSON saving successful.\nFile location: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            mapView.showPopUp("Error saving file.");
        }
    }

    private void removeTrailingComma(StringBuilder sb) {
        if (sb.charAt(sb.length() - 2) == ',') {
            sb.deleteCharAt(sb.length() - 2);
        }
    }
}
