package App;

import App.Controller.Controller;
import App.View.GenerateTabFX;
import App.View.ManifestoTabFX;
import App.View.ToolBarFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppFX extends Application {

    private ToolBarFX toolBar;
    private GenerateTabFX generateTab;
    private Controller controller;

    private ScrollPane manifestoScrollPane;
    private ScrollPane generateScrollPane;

    @Override
    public void start(Stage stage) throws Exception {

        // Creates tabPane and all the tabs within
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        ManifestoTabFX manifestoTab = new ManifestoTabFX();
        generateTab = new GenerateTabFX();


        // Creates toolBar
        manifestoScrollPane = createTab(tabPane, manifestoTab, "Manifesto");
        generateScrollPane = createTab(tabPane, generateTab, "Generate");
        toolBar = new ToolBarFX(manifestoScrollPane);

        BorderPane root = new BorderPane();
        root.setTop(toolBar);
        root.setCenter(tabPane);
        Scene scene = new Scene(root, 700, 700);



        // Creates controller that handles interactions
        controller = new Controller(root, toolBar, generateTab, manifestoScrollPane);

        stage.setTitle("Resume Builder");
        stage.setScene(scene);
        stage.show();

    }

    private ScrollPane createTab(TabPane tabPane, VBox tabBox, String title) {
        ScrollPane scrollPane = new ScrollPane(tabBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Tab tab = new Tab(title);
        tab.setContent(scrollPane);

        tabPane.getTabs().add(tab);
        return scrollPane;
    }

}
