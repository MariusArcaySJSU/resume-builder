package App.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GenerateTabFX extends VBox {
    
    private final VBox generatePane;
    private final TextArea jobDescription;
    private final CheckBox openWhenDone;
    private final Button generateTailoredResumeButton;
    private final Button generateRawResumeButton;
    private final Button returnButton;

    public GenerateTabFX() {

        super(50);

        Label jobDescriptionLabel = new Label("Job Description:");
        jobDescription = new TextArea();
        jobDescription.setWrapText(true);
        jobDescription.setPrefRowCount(12);

        openWhenDone = new CheckBox("Open when done");
        generateRawResumeButton = new Button("Generate Raw Resume");       
        generateTailoredResumeButton = new Button("Generate Tailored Resume");
        returnButton = new Button("Return to Manifesto");

        openWhenDone.setSelected(true);

        generatePane = new VBox(15, jobDescriptionLabel, jobDescription, openWhenDone, new HBox(10, generateTailoredResumeButton, generateRawResumeButton));
        generatePane.getStyleClass().add("generate-panel");

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
        this.getStyleClass().add("generate-page");
        generateTailoredResumeButton.getStyleClass().add("primary-action");
        returnButton.getStyleClass().add("secondary-action");
        Label pageTitle = new Label("Generate your resume");
        pageTitle.getStyleClass().add("page-title");
        Label pageSubtitle = new Label("Create a polished resume from the details you added.");
        pageSubtitle.getStyleClass().add("page-subtitle");
        this.getChildren().addAll(
            pageTitle,
            pageSubtitle,
            generatePane,
            returnButton
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

    public void setOnReturn(Runnable action) {
        returnButton.setOnAction(event -> action.run());
    }

}
