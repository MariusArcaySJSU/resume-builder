package ResumeItems;

public class TechnicalSkillsGroup extends ResumeItemGroup {

    public TechnicalSkillsGroup(TechnicalSkills[] technicalSkillsList) {
        super.groupName = "Technical Skills";
        super.smallText = true;
        super.items = technicalSkillsList;
    }

}