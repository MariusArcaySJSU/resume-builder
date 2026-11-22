package App.Model.Resume.ResumeItems;

import java.util.ArrayList;

public class TechnicalSkillsGroup extends ResumeItemGroup {

    public TechnicalSkillsGroup(ArrayList<TechnicalSkills> technicalSkillsList) {
        super.groupName = "Technical Skills";
        super.smallText = true;
        super.items = new ArrayList<>(technicalSkillsList);
    }

}