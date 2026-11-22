package App.View;

import App.Model.Resume.ResumeItems.Experience;
import App.Model.Resume.ResumeItems.ExperienceGroup;
import App.Model.Resume.ResumeItems.ResumeItem;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class ExperienceGroupFX extends ResumeItemGroupFX<Experience> {

    public ExperienceGroupFX(ExperienceGroup experienceGroup) {
        this();
        clear();

        for (ResumeItem item : experienceGroup.items) {
            addItem((Experience) item);
        }
    }

    public ExperienceGroupFX() {
        super("Experience", "Add experience", 40, new Insets(15), 0);
        addItem(null);
    }

    public ExperienceGroup getExperienceGroup() {
        return new ExperienceGroup(getItems().toArray(Experience[]::new));
    }

    @Override
    protected Node createItemView(Experience experience) {
        return new ExperienceFX(experience);
    }

    @Override
    protected Node createEntry(Node itemView, Button removeButton, boolean firstItem) {
        ExperienceFX experienceFX = (ExperienceFX) itemView;
        if (firstItem) {
            return new VBox(10, experienceFX, new Separator());
        }
        return new VBox(10, experienceFX, removeButton, new Separator());
    }

    @Override
    protected Experience getItem(Node entry) {
        return ((ExperienceFX) ((VBox) entry).getChildren().get(0)).getExperience();
    }
}