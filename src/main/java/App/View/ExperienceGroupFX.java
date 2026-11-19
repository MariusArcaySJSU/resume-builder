package App.View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class ExperienceGroupFX extends VBox {

    private final TitledPane titledPane;
    private final VBox experienceList;

    public ExperienceGroupFX() {
        experienceList = new VBox(40);
        experienceList.setPadding(new Insets(15));
        
        Button addexperience = new Button("Add experience");
        addexperience.setOnAction(event -> {
            addexperience();
        });
        
        titledPane = new TitledPane("Experience", new VBox(experienceList, addexperience));
        titledPane.setCollapsible(true);

        addexperience();

        this.getChildren().add(titledPane);

    }

    private void addexperience() {

        ExperienceFX experience = new ExperienceFX();
        Button removeButton = new Button("Remove");
        removeButton.setFocusTraversable(false);

        VBox experienceBox;
        if (experienceList.getChildren().isEmpty()) {
            experienceBox = new VBox(10, experience, new Separator());
        } else {
            experienceBox = new VBox(10, experience, removeButton, new Separator());
        }

        experienceList.getChildren().add(
            experienceBox
        );

        removeButton.setOnAction(event -> {
            experienceList.getChildren().remove(experienceBox);
        });

    }


}