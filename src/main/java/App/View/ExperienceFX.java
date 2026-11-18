package App.View;

import java.time.LocalDate;

import App.Model.Resume.ResumeItems.Experience;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ExperienceFX extends VBox {

    private final TextField roleField;
    private final TextField employerField;
    private final TextField locationField;
    private final TextArea descriptionArea;
    private final DatePicker startDatePicker;
    private final DatePicker endDatePicker;
    private final CheckBox currentlyEmployed;

    public ExperienceFX() {
        super(10);

        Label roleLabel = new Label("Role:");
        roleField = new TextField();
        roleField.setPromptText("Software Engineering Intern");

        Label employerLabel = new Label("Employer:");
        employerField = new TextField();
        employerField.setPromptText("Apple Inc.");

        Label locationLabel = new Label("Location:");
        locationField = new TextField();
        locationField.setPromptText("Cupertino, CA");

        Label descriptionLabel = new Label("Description:");
        descriptionArea = new TextArea();

        Label startDateLabel = new Label("Start Date:");
        startDatePicker = new DatePicker();
        startDatePicker.setPromptText("MM/DD/YYYY");

        Label endDateLabel = new Label("End Date:");
        endDatePicker = new DatePicker();
        endDatePicker.setPromptText("MM/DD/YYYY");

        currentlyEmployed = new CheckBox("Currently Employed");
        
        HBox endDateBox = new HBox(10, endDatePicker, currentlyEmployed);

        currentlyEmployed.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            endDatePicker.setDisable(isNowSelected);
        });

        this.getChildren().addAll(
                roleLabel, roleField,
                employerLabel, employerField,
                locationLabel, locationField,
                descriptionLabel, descriptionArea,
                startDateLabel, startDatePicker,
                endDateLabel, endDateBox
        );
    }

    public Experience getExperience() {
        return new Experience(getRole(), getEmployer(), new String[]{ getDescription() }, getLocation(), getStartDate(), getEndDate());
    }

    public String getRole() {
        return roleField.getText();
    }

    public String getEmployer() {
        return employerField.getText();
    }

    public String getDescription() {
        return descriptionArea.getText();
    }

    public String getLocation() {
        return locationField.getText();
    }

    public LocalDate getStartDate() {
        return startDatePicker.getValue();
    }

    public LocalDate getEndDate() {
        return endDatePicker.getValue();
    }
}
