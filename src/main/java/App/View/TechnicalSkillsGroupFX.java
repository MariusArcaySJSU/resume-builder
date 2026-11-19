package App.View;

import java.util.ArrayList;

import App.Model.Resume.ResumeItems.TechnicalSkills;
import App.Model.Resume.ResumeItems.TechnicalSkillsGroup;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class TechnicalSkillsGroupFX extends VBox {

    private final TitledPane titledPane;
    private final VBox technicalSkillsBox;

    public TechnicalSkillsGroupFX() {

        technicalSkillsBox = new VBox(5);

        Button addSkills = new Button("Add set of skills");
        addSkills.setOnAction(event -> {
            addSkills();
        });

        addSkills();

        titledPane = new TitledPane("Technical Skills", new VBox(10, technicalSkillsBox, addSkills));
        titledPane.setCollapsible(true);

        this.getChildren().add(titledPane);
    }

    public TechnicalSkillsGroup getTechnicalSkillsGroup() {

        ArrayList<TechnicalSkills> technicalSkillList = new ArrayList<>();

        for (Node node : technicalSkillsBox.getChildren()) {

            TechnicalSkillsFX skillFX = (TechnicalSkillsFX) ((HBox) node).getChildren().get(0);

            String category = skillFX.getCategory();
            ArrayList<String> skills = skillFX.getSkills();

            TechnicalSkills technicalSkills = new TechnicalSkills(category, skills.toArray(String[]::new));
            technicalSkillList.add(technicalSkills);

        }

        TechnicalSkills[] technicalSkillsArray = technicalSkillList.toArray(TechnicalSkills[]::new);

        return new TechnicalSkillsGroup(technicalSkillsArray);

    }

    private void addSkills() {

        TechnicalSkillsFX skill = new TechnicalSkillsFX();
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
