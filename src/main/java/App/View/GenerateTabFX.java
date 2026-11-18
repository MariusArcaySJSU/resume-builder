package App.View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class GenerateTabFX extends VBox {
    
    private final TitledPane generatePane;
    private final TextArea jobDescription;

    public GenerateTabFX() {

        super(50);

        Label jobDescriptionLabel = new Label("Job Description:");
        jobDescription = new TextArea();

        Button generate = new Button("Generate");

        generatePane = new TitledPane("Generate", new VBox(15, jobDescriptionLabel, jobDescription, generate));
        generatePane.setCollapsible(false);

        this.setPadding(new Insets(50));
        this.getChildren().addAll(
            generatePane
        );

    }

}
