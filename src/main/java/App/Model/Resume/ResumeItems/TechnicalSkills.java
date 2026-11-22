package App.Model.Resume.ResumeItems;

import java.util.ArrayList;

public class TechnicalSkills extends ResumeItem {
    
    public String category;
    public ArrayList<String> skills;

    public TechnicalSkills(String category, ArrayList<String> skills) {
        this.category = category;
        this.skills = new ArrayList<>(skills);
    }

    @Override
    public boolean isValid() {
        return !category.isBlank() && !skills.isEmpty();
    }

    @Override
    public String toLatex() {

        String skillsCombined = "";
        for (int i = 0; i < skills.size(); ++i) {
            skillsCombined += skills.get(i) + ((i == skills.size() - 1) ? "" : ", ");
        }

        return "\\textbf{" + sanitizeLatex(category) + "}{: " + sanitizeLatex(skillsCombined) + "}\\\\\n";
    }

    @Override
    public String toJSON() {

        String skillsJSON = "\"skills\": [";
        for (String skill : skills) {
            skillsJSON += "\"" + sanitizeJSON(skill) + "\", ";
        }
        if (!skills.isEmpty()) skillsJSON = skillsJSON.substring(0, skillsJSON.length() - 2);
        skillsJSON += "]\n";

        String JSON = 
                "{\n" + //
                indentText(1, "\"category\": \"" + sanitizeJSON(category) + "\",\n") + //
                indentText(1, skillsJSON) + //
                "}";

        return JSON;
    }

}
