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

    private final ToolBarFX toolBar;
    private ManifestoTabFX manifestoTab;
    private final GenerateTabFX generateTab;
    private final Controller controller;

    private ScrollPane manifestoScrollPane;

    public AppFX() throws Exception {

        toolBar = new ToolBarFX();
        manifestoTab = new ManifestoTabFX();
        generateTab = new GenerateTabFX();
        controller = new Controller();

        generateTab.setOnGenerateManifesto( event -> {
                try {
                    controller.generateManifesto(
                        manifestoTab.generateManifesto(), 
                        generateTab.getOpenWhenDone()
                    ); 
                } catch (Exception e) {
                    System.out.println("Error generating manifesto:" + e.getMessage());
                    e.printStackTrace();
                }
            }
        );

        generateTab.setOnGenerateResume( event -> {
                try {
                    controller.generateResume(
                        manifestoTab.generateManifesto(), 
                        generateTab.getJobDescription(), 
                        generateTab.getOpenWhenDone()
                    ); 
                } catch (Exception e) {
                    System.out.println("Error generating manifesto:" + e.getMessage());
                    e.printStackTrace();
                }
            }
        );

        toolBar.newItem.setOnAction( event -> {
            manifestoTab = new ManifestoTabFX();
            manifestoScrollPane.setContent(manifestoTab);
        });

    }

    @Override
    public void start(Stage stage) {

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        manifestoScrollPane = createTab(tabPane, manifestoTab, "Manifesto");
        createTab(tabPane, generateTab, "Generate");

        BorderPane root = new BorderPane();
        root.setTop(toolBar);
        root.setCenter(tabPane);

        Scene scene = new Scene(root, 700, 700);

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
