package App.Model.Resume.ResumeItems;

import java.util.ArrayList;

public class ExperienceGroup extends ResumeItemGroup {

    public ExperienceGroup(ArrayList<Experience> experienceList) {
        super.groupName = "Experience";
        super.items = new ArrayList<>(experienceList);
    }

}