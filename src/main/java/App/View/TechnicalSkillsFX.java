package App.View;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class TechnicalSkillsFX extends HBox {
    private final TextField categoryField;
    private final TextField skillsField;

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


    public String getCategory() {
        return categoryField.getText();
    }

    public ArrayList<String> getSkills() {
        
        ArrayList<String> result = new ArrayList<>();
        String[] technologiesArray = skillsField.getText().split(",");

        for (String technology : technologiesArray) {
            result.add(technology.trim());
        }

        return result;
    }
}