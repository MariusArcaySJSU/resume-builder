package App.View;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class LinkFX extends HBox {
    private final TextField urlField;
    private final TextField labelField;

    public LinkFX() {
        this.setSpacing(5);

        labelField = new TextField();
        labelField.setPromptText("Label (e.g., GitHub)");

        urlField = new TextField();
        urlField.setPromptText("URL (e.g., https://github.com)");

        HBox.setHgrow(labelField, Priority.ALWAYS);
        HBox.setHgrow(urlField, Priority.ALWAYS);

        HBox fieldsRow = new HBox(10, labelField, urlField);

        this.getChildren().addAll(fieldsRow);
    }

    public String getUrl() {
        return urlField.getText();
    }

    public String getLabel() {
        return labelField.getText();
    }
}