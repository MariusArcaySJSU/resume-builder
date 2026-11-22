package App.View;

import java.util.ArrayList;

import App.Model.Manifesto.Manifesto;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToolBar;

public class ToolBarFX extends ToolBar {

    public final ScrollPane manifestoScrollPane;

    public final MenuItem newItem = new MenuItem("New");
    public final Menu openItem = new Menu("Open...");
    public final MenuItem saveItem = new MenuItem("Save");
    public final MenuItem exitItem = new MenuItem("Exit");

    public ToolBarFX(ScrollPane manifestoScrollPane) throws Exception {

        this.manifestoScrollPane = manifestoScrollPane;
        MenuButton fileButton = new MenuButton("File");

        fileButton.setOnShowing(event -> {
            try { 
                updateOpenMenu();
            } catch (Exception e) {
                System.out.println("Error updating open menu: " + e.getMessage());
                e.printStackTrace();
            }
        });
        newItem.setOnAction(event -> createNewManifesto());
        saveItem.setOnAction(event -> saveManifesto());
        exitItem.setOnAction(event -> Platform.exit());

        fileButton.getItems().addAll(
            newItem,
            openItem,
            saveItem,
            new SeparatorMenuItem(),
            exitItem
        );

        this.getItems().add(fileButton);
    }

    public void createNewManifesto() {
        manifestoScrollPane.setContent( new ManifestoTabFX() );
        System.out.println("Created new manifesto");
    }

    public void saveManifesto() {

        try {
            ((ManifestoTabFX) manifestoScrollPane.getContent()).generateManifesto().saveManifestoFile();
            System.out.println("Succesfully saved manifesto");

        } catch (Exception e) {
            System.out.println("Error saving manifesto file: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void createAlert(String header, String content) {
        
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();

    }

    private void updateOpenMenu() throws Exception {

        openItem.getItems().clear();

        ArrayList<String> manifestos = Manifesto.listAllManifestoFiles();

        for (String s : manifestos) {

            MenuItem fileButton = new MenuItem(s);
            fileButton.setMnemonicParsing(false);

            fileButton.setOnAction(event -> {

                try {
                    // Manifesto manifesto = new Manifesto( s.split(".")[0] );
                    int dotIndex = s.lastIndexOf('.');
                    String fileNameWithoutExtension = s.substring(0, dotIndex);
                    Manifesto manifesto = new Manifesto( fileNameWithoutExtension );
                    manifestoScrollPane.setContent( new ManifestoTabFX( manifesto ) );
                    System.out.println("sSuccesfully opened manifesto");

                } catch (Exception e) {
                    System.out.println("Error opening manifesto: " + e.getMessage());
                    e.printStackTrace();
                }

            });

            openItem.getItems().add( fileButton );
        }

    }

}
