package App.View;

import java.util.ArrayList;

import App.Model.Resume.ResumeItems.Education;
import App.Model.Resume.ResumeItems.EducationGroup;
import App.Model.Resume.ResumeItems.ResumeItem;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class EducationGroupFX extends VBox {

    private final TitledPane titledPane;
    private final VBox educationList;

    public EducationGroupFX(EducationGroup educationGroup) {
        this();
        clear();

        for (ResumeItem item : educationGroup.items) {

            Education e = (Education) item;
            addEducation(e);

        }
    }

    public EducationGroupFX() {
        educationList = new VBox(40);
        educationList.setPadding(new Insets(15));
        
        Button addEducation = new Button("Add education");
        addEducation.setOnAction(event -> {
            addEducation();
        });
        
        titledPane = new TitledPane("Education", new VBox(educationList, addEducation));
        titledPane.setCollapsible(true);

        addEducation();

        this.getChildren().add(titledPane);

    }

    public void clear() {
        educationList.getChildren().clear();
    }

    private void addEducation() {
        addEducation(null);
    }

    private void addEducation(Education education) {

        EducationFX educationFX = new EducationFX(education);
        Button removeButton = new Button("Remove");
        removeButton.setFocusTraversable(false);

        VBox educationBox;
        if (educationList.getChildren().isEmpty()) {
            educationBox = new VBox(10, educationFX, new Separator());
        } else {
            educationBox = new VBox(10, educationFX, removeButton, new Separator());
        }

        educationList.getChildren().add(
            educationBox
        );

        removeButton.setOnAction(event -> {
            educationList.getChildren().remove(educationBox);
        });

    }

    public EducationGroup getEducationGroup() {

        ArrayList<Education> educationArrayList = new ArrayList<>();

        for (Node node : educationList.getChildren()) {
            VBox educationBox = (VBox) node;
            EducationFX educationFX = (EducationFX) educationBox.getChildren().get(0);

            educationArrayList.add(educationFX.getEducation());
        }

        Education[] educationArray = educationArrayList.toArray(Education[]::new);

        return new EducationGroup(educationArray);
        
    }

}