package App.View;

import java.time.LocalDate;
import java.util.ArrayList;

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

    public ExperienceFX(Experience experience) {
        this();
        fill(experience);
    }

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

    public void fill(Experience experience) {

        if (experience == null) return;

        roleField.setText(experience.role);
        employerField.setText(experience.employer);
        locationField.setText(experience.location);

        String descriptionAppended = "";
        for (String bulletPoint : experience.description) {
            descriptionAppended += bulletPoint + ", ";
        }
        if(!descriptionAppended.isBlank()) descriptionAppended = descriptionAppended.substring(0, descriptionAppended.length() - 2);
        
        descriptionArea.setText(descriptionAppended);

        startDatePicker.setValue(experience.startDate);
        endDatePicker.setValue(experience.endDate);

    }

    public Experience getExperience() {
        ArrayList<String> description = new ArrayList<>();
        description.add(getDescription());
        return new Experience(getRole(), getEmployer(), description, getLocation(), getStartDate(), getEndDate());
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
