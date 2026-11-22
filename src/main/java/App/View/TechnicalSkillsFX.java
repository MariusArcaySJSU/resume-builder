package App.View;

import java.util.ArrayList;

import App.Model.Resume.ResumeItems.TechnicalSkills;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class TechnicalSkillsFX extends HBox {
    private final TextField categoryField;
    private final TextField skillsField;

    public TechnicalSkillsFX(TechnicalSkills technicalSkills) {
        this();
        fill(technicalSkills);
    }

    public TechnicalSkillsFX() {
        this.setSpacing(5);

        categoryField = new TextField();
        categoryField.setPromptText("Category (e.g., Languages)");

        skillsField = new TextField();
        skillsField.setPromptText("C++, C, Java, Python...");
        skillsField.setPrefWidth(330);

        HBox.setHgrow(categoryField, Priority.ALWAYS);
        HBox.setHgrow(skillsField, Priority.ALWAYS);

        HBox fieldsRow = new HBox(10, categoryField, skillsField);
        
        HBox.setHgrow(fieldsRow, Priority.ALWAYS);

        this.getChildren().addAll(fieldsRow);
    }

    public void fill(TechnicalSkills technicalSkills) {

        if (technicalSkills == null) return;

        String skillsAppended = "";
        for (String skill : technicalSkills.skills) {
            skillsAppended += skill + ", ";
        }
        if(!skillsAppended.isBlank()) skillsAppended = skillsAppended.substring(0, skillsAppended.length() - 2);

        categoryField.setText(technicalSkills.category);
        skillsField.setText(skillsAppended);
        
    }

    public String getCategory() {
        return categoryField.getText();
    }

    public ArrayList<String> getSkills() {
        
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> technologiesArray = new ArrayList<>(java.util.Arrays.asList(skillsField.getText().split(",")));

        for (String technology : technologiesArray) {
            if (!technology.trim().isBlank()) result.add(technology.trim()); // Don't add if it's blank
        }

        return result;
    }
}