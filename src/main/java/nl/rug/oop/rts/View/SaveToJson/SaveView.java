package nl.rug.oop.rts.View.SaveToJson;

import nl.rug.oop.rts.View.Map.MapView;

import javax.swing.*;
import java.io.File;

/**
 * The view for the Save Controller.
 * */
public class SaveView{
    private JFileChooser fileChooser;
    private MapView mapView;

    public SaveView(MapView mapView) {
        fileChooser = new JFileChooser();
        this.mapView = mapView;
    }

    /**
     * Asks the user for the location to save the file.
     * @return the location to save the file or null if none was selected.
     * */
    public String showFileChooser() {
        String filePath = null;
        int option = fileChooser.showOpenDialog(mapView);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
        }
        return filePath;
    }
}
