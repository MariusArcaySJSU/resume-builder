package App.View;

import java.time.LocalDate;
import java.util.ArrayList;

import App.Model.Resume.ResumeItems.Education;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class EducationFX extends VBox {

    private final TextField institutionField;
    private final TextField locationField;
    private VBox degrees;
    private final DatePicker startDatePicker;
    private final DatePicker endDatePicker;

    public EducationFX(Education education) {
        this();
        clearDegrees();
        fill(education);
    }

    public EducationFX() {
        super(10);

        Label institutionLabel = new Label("Institution:");
        institutionField = new TextField();
        institutionField.setPromptText("San Jose State University");

        Label degreesLabel = new Label("Degrees:");
        degrees = new VBox(5);
        addDegree();

        Button addDegree = new Button("Add degree");
        addDegree.setOnAction(event -> {
            addDegree();
        });

        Label locationLabel = new Label("Location:");
        locationField = new TextField();
        locationField.setPromptText("San Jose, CA");

        Label startDateLabel = new Label("Start Date:");
        startDatePicker = new DatePicker();
        startDatePicker.setPromptText("MM/DD/YYYY");

        Label endDateLabel = new Label("End Date (Expected):");
        endDatePicker = new DatePicker();
        endDatePicker.setPromptText("MM/DD/YYYY");

        this.getChildren().addAll(
                institutionLabel, institutionField,
                degreesLabel, degrees, addDegree,
                locationLabel, locationField,
                startDateLabel, startDatePicker,
                endDateLabel, endDatePicker
        );
    }

    public void fill(Education education) {

        if (education == null) return;

        institutionField.setText(education.institution);
        
        for (String degree : education.degrees) {
            addDegree(degree);
        }

        locationField.setText(education.location);
        startDatePicker.setValue(education.startDate);
        endDatePicker.setValue(education.endDate);

    }

    public Education getEducation() {

        String[] degrees = getDegrees().toArray(String[]::new);

        return new Education(getInstitution(), degrees, getLocation(), getStartDate(), getEndDate());

    }

    public String getInstitution() {
        return institutionField.getText();
    }

    public ArrayList<String> getDegrees() {

        ArrayList<String> result = new ArrayList<>();

        for (Node node : degrees.getChildren()) {
            HBox degreeBox = (HBox) node;
            TextField degree = (TextField) degreeBox.getChildren().get(0);
            if (!degree.getText().isBlank()) result.add(degree.getText()); // don't add if entry is empty
        }

        return result;

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

    private void clearDegrees() {
        degrees.getChildren().clear();
    }

    private void addDegree() {
        addDegree("");
    }

    private void addDegree(String degree) {

        TextField degreeField = new TextField(degree);
        degreeField.setPrefWidth(250);

        Button removeButton = new Button("Remove");
        removeButton.setFocusTraversable(false);

        HBox fullDegreeList;
        if (degrees.getChildren().isEmpty()) {
            fullDegreeList = new HBox(10, degreeField);
        } else {
            fullDegreeList = new HBox(10, degreeField, removeButton);
        }

        degrees.getChildren().add(
            fullDegreeList
        );

        removeButton.setOnAction(event -> {
            degrees.getChildren().remove(fullDegreeList);
        });
    }
}
