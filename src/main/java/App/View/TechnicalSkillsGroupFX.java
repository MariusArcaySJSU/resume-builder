package App.View;

import App.Model.Resume.ResumeItems.ResumeItem;
import App.Model.Resume.ResumeItems.TechnicalSkills;
import App.Model.Resume.ResumeItems.TechnicalSkillsGroup;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class TechnicalSkillsGroupFX extends ResumeItemGroupFX<TechnicalSkills> {

    public TechnicalSkillsGroupFX(TechnicalSkillsGroup technicalSkillsGroup) {
        this();
        clear();

        for (ResumeItem item : technicalSkillsGroup.items) {
            addItem((TechnicalSkills) item);
        }
    }

    public TechnicalSkillsGroupFX() {
        super("Technical Skills", "Add set of skills", 5, null, 10);
        addItem(null);
    }

    public TechnicalSkillsGroup getTechnicalSkillsGroup() {
        return new TechnicalSkillsGroup(getItems().toArray(TechnicalSkills[]::new));
    }

    @Override
    protected Node createItemView(TechnicalSkills technicalSkills) {
        return new TechnicalSkillsFX(technicalSkills);
    }

    @Override
    protected Node createEntry(Node itemView, Button removeButton, boolean firstItem) {
        TechnicalSkillsFX skillFX = (TechnicalSkillsFX) itemView;
        if (firstItem) {
            return new HBox(10, skillFX);
        }
        return new HBox(10, skillFX, removeButton);
    }

    @Override
    protected TechnicalSkills getItem(Node entry) {
        TechnicalSkillsFX skillFX = (TechnicalSkillsFX) ((HBox) entry).getChildren().get(0);
        return new TechnicalSkills(skillFX.getCategory(), skillFX.getSkills().toArray(String[]::new));
    }
}
