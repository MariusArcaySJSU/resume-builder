package App.View;

import java.util.ArrayList;

import App.Model.Resume.ResumeItems.Experience;
import App.Model.Resume.ResumeItems.ExperienceGroup;
import App.Model.Resume.ResumeItems.ResumeItem;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class ExperienceGroupFX extends VBox {

    private final TitledPane titledPane;
    private final VBox experienceList;

    public ExperienceGroupFX(ExperienceGroup experienceGroup) {
        this();
        clear();

        for (ResumeItem item : experienceGroup.items) {

            Experience e = (Experience) item;
            addExperience(e);

        }
    }

    public ExperienceGroupFX() {
        experienceList = new VBox(40);
        experienceList.setPadding(new Insets(15));
        
        Button addexperience = new Button("Add experience");
        addexperience.setOnAction(event -> {
            addExperience();
        });
        
        titledPane = new TitledPane("Experience", new VBox(experienceList, addexperience));
        titledPane.setCollapsible(true);

        addExperience();

        this.getChildren().add(titledPane);

    }

    public void clear() {
        experienceList.getChildren().clear();
    }

    private void addExperience() {
        addExperience(null);
    }

    private void addExperience(Experience experience) {

        ExperienceFX experienceFX = new ExperienceFX(experience);
        Button removeButton = new Button("Remove");
        removeButton.setFocusTraversable(false);

        VBox experienceBox;
        if (experienceList.getChildren().isEmpty()) {
            experienceBox = new VBox(10, experienceFX, new Separator());
        } else {
            experienceBox = new VBox(10, experienceFX, removeButton, new Separator());
        }

        experienceList.getChildren().add(
            experienceBox
        );

        removeButton.setOnAction(event -> {
            experienceList.getChildren().remove(experienceBox);
        });

    }

    public ExperienceGroup getExperienceGroup() {

        ArrayList<Experience> experienceArrayList = new ArrayList<>();

        for (Node node : experienceList.getChildren()) {
            VBox experienceBox = (VBox) node;
            ExperienceFX experienceFX = (ExperienceFX) experienceBox.getChildren().get(0);

            experienceArrayList.add(experienceFX.getExperience());
        }

        Experience[] educationArray = experienceArrayList.toArray(Experience[]::new);

        return new ExperienceGroup(educationArray);
        
    }


}