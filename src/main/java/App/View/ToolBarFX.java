package App.View;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToolBar;

public class ToolBarFX extends ToolBar {

    public ToolBarFX() {
        MenuButton fileButton = new MenuButton("File");

        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open...");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem exitItem = new MenuItem("Exit");

        newItem.setOnAction(e -> System.out.println("New clicked"));
        openItem.setOnAction(e -> System.out.println("Open clicked"));
        saveItem.setOnAction(e -> System.out.println("Save clicked"));
        exitItem.setOnAction(e -> System.out.println("Exit clicked"));

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
