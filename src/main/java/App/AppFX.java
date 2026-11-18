package App;

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

    @Override
    public void start(Stage stage) {

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        createTab(tabPane, new ManifestoTabFX(), "Manifesto");
        createTab(tabPane, new GenerateTabFX(), "Generate");

        Scene scene = new Scene(tabPane, 400, 300);

        stage.setTitle("Resume Builder");
        stage.setScene(scene);
        stage.show();

    }

    void createTab(TabPane tabPane, VBox tab, String title) {
        ScrollPane scrollPane = new ScrollPane(tab);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Tab manifestoTab = new Tab(title);
        manifestoTab.setContent(scrollPane);

        tabPane.getTabs().add(manifestoTab);
    }

}
