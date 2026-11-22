package App.Model.Resume.ResumeItems;

import java.util.ArrayList;

public class EducationGroup extends ResumeItemGroup {

    public EducationGroup(ArrayList<Education> educationList) {
        super.groupName = "Education";
        super.items = new ArrayList<>(educationList);
    }

}