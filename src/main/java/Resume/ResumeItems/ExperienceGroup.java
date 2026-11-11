package Resume.ResumeItems;

public class ExperienceGroup extends ResumeItemGroup {

    public ExperienceGroup(Experience[] experienceList) {
        super.groupName = "Experience";
        super.items = experienceList;
    }

}