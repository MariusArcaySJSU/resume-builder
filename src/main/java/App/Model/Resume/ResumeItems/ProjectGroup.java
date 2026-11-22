package App.Model.Resume.ResumeItems;

import java.util.ArrayList;

public class ProjectGroup extends ResumeItemGroup {
    
    public ProjectGroup(ArrayList<Project> projectList) {
        super.groupName = "Project";
        super.items = new ArrayList<>(projectList);
    }
}
