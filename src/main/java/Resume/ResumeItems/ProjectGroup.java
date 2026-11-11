package Resume.ResumeItems;

public class ProjectGroup extends ResumeItemGroup {
    
    public ProjectGroup(Project[] projectList) {
        super.groupName = "Project";
        super.items = projectList;
    }

}
