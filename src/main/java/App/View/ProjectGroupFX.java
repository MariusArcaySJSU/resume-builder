package App.View;

import App.Model.Resume.ResumeItems.Project;
import App.Model.Resume.ResumeItems.ProjectGroup;
import App.Model.Resume.ResumeItems.ResumeItem;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class ProjectGroupFX extends ResumeItemGroupFX<Project> {

    public ProjectGroupFX(ProjectGroup projectGroup) {
        this();
        clear();

        for (ResumeItem item : projectGroup.items) {
            addItem((Project) item);
        }
    }

    public ProjectGroupFX() {
        super("Project", "Add project", 40, new Insets(15), 0);
        addItem(null);
    }

    public ProjectGroup getProjectGroup() {
        return new ProjectGroup(getItems());
    }

    @Override
    protected Node createItemView(Project project) {
        return new ProjectFX(project);
    }

    @Override
    protected Node createEntry(Node itemView, Button removeButton, boolean firstItem) {
        ProjectFX projectFX = (ProjectFX) itemView;
        if (firstItem) {
            return new VBox(10, projectFX, removeButton, new Separator());
        }
        return new VBox(10, projectFX, removeButton, new Separator());
    }

    @Override
    protected Project getItem(Node entry) {
        return ((ProjectFX) ((VBox) entry).getChildren().get(0)).getProject();
    }
}
