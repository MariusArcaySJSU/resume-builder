package App.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GenerateTabFX extends VBox {
    
    private final TitledPane generatePane;
    private final TextArea jobDescription;
    private final CheckBox openWhenDone;
    private final Button generateTailoredResumeButton;
    private final Button generateRawResumeButton;

    public GenerateTabFX() {

        super(50);

        Label jobDescriptionLabel = new Label("Job Description:");
        jobDescription = new TextArea();

        openWhenDone = new CheckBox("Open when done");
        generateRawResumeButton = new Button("Generate Raw Resume");       
        generateTailoredResumeButton = new Button("Generate Tailored Resume");

        openWhenDone.setSelected(true);

        generatePane = new TitledPane("Generate", new VBox(15, jobDescriptionLabel, jobDescription, openWhenDone, new HBox(10, generateTailoredResumeButton, generateRawResumeButton)));
        generatePane.setCollapsible(false);

        // Disables tailor button when description is empty
        generateTailoredResumeButton.setDisable(true);
        jobDescription.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.trim().isEmpty()) {
                generateTailoredResumeButton.setDisable(true);
            } else {
                generateTailoredResumeButton.setDisable(false);
            }
        });

        this.setPadding(new Insets(50));
        this.getChildren().addAll(
            generatePane
        );

    }

    public Boolean getOpenWhenDone() {
        return openWhenDone.isSelected();
    }

    public void setOnGenerateRawResume(EventHandler<ActionEvent> handler) {
        generateRawResumeButton.setOnAction(handler);
    }

    public void setOnGenerateTailoredResume(EventHandler<ActionEvent> handler) {
        generateTailoredResumeButton.setOnAction(handler);
    }

    public String getJobDescription() {
        return jobDescription.getText();
    }

}
