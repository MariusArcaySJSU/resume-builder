package App.View;

import java.util.ArrayList;

import App.Model.Resume.ResumeItems.ResumeItem;
import App.Model.Resume.ResumeItems.Experience;
import App.Model.Resume.ResumeItems.ExperienceGroup;
import App.Model.Resume.ResumeItems.Project;
import App.Model.Resume.ResumeItems.ProjectGroup;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class ProjectGroupFX extends VBox {

    private final TitledPane titledPane;
    private final VBox projectList;

    public ProjectGroupFX(ProjectGroup projectGroup) {
        this();
        clear();

        for (ResumeItem item : projectGroup.items) {

            Project p = (Project) item;
            addProject(p);

        }
    }

    public ProjectGroupFX() {
        projectList = new VBox(40);
        projectList.setPadding(new Insets(15));
        
        Button addProject = new Button("Add project");
        addProject.setOnAction(event -> {
            addProject();
        });
        
        titledPane = new TitledPane("Project", new VBox(projectList, addProject));
        titledPane.setCollapsible(true);

        addProject();

        this.getChildren().add(titledPane);

    }

    public void clear() {
        projectList.getChildren().clear();
    }

    private void addProject() {
        addProject(null);
    }

    private void addProject(Project project) {

        ProjectFX projectFX = new ProjectFX(project);
        Button removeButton = new Button("Remove");
        removeButton.setFocusTraversable(false);

        VBox projectBox;
        if (projectList.getChildren().isEmpty()) {
            projectBox = new VBox(10, projectFX, new Separator());
        } else {
            projectBox = new VBox(10, projectFX, removeButton, new Separator());
        }

        projectList.getChildren().add(
            projectBox
        );

        removeButton.setOnAction(event -> {
            projectList.getChildren().remove(projectBox);
        });

    }

    public ProjectGroup getProjectGroup() {

        ArrayList<Project> projectArrayList = new ArrayList<>();

        for (Node node : projectList.getChildren()) {
            VBox projectBox = (VBox) node;
            ProjectFX projectFX = (ProjectFX) projectBox.getChildren().get(0);

            projectArrayList.add(projectFX.getProject());
        }

        Project[] projectArray = projectArrayList.toArray(Project[]::new);

        return new ProjectGroup(projectArray);

    }

}