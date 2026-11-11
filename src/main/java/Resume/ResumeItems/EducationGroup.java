package Resume.ResumeItems;

public class EducationGroup extends ResumeItemGroup {

    public EducationGroup(Education[] educationList) {
        super.groupName = "Education";
        super.items = educationList;
    }

}