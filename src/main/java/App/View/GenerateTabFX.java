package App.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class GenerateTabFX extends VBox {
    
    private final TitledPane generatePane;
    private final TextArea jobDescription;
    private final CheckBox openWhenDone;
    private final Button generateResume;
    private final Button generateManifesto;

    public GenerateTabFX() {

        super(50);

        Label jobDescriptionLabel = new Label("Job Description:");
        jobDescription = new TextArea();

        openWhenDone = new CheckBox("Open when done");
        generateManifesto = new Button("Generate Manifesto");       
        generateResume = new Button("Generate Resume");

        openWhenDone.setSelected(true);

        generatePane = new TitledPane("Generate", new VBox(15, jobDescriptionLabel, jobDescription, openWhenDone, new HBox(10, generateResume, generateManifesto)));
        generatePane.setCollapsible(false);

        this.setPadding(new Insets(50));
        this.getChildren().addAll(
            generatePane
        );

    }

    public Boolean getOpenWhenDone() {
        return openWhenDone.isSelected();
    }

    public void setOnGenerateManifesto(EventHandler<ActionEvent> handler) {
        generateManifesto.setOnAction(handler);
    }

    public void setOnGenerateResume(EventHandler<ActionEvent> handler) {
        generateResume.setOnAction(handler);
    }

    public String getJobDescription() {
        return jobDescription.getText();
    }

}
