package App.View;

import java.util.ArrayList;

import App.Model.Resume.ResumeItems.Education;
import App.Model.Resume.ResumeItems.EducationGroup;
import App.Model.Resume.ResumeItems.ResumeItem;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class EducationGroupFX extends ResumeItemGroupFX<Education> {

    public EducationGroupFX(EducationGroup educationGroup) {
        this();
        clear();

        for (ResumeItem item : educationGroup.items) {
            addItem((Education) item);
        }
    }

    public EducationGroupFX() {
        super("Education", "Add education", 40, new Insets(15), 0);
        addItem(null);
    }

    public EducationGroup getEducationGroup() {
        return new EducationGroup(getItems().toArray(Education[]::new));
    }

    @Override
    protected Node createItemView(Education education) {
        return new EducationFX(education);
    }

    @Override
    protected Node createEntry(Node itemView, Button removeButton, boolean firstItem) {
        EducationFX educationFX = (EducationFX) itemView;
        if (firstItem) {
            return new VBox(10, educationFX, new Separator());
        }
        return new VBox(10, educationFX, removeButton, new Separator());
    }

    @Override
    protected Education getItem(Node entry) {
        return ((EducationFX) ((VBox) entry).getChildren().get(0)).getEducation();
    }
}