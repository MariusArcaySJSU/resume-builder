package App.View;

import java.time.LocalDate;
import java.util.ArrayList;

import App.Model.Resume.ResumeItems.Project;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ProjectFX extends VBox {

    private final TextField titleField;
    private final TextField technologiesField;
    private final TextArea descriptionArea;
    private final DatePicker startDatePicker;
    private final DatePicker endDatePicker;
    private final CheckBox displayDate;

    public ProjectFX() {
        super(10);

        Label titleLabel = new Label("Title:");
        titleField = new TextField();
        titleField.setPromptText("Resume Builder");

        Label technologiesLabel = new Label("Technologies used (comma seperated):");
        technologiesField = new TextField();
        technologiesField.setPromptText("Java, VSCode, Maven...");

        Label descriptionLabel = new Label("Description:");
        descriptionArea = new TextArea();

        Label startDateLabel = new Label("Start Date:");
        startDatePicker = new DatePicker();
        startDatePicker.setPromptText("MM/DD/YYYY");

        Label endDateLabel = new Label("End Date:");
        endDatePicker = new DatePicker();
        endDatePicker.setPromptText("MM/DD/YYYY");

        displayDate = new CheckBox("Display Date");
        displayDate.setSelected(true);

        displayDate.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            startDatePicker.setDisable(!isNowSelected);
            endDatePicker.setDisable(!isNowSelected);
        });

        this.getChildren().addAll(
                titleLabel, titleField,
                technologiesLabel, technologiesField,
                descriptionLabel, descriptionArea,
                startDateLabel, startDatePicker,
                endDateLabel, endDatePicker,
                displayDate
        );
    }

    public Project getProject() {

        String[] technologies = getTechnologies().toArray(String[]::new);
        return new Project(getTitle(), technologies, new String[]{ getDescription() }, getStartDate(), getEndDate(), getDisplayDate());

    }

    public String getTitle() {
        return titleField.getText();
    }

    public ArrayList<String> getTechnologies() {
        
        ArrayList<String> result = new ArrayList<>();
        String[] technologiesArray = technologiesField.getText().split(",");

        for (String technology : technologiesArray) {
            result.add(technology.trim());
        }

        return result;
    }

    public String getDescription() {
        return descriptionArea.getText();
    }

    public LocalDate getStartDate() {
        return startDatePicker.getValue();
    }

    public LocalDate getEndDate() {
        return endDatePicker.getValue();
    }

    public Boolean getDisplayDate() {
        return displayDate.isSelected();
    }
}
