package App;

import App.View.HeaderFX;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppFX extends Application {

    private TitledPane headerSection;

    @Override
    public void start(Stage stage) {

        createHeaderSection();

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
            headerSection
        );

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Scene scene = new Scene(scrollPane, 400, 300);

        stage.setTitle("Resume Builder");
        stage.setScene(scene);
        stage.show();

    }

    private void createHeaderSection() {

        VBox header = new HeaderFX();
        header.setPadding(new Insets(15));
        headerSection = new TitledPane("Personal Information", header);
        headerSection.setCollapsible(true);

    }



}
