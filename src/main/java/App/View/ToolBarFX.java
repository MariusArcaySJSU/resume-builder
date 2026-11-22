package App.View;

import javafx.application.Platform;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToolBar;

public class ToolBarFX extends ToolBar {

    public final MenuItem newItem = new MenuItem("New");
    public final MenuItem openItem = new MenuItem("Open...");
    public final MenuItem saveItem = new MenuItem("Save");
    public final MenuItem exitItem = new MenuItem("Exit");

    public ToolBarFX() {
        MenuButton fileButton = new MenuButton("File");

        newItem.setOnAction(e -> System.out.println("New clicked"));
        openItem.setOnAction(e -> System.out.println("Open clicked"));
        saveItem.setOnAction(e -> System.out.println("Save clicked"));
        exitItem.setOnAction(e -> Platform.exit());

        fileButton.getItems().addAll(
            newItem,
            openItem,
            saveItem,
            new SeparatorMenuItem(),
            exitItem
        );

        this.getItems().add(fileButton);
    }

}
