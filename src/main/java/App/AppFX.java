package App;

import App.Controller.Controller;
import App.View.GenerateTabFX;
import App.View.ManifestoTabFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppFX extends Application {

    private final ManifestoTabFX manifestoTab;
    private final GenerateTabFX generateTab;

    private final Controller controller;

    public AppFX() {
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

    }

    @Override
    public void start(Stage stage) {

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        createTab(tabPane, manifestoTab, "Manifesto");
        createTab(tabPane, generateTab, "Generate");

        Scene scene = new Scene(tabPane, 700, 700);

        stage.setTitle("Resume Builder");
        stage.setScene(scene);
        stage.show();

    }

    private void createTab(TabPane tabPane, VBox tabBox, String title) {
        ScrollPane scrollPane = new ScrollPane(tabBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Tab tab = new Tab(title);
        tab.setContent(scrollPane);

        tabPane.getTabs().add(tab);
    }

}
