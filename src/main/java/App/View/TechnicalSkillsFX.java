package App.View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class TechnicalSkillsFX extends VBox {

    private final VBox technicalSkillsBox;

    public TechnicalSkillsFX() {
        super(10);

        Label categoryLabel = new Label("Category:");
        technicalSkillsBox = new VBox(5);

        Button addSkills = new Button("Add set of skills");
        addSkills.setOnAction(event -> {
            addSkills();
        });

        addSkills();

        this.getChildren().addAll(
            categoryLabel, technicalSkillsBox, addSkills
        );
    }

    private void addSkills() {

        SkillFX skill = new SkillFX();
        Button removeButton = new Button("Remove");
        removeButton.setFocusTraversable(false);
        
        HBox skillBox;
        if (technicalSkillsBox.getChildren().isEmpty()) {
            skillBox = new HBox(10, skill);
        } else {
            skillBox = new HBox(10, skill, removeButton);
        }
        
        technicalSkillsBox.getChildren().add(
            skillBox
        );

        removeButton.setOnAction(event -> {
            technicalSkillsBox.getChildren().remove(skillBox);
        });
    }

}
