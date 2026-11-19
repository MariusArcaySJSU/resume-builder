package App.View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class ProjectGroupFX extends VBox {

    private final TitledPane titledPane;
    private final VBox projectList;

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

    private void addProject() {

        ProjectFX project = new ProjectFX();
        Button removeButton = new Button("Remove");
        removeButton.setFocusTraversable(false);

        VBox projectBox;
        if (projectList.getChildren().isEmpty()) {
            projectBox = new VBox(10, project, new Separator());
        } else {
            projectBox = new VBox(10, project, removeButton, new Separator());
        }

        projectList.getChildren().add(
            projectBox
        );

        removeButton.setOnAction(event -> {
            projectList.getChildren().remove(projectBox);
        });

    }


}