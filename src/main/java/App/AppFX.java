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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import io.github.cdimascio.dotenv.Dotenv;

public class AppFX extends Application {

    private ToolBarFX toolBar;
    private GenerateTabFX generateTab;
    private Controller controller;

    private ScrollPane manifestoScrollPane;
    private ScrollPane generateScrollPane;

    final private Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

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
        Runnable showGenerateTab = () -> tabPane.getSelectionModel().select(1);
        Runnable showManifestoTab = () -> tabPane.getSelectionModel().select(0);
        manifestoTab.setOnContinue(showGenerateTab);
        generateTab.setOnReturn(showManifestoTab);
        toolBar = new ToolBarFX(manifestoScrollPane, showGenerateTab);

        if (!isApiKeySet()) toolBar.setApiKeyPrompt();

        BorderPane root = new BorderPane();
        root.getStyleClass().add("app-root");
        root.setTop(toolBar);
        root.setCenter(tabPane);
        Scene scene = new Scene(root, 700, 700);
        String stylesheet = getClass().getResource("/app.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);



        // Creates controller that handles interactions
        controller = new Controller(root, toolBar, generateTab, manifestoScrollPane);

        stage.setTitle("Resume Builder");
        stage.setScene(scene);
        stage.show();

    }

    private Boolean isApiKeySet() {
        if (dotenv.get("OPENAI_API_KEY") == null) {
            System.out.println("Error fetching api key, will prompt user for one");
            return false;
        } else {
            return true;
        }
    }

    private ScrollPane createTab(TabPane tabPane, VBox tabBox, String title) {
        StackPane content = new StackPane(tabBox);
        content.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Tab tab = new Tab(title);
        tab.setContent(scrollPane);

        tabPane.getTabs().add(tab);
        return scrollPane;
    }

}
